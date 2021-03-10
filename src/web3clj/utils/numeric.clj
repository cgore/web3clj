(ns web3clj.utils.numeric
  (:import [clojure.lang BigInt]
           [java.math BigInteger]
           [org.web3j.abi.datatypes Uint]
           [org.web3j.utils Numeric]))

(defn decode-quantity
  "Decodes a hex string."
  [^String string]
  (Numeric/decodeQuantity string))

(defn clean-hex-prefix
  "Removes the hex prefix \"0x\" from a string, if it's there."
  [^String string]
  (Numeric/cleanHexPrefix string))

(defn prepend-hex-prefix
  "Adds the hex prefix \"0x\" to a hex string, if it's missing."
  [^String string]
  (Numeric/prependHexPrefix string))

(defn contains-hex-prefix?
  "Returns true if the hex string contains the \"0x\" hex prefix."
  [string]
  (and string
       (string? string)
       (Numeric/containsHexPrefix string)))

(defn ->big-integer
  "Converts integers of various types to BigInteger."
  [n]
  (cond (= BigInteger (type n))         n
        (= BigInt (type n))            (biginteger n)
        (int? n)                       (biginteger n)
        (and (string? n)
             (contains-hex-prefix? n)) (BigInteger. (clean-hex-prefix n) 16)
        (string? n)                    (biginteger n)))

(defn ->non-negative-big-integer
  "Converts non-negative integers of various types to a Biginteger."
  [n]
  (let [v (->big-integer n)]
    (when (and (not (nil? v))
               (not (neg? v)))
      v)))

(defn encode-quantity
  "Encodes a quantity as a hex string.

  Cf. https://github.com/ethereum/wiki/wiki/JSON-RPC#hex-value-encoding"
  [value]
  (Numeric/encodeQuantity (->big-integer value)))
