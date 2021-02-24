(ns web3clj.abi.datatypes-test
  (:require [web3clj.abi.datatypes :as datatypes]
            [clojure.test :refer :all])
  (:import [org.web3j.abi.datatypes Address Uint]))

(deftest ->big-integer
  (testing "passes through an existing BigInteger"
    (let [i (biginteger 12)]
      (is (= i (datatypes/->big-integer i)))))
  (testing "works with BigInt"
    (is (= (biginteger 13)
           (datatypes/->big-integer (bigint 13)))))
  (testing "works with normal ints"
    (is (= (biginteger 14)
           (datatypes/->big-integer 14))))
  (testing "works with strings"
    (is (= (biginteger 15)
           (datatypes/->big-integer "15")))))

(deftest ->non-negative-big-integer
  (testing "positives are accepted and translated"
    (is (= (biginteger 12)
           (datatypes/->non-negative-big-integer 12)
           (datatypes/->non-negative-big-integer (bigint 12))
           (datatypes/->non-negative-big-integer (biginteger 12))
           (datatypes/->non-negative-big-integer "12"))))
  (testing "negatives are rejected and nil is returned"
    (is (= nil
           (datatypes/->non-negative-big-integer -12)
           (datatypes/->non-negative-big-integer (bigint -12))
           (datatypes/->non-negative-big-integer (biginteger -12))
           (datatypes/->non-negative-big-integer "-12")))))

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
