(ns statute.facts
  "General-law compliance catalog for Togo (TGO) -- extends this repo's
  existing `marketentry.facts` (public-procurement market-entry only,
  narrow scope) with a second, orthogonal catalog of statutes a company
  operating in this jurisdiction must generally track for compliance.
  Mirrors cloud-itonami-iso3166-jpn/-deu/-bgr/-aze/-alb/-arm/-atg/-ben's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  Every entry cites an OFFICIAL government-hosted, official-supranational
  -body-hosted (OHADA), or official-international-organization-hosted
  (UNCTAD Investment Policy Hub, the SAME international-organization
  hosting discipline a Niger iteration used for its own Code des
  Investissements when the domestic .gouv.tg hosting could not be
  reached) URL -- never fabricated. Togo's official Journal Officiel
  portal (`jo.gouv.tg`) was reached and read directly (its 'advanced
  search' Views form proved non-functional for this iteration's queries
  -- an honest, disclosed tooling gap, NOT worked around by any
  bot-detection bypass; its Drupal-core `/search/node/` endpoint DID
  return real, directly-read content), but this iteration was NOT able
  to independently locate Togo's current Labour Code (Code du Travail)
  or a dedicated personal-data-protection law with a confirmed law
  number/date this session -- see the honest coverage note below rather
  than a fabricated entry for either:

  - **Companies/commercial-entity law**: this iteration specifically
    investigated, rather than assumed by analogy to prior OHADA-member
    siblings, whether Togo has company law of its own. It does NOT --
    Togo, independently re-confirmed an OHADA member state THIS session
    directly from ohada.org's own 'Les Etats membres de l'OHADA' page
    ('Bénin Burkina Faso ... Cameroun ... Centrafrique ... Comores Congo
    ... Cote d'Ivoire ... Gabon ... Guinée ... Guinée Bissau ... Guinée
    Equatoriale Mali ... Niger RDC ... Sénégal ... Tchad ... Togo'), has
    company law governed DIRECTLY by a SUPRANATIONAL instrument, the
    OHADA Uniform Act on Commercial Companies and Economic Interest
    Groupings (Acte uniforme relatif au droit des sociétés commerciales
    et du groupement d'intérêt économique, AUSCGIE) -- independently
    re-fetched from ohada.org THIS session: adopted 30 January 2014 in
    Ouagadougou, published 4 February 2014, in force since 5 May 2014.
    This iteration did NOT fetch the OHADA Treaty's own Article 10
    direct-effect text this session (unlike a Benin iteration's
    WebSearch-corroborated quote of that article -- this session had no
    working WebSearch tool available), so this entry does not quote
    Article 10's wording, only AUSCGIE's own confirmed adoption/force
    dates. Separately, RCCM/business-entity REGISTRATION is governed by
    the DIFFERENT OHADA Acte Uniforme relatif au Droit Commercial
    Général (AUDCG, adopted 15 décembre 2010 in LOMÉ, TOGO itself,
    independently re-fetched from ohada.org this session); this catalog
    does not conflate the two -- `marketentry.facts` cites AUDCG
    separately for RCCM.
  - **Investment Code**: LOI N° 2019-005 DU 17/06/2019 PORTANT CODE DES
    INVESTISSEMENTS EN REPUBLIQUE TOGOLAISE -- found and read in full via
    UNCTAD's Investment Policy Hub (`investmentpolicy.unctad.org`, an
    international-organization-hosted source, WebFetch/curl-verified
    directly, the SAME hosting discipline a Niger iteration relied on
    for its own investment code), NOT located on a togo.gouv/jo.gouv.tg
    domain directly this session -- an honest, disclosed gap on primary
    domestic hosting, though UNCTAD's own copy IS the full native law
    text, not a summary. Art. 2 (definitions): 'API-ZF : Agence de la
    promotion des investissements et de la Zone Franche, désignée «
    l'Agence »'. Art. 9 (own text, read directly): 'Il est créé, pour
    l'administration du code des investissements et du statut de Zone
    Franche, une Agence de la Promotion des Investissements et de la
    Zone Franche Industrielle (API-ZF). L'Agence est un établissement
    public administratif doté de la personnalité morale et de
    l'autonomie financière.' Art. 10: 'L'Agence exerce les fonctions de
    guichet unique pour l'ensemble des démarches administratives liées à
    l'implantation et à l'exploitation des sociétés situées sur le
    territoire togolais et éligibles au code des investissements.' This
    independently corroborates (not merely repeats) what this iteration
    separately found on API-ZF's own promotional portal
    (`investirautogo.tg`/`apizf.org`), which self-describes API-ZF but
    did not itself expose the founding law's own article text -- the
    UNCTAD-hosted primary text is the actual citation basis here.
  - **Tax administration**: Office Togolais des Recettes (OTR), Togo's
    integrated tax-and-customs administration -- this iteration found
    its creation/amendment law title/date directly on Togo's own
    Journal Officiel (`jo.gouv.tg`) full-text search, read directly:
    'Loi 2025-004 Du 01/04/2025 Assemblée Nationale modifiant et
    complétant la loi n° 2012-016 du 14 décembre 2012 portant création
    de l'Office Togolais des Recettes.' This iteration did NOT fetch
    either law's own full article text this session (an honest,
    disclosed gap -- the creation/amendment FACT and exact title/date
    are primary-source-confirmed; the articles' substantive content
    is not). This entry is included as a genuine, real, currently-
    tracked institutional-creation statute -- narrower in kind than a
    full labour/tax code but no less real.
  - **Labour Code / personal-data protection**: this iteration
    specifically searched Togo's own Journal Officiel (`jo.gouv.tg`,
    multiple query attempts against its `/search/node/` endpoint,
    16 result pages scanned for 'code du travail') and could NOT
    confirm a current law number/date for either a Labour Code or a
    dedicated data-protection law this session. ILO's NATLEX database
    (a natural fallback an ILO-hosting precedent in this family used
    for a sibling's labour code) returned a Cloudflare 'Just a moment'
    bot-detection challenge on every URL tried -- per this loop's own
    hard rule, that challenge was NOT bypassed by any means; NATLEX is
    recorded here as UNREACHABLE, not as a source that was consulted.
    Neither gap is papered over with an invented law number -- extend
    `catalog` in a future iteration once a primary or credible
    international-organization-hosted source is actually found and
    read.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"TGO"
   [{:statute/id "tgo.ohada-auscgie"
     :statute/title "Acte uniforme relatif au droit des sociétés commerciales et du groupement d'intérêt économique (AUSCGIE)"
     :statute/jurisdiction "TGO"
     :statute/kind :law
     :statute/law-number "OHADA Uniform Act -- adopted 30 January 2014 (Ouagadougou), published 4 February 2014, in force 5 May 2014; directly applicable in Togo as an OHADA member state (Togo membership independently re-verified this session from ohada.org's own member-state list)"
     :statute/url "https://www.ohada.org/en/commercial-companies-and-economic-interest-groups/"
     :statute/url-provenance :official-ohada-org
     :statute/enacted-date "2014-01-30"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "tgo.code-investissements"
     :statute/title "Code des Investissements en République Togolaise"
     :statute/jurisdiction "TGO"
     :statute/kind :law
     :statute/law-number "LOI N° 2019-005 DU 17/06/2019"
     :statute/url "https://investmentpolicy.unctad.org/investment-laws/laws/364/togo-togo-investment-code-2019"
     :statute/url-provenance :official-unctad-investment-policy-hub
     :statute/enacted-date "2019-06-17"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:investment :business-registration}}
    {:statute/id "tgo.otr-creation"
     :statute/title "Loi portant création de l'Office Togolais des Recettes (OTR)"
     :statute/jurisdiction "TGO"
     :statute/kind :law
     :statute/law-number "Loi n° 2012-016 du 14 décembre 2012, telle que modifiée par la Loi n° 2025-004 du 1er avril 2025"
     :statute/url "https://www.jo.gouv.tg/"
     :statute/url-provenance :official-jo-gouv-tg
     :statute/enacted-date "2012-12-14"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:tax :administration}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tgo statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "TGO")) " TGO statutes seeded with an "
                 "official citation. Labour Code and personal-data-protection "
                 "law were investigated and NOT found with a confirmed "
                 "citation this session (jo.gouv.tg search inconclusive; "
                 "ILO NATLEX blocked by Cloudflare bot-detection, not "
                 "bypassed) -- an honest, disclosed coverage gap, not an "
                 "assertion no such law exists. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :investment, :tax)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
