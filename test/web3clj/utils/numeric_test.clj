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
