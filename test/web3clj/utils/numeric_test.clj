(ns web3clj.utils.numeric-test
  (:require [web3clj.utils.numeric :as numeric]
            [clojure.test :refer :all]))

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
