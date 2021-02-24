(ns web3clj.abi.datatypes
  (:import [clojure.lang BigInt]
           [java.math BigInteger]
           [org.web3j.abi.datatypes Address Uint]))

(defn ->address
  "Creates a new address instance.
  Value can be a Uint, BigInteger, or String (hex value).
  Bit size should be an int."
  ([value]
   (Address.          (cond (string?            value)              value
                            (int?               value)  (biginteger value)
                            (= BigInt     (type value)) (biginteger value)
                            (= BigInteger (type value))             value
                            (= Uint       (type value))  (.getValue value))))
  ([bit-size value]
   (Address. bit-size (cond (int?               value)  (biginteger value)
                            (= BigInt     (type value)) (biginteger value)
                            (= BigInteger (type value))             value
                            (= Uint       (type value))  (.getValue value)))))

(defn address->uint [^Address address]
  (.toUint address))

(defn address->string [^Address address]
  (.toString address))
