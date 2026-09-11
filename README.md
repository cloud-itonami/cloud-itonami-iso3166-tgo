# cloud-itonami-iso3166-tgo

**TGO**: Togolese Republic.

- ARCOP e-procurement (arcop.tg, formerly branded ARMP)
- RCCM + NIF

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture
as `cloud-itonami-iso3166-ben`/`-ner`/`-bfa` (fellow UEMOA + OHADA
member states):

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites the Autorité de
  Régulation de la Commande Publique (ARCOP, Loi n°2021-033 du 31
  décembre 2021), the Direction Nationale de Contrôle de la Commande
  Publique (DNCCP), RCCM (OHADA's Acte Uniforme relatif au Droit
  Commercial Général, adopted in Lomé itself) and NIF (Numéro
  d'Identification Fiscale, Office Togolais des Recettes -- OTR),
  all bundled into ONE 'carte unique de création d'entreprise' by the
  Centre de Formalités des Entreprises (CFE Togo). `governor.cljc`'s
  flagship check independently verifies that a market an engagement
  declares reserved for jeunes/femmes entrepreneurs was actually run
  through one of Décret n°2018-028/PR Art. 2's two permitted simplified
  procedures (demande de cotation / appel d'offres restreint avec
  publicité) -- a categorical procedure-type eligibility gate, a
  genuinely new check shape for this family (not a price recompute or a
  sanction-range check).
- `src/statute/facts.cljk` -- general-law catalog: the OHADA Uniform Act
  on Commercial Companies (AUSCGIE, directly applicable), the Code des
  Investissements (Loi n°2019-005 du 17 juin 2019, creates the API-ZF
  investment-promotion guichet unique), and the Office Togolais des
  Recettes' own creation law (Loi n°2012-016 du 14 décembre 2012). A
  Labour Code / personal-data-protection law was investigated and
  honestly NOT found with a confirmed citation this session -- see the
  namespace docstring's coverage note.

Every citation is WebFetch/curl-verified against an official source
(arcop.tg, ohada.org, uemoa.int, otr.tg, cfetogo.tg, jo.gouv.tg,
investmentpolicy.unctad.org); see each namespace's docstring for the
full research trail and any honestly-narrowed scope or disclosed gap.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Togo:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
