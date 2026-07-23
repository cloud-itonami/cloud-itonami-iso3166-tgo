(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `youth-women-procedure-satisfied?` is the SAME discipline applied to a
  genuinely Togo-specific mechanism: Décret n°2018-028/PR du 1er février
  2018 ('portant attribution d'une part des marchés publics aux jeunes
  et femmes entrepreneurs'), Article 2: 'Les attributions des marchés
  aux jeunes et femmes entrepreneurs seront faites selon les procédures
  simplifiées de demande de cotation et d'appel d'offres restreint avec
  publicité.' When an engagement declares itself a market genuinely
  reserved for jeunes/femmes entrepreneurs, its own declared procedure
  type must be one of those two named simplified procedures -- never an
  open/international tender.

  This is a GENUINELY DIFFERENT check SHAPE than every prior iso3166
  sibling this repo mirrors (bid-evaluation price adjustment, two-
  dimension currency+duration sanction range, discretionary/mandatory
  percentage margin, boolean registry-membership, flat-constant
  threshold, turnover-percentage, three-tier value classification): a
  **CATEGORICAL PROCEDURE-TYPE ELIGIBILITY GATE conditioned on a
  beneficiary-category flag**. It recomputes no monetary amount at all
  -- it verifies that a DECLARED special-eligibility market was actually
  run through a PERMITTED PROCEDURE, the honest single-engagement-level
  slice of a décret whose OTHER, aggregate 20%->25% annual-plan-quota
  provision is deliberately NOT modeled here (no single engagement's own
  fields can validate or falsify an authority's aggregate annual
  compliance -- the same honest scope-narrowing a Burkina Faso iteration
  applied to its own Art. 57 annual PME set-aside quota).

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an
  operator would keep, not the act of submitting a portal registration
  itself (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [clojure.string :as str]
            [marketentry.facts :as facts]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(defn youth-women-permitted-procedures
  "The set of procedure-type keywords Décret n°2018-028/PR Art. 2
  permits for a market genuinely reserved for jeunes/femmes
  entrepreneurs, for `iso3` -- or `#{}` when the jurisdiction has no
  such spec-basis (never a free pass; an unknown jurisdiction satisfies
  nothing)."
  [iso3]
  (:youth-women-quota-permitted-procedures (facts/youth-women-quota-spec-basis iso3) #{}))

(defn youth-women-procedure-satisfied?
  "Does `engagement` comply with Décret n°2018-028/PR Art. 2's
  procedure-type restriction? When `:reserved-for-youth-women?` is not
  true, the restriction does not apply (this décret's Art. 2 is silent
  on non-reserved markets) -- always satisfied. When it IS true, the
  engagement's own declared `:procedure-type` must be a member of the
  jurisdiction's permitted-procedures set. An engagement in a
  jurisdiction with no youth-women-quota spec-basis is NEVER satisfied
  when it claims `:reserved-for-youth-women?` -- there is nothing to
  honestly ground that claim against."
  [engagement]
  (if (true? (:reserved-for-youth-women? engagement))
    (contains? (youth-women-permitted-procedures (:jurisdiction engagement))
               (:procedure-type engagement))
    true))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper-case jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper-case jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
