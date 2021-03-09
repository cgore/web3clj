(ns web3clj.abi.datatypes-test
  (:require [web3clj.abi.datatypes :as datatypes]
            [web3clj.utils.numeric :refer [->big-integer]]
            [clojure.test :refer :all])
  (:import [org.web3j.abi.datatypes Address Uint]))

(deftest address?
  (testing "returns true for an Address"
    (is (datatypes/address? (Address. (->big-integer 123)))))
  (testing "returns false for other things"
    (is (not (datatypes/address? (->big-integer 123))))))

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

(deftest address=
  (testing "single address signature is basically just a check if this is an address"
    (testing "affirmative case"
      (is (datatypes/address= (Address. (->big-integer 123)))))
    (testing "negative case"
      (is (not (datatypes/address= (->big-integer 123))))))
  (testing "two addresses"
    (testing "first not an address ⇒ no"
      (is (not (datatypes/address= (->big-integer 123)
                                   (Address. (->big-integer 123))))))
    (testing "second not an address ⇒ no"
      (is (not (datatypes/address= (Address. (->big-integer 123))
                                   (->big-integer 123)))))
    (testing "both addresses but not matching ⇒ no"
      (is (not (datatypes/address= (Address. (->big-integer 123))
                                   (Address. (->big-integer 124))))))
    (testing "both addresses and matching ⇒ yes"
      (is (datatypes/address= (Address. (->big-integer 123))
                              (Address. (->big-integer 123))))))
  (testing "multiple addresses"
    (let [a (Address. (->big-integer 123))
          b (Address. (->big-integer 456))]
      (is (datatypes/address= a a a))
      (is (datatypes/address= a a a a))
      (is (datatypes/address= a a a a a))
      (is (datatypes/address= a a a a a a))
      (is (datatypes/address= a a a a a a a))
      (is (not (datatypes/address= a a a a a a a b))))))
