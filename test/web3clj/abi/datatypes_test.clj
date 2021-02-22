(ns web3clj.abi.datatypes-test
  (:require [web3clj.abi.datatypes :as datatypes]
            [clojure.test :refer :all])
  (:import [org.web3j.abi.datatypes Address Uint]))

(deftest ->address
  (testing "Uint round trip"
    (let [ui (Uint. (biginteger 13))]
      (is (= ui (-> ui datatypes/->address datatypes/address->uint))))))
