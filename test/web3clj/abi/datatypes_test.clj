(ns web3clj.abi.datatypes-test
  (:require [web3clj.abi.datatypes :as datatypes]
            [clojure.test :refer :all])
  (:import [org.web3j.abi.datatypes Address Uint]))

(deftest address->uint
  (testing "round trip"
    (let [ui (Uint. (biginteger 13))]
      (is (= ui (-> ui datatypes/->address datatypes/address->uint))))))

(deftest address->string
  (testing "round trip"
    (let [s "0x000000000000000000000000000000000000000d"]
      (is (= s (-> s datatypes/->address datatypes/address->string))))))
