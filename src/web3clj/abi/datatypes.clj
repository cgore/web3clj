(ns web3clj.abi.datatypes
  (:import [org.web3j.abi.datatypes Address]))

(defn ->address
  "Creates a new address instance.
  Value can be a Uint, BigInteger, or String (hex value).
  Bit size should be an int."
  ([value]
   (Address. value))
  ([bit-size value]
   (Address. bit-size value)))

(defn address->uint [^Address address]
  (.toUint address))

(defn address->string [^Address address]
  (.toString address))
