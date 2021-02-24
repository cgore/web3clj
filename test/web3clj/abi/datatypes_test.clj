(ns web3clj.abi.datatypes-test
  (:require [web3clj.abi.datatypes :as datatypes]
            [clojure.test :refer :all])
  (:import [org.web3j.abi.datatypes Address Uint]))

(deftest ->address
  (testing "works with big integers"
    (is (= (biginteger 13)
           (.getValue (datatypes/address->uint (datatypes/->address (biginteger 13)))))))
  (testing "works with normal integers"
    (is (= (biginteger 14)
           (.getValue (datatypes/address->uint (datatypes/->address 14)))))))

(deftest address->uint
  (testing "round trip"
    (is (= (biginteger 15)
           (.getValue (datatypes/address->uint (datatypes/->address (biginteger 15))))))))

(deftest address->string
  (testing "round trip"
    (let [s "0x000000000000000000000000000000000000000d"]
      (is (= s (-> s datatypes/->address datatypes/address->string))))))
