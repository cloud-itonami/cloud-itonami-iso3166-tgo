(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "TGO" 0)
        s (registry/register-submit "eng-1" "TGO" 0)]
    (is (= "TGO-DFT-000000" (get d "draft_number")))
    (is (= "TGO-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "TGO" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest youth-women-permitted-procedures-for-known-and-unknown-jurisdictions
  (is (= #{:demande-de-cotation :appel-offres-restreint-avec-publicite}
         (registry/youth-women-permitted-procedures "TGO")))
  (is (= #{} (registry/youth-women-permitted-procedures "ATL"))))

(deftest youth-women-procedure-satisfied-when-not-reserved
  (testing "the Art. 2 restriction does not apply to a non-reserved market -- always satisfied"
    (is (true? (registry/youth-women-procedure-satisfied?
                {:reserved-for-youth-women? false :procedure-type :appel-offres-ouvert :jurisdiction "TGO"})))
    (is (true? (registry/youth-women-procedure-satisfied?
                {:jurisdiction "TGO"})))))

(deftest youth-women-procedure-satisfied-when-reserved-and-permitted
  (is (true? (registry/youth-women-procedure-satisfied?
              {:reserved-for-youth-women? true :procedure-type :demande-de-cotation :jurisdiction "TGO"})))
  (is (true? (registry/youth-women-procedure-satisfied?
              {:reserved-for-youth-women? true :procedure-type :appel-offres-restreint-avec-publicite :jurisdiction "TGO"}))))

(deftest youth-women-procedure-unsatisfied-when-reserved-but-open-tender
  (testing "an open/international tender is NOT one of Art. 2's two permitted simplified procedures"
    (is (false? (registry/youth-women-procedure-satisfied?
                 {:reserved-for-youth-women? true :procedure-type :appel-offres-ouvert :jurisdiction "TGO"})))))

(deftest youth-women-procedure-unsatisfied-for-unknown-jurisdiction
  (testing "a reserved-market claim in a jurisdiction with no spec-basis is never honestly satisfiable"
    (is (false? (registry/youth-women-procedure-satisfied?
                 {:reserved-for-youth-women? true :procedure-type :demande-de-cotation :jurisdiction "ATL"})))))
