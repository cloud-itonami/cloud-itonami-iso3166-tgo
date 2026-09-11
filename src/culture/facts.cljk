(ns culture.facts
  "Country-level regional-culture catalog for Togo (TGO) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"TGO"
   [{:culture/id "tgo.dish.fufu"
     :culture/name "Fufu"
     :culture/country "TGO"
     :culture/kind :dish
     :culture/summary "Togolese staple made from peeled and boiled yams pounded with a pestle until reaching a dough consistency."
     :culture/url "https://en.wikipedia.org/wiki/Togolese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.dish.akpan"
     :culture/name "Akpan"
     :culture/country "TGO"
     :culture/kind :dish
     :culture/summary "Togolese fermented maize dessert, steamed in banana leaves."
     :culture/url "https://en.wikipedia.org/wiki/Togolese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.dish.koklo-meme"
     :culture/name "Koklo meme"
     :culture/country "TGO"
     :culture/kind :dish
     :culture/summary "Togolese dish of grilled chicken with chili sauce."
     :culture/url "https://en.wikipedia.org/wiki/Togolese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.beverage.sodabi"
     :culture/name "Sodabi"
     :culture/country "TGO"
     :culture/kind :beverage
     :culture/summary "Homemade alcohol obtained by distilling palm wine, holding an important place in daily life and religious practices in Togo and Benin."
     :culture/url "https://en.wikipedia.org/wiki/Sodabi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.festival.evala"
     :culture/name "Evala"
     :culture/country "TGO"
     :culture/kind :festival
     :culture/summary "Traditional wrestling practised mainly by the Kabyé of northern Togo, the penultimate element of a young men's initiation rite into adulthood."
     :culture/url "https://en.wikipedia.org/wiki/Kabye_people"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.heritage.koutammakou"
     :culture/name "Koutammakou"
     :culture/name-local "Land of the Batammariba"
     :culture/country "TGO"
     :culture/kind :heritage
     :culture/summary "Cultural landscape on the border between northern Togo and Benin, featuring traditional mud tower-houses (Takienta); designated a UNESCO World Heritage Site in 2004 and expanded in 2023."
     :culture/url "https://en.wikipedia.org/wiki/Koutammakou"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tgo.heritage.akodessawa-fetish-market"
     :culture/name "Akodessawa Fetish Market"
     :culture/country "TGO"
     :culture/kind :heritage
     :culture/summary "Market in Lomé, Togo, described as the world's largest voodoo market, selling items used in vodún religious practices."
     :culture/url "https://en.wikipedia.org/wiki/Akodessawa_Fetish_Market"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tgo culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "TGO"))
                 " TGO entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
