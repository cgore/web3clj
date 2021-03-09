(ns web3clj.utils.numeric-test
  (:require [web3clj.utils.numeric :as numeric]
            [clojure.test :refer :all]))

(deftest ->big-integer
  (testing "passes through an existing BigInteger"
    (let [i (biginteger 12)]
      (is (= i (numeric/->big-integer i)))))
  (testing "works with BigInt"
    (is (= (biginteger 13)
           (numeric/->big-integer (bigint 13)))))
  (testing "works with normal ints"
    (is (= (biginteger 14)
           (numeric/->big-integer 14))))
  (testing "works with strings"
    (is (= (biginteger 15)
           (numeric/->big-integer "15")))))

(deftest ->non-negative-big-integer
  (testing "positives are accepted and translated"
    (is (= (biginteger 12)
           (numeric/->non-negative-big-integer 12)
           (numeric/->non-negative-big-integer (bigint 12))
           (numeric/->non-negative-big-integer (biginteger 12))
           (numeric/->non-negative-big-integer "12"))))
  (testing "negatives are rejected and nil is returned"
    (is (= nil
           (numeric/->non-negative-big-integer -12)
           (numeric/->non-negative-big-integer (bigint -12))
           (numeric/->non-negative-big-integer (biginteger -12))
           (numeric/->non-negative-big-integer "-12")))))

(deftest encode-quantity
  (testing "some examples"
    (are [result input] (= result (numeric/encode-quantity input))
      "0x0"       0
      "0xc"       12
      "0x7b"      123
      "0x4d2"     1234
      "0x3039"    12345
      "0x1e240"   123456
      "0x12d687"  1234567
      "0xbc614e"  12345678
      "0x75bcd15" 123456789)))

(deftest decode-quantity
  (testing "some examples"
    (are [input result] (= result (numeric/decode-quantity input))
      "0x0"       0
      "0xc"       12
      "0x7b"      123
      "0x4d2"     1234
      "0x3039"    12345
      "0x1e240"   123456
      "0x12d687"  1234567
      "0xbc614e"  12345678
      "0x75bcd15" 123456789)))

(deftest clean-hex-prefix
  (testing "removes it if it's there"
    (is (= "123" (numeric/clean-hex-prefix "0x123"))))
  (testing "doesn't mangle it if it's not there"
    (is (= "123" (numeric/clean-hex-prefix "123")))))

(deftest prepend-hex-prefix
  (testing "adds it if it's not there"
    (is (= "0x123" (numeric/prepend-hex-prefix "123"))))
  (testing "doesn't mangle it if it's not there"
    (is (= "0x123" (numeric/prepend-hex-prefix "0x123")))))

(deftest contains-hex-prefix?
  (testing "false for nil"
    (is (not (numeric/contains-hex-prefix? nil))))
  (testing "false for empty string"
    (is (not (numeric/contains-hex-prefix? ""))))
  (testing "false for missing the prefix"
    (is (not (numeric/contains-hex-prefix? "123"))))
  (testing "true case"
    (is (numeric/contains-hex-prefix? "0x123"))))
