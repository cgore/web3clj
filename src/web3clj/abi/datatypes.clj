(ns web3clj.abi.datatypes
  (:import [clojure.lang BigInt]
           [java.math BigInteger]
           [org.web3j.abi.datatypes Address Uint]))

(defn ->big-integer
  "Converts integers of various types to BigInteger."
  [n]
  (cond (= BigInteger (type n))  n
        (= BigInt (type n))     (biginteger n)
        (int? n)                (biginteger n)
        (string? n)             (biginteger n)))

(defn ->non-negative-big-integer
  "Converts non-negative integers of various types to a Biginteger."
  [n]
  (let [v (->big-integer n)]
    (when (and (not (nil? v))
               (not (neg? v)))
      v)))

(defn address?
  "Returns true if this is a web3j Address."
  [address]
  (= Address (type address)))

(defn ->address
  "Creates a new address instance.
  Value can be a Uint, BigInteger, or String (hex value).
  Bit size should be an int."
  ([value]
   (Address. (cond (string? value)                    value
                   (not (nil? (->big-integer value))) (->big-integer value)
                   (= Uint (type value))              (.getValue value))))
  ([bit-size value]
   (Address. bit-size (cond (not (nil? (->big-integer value)))  (->big-integer value)
                            (= Uint (type value))               (.getValue value)))))

(defn address->uint
  "Converts the address to a Uint."
  [^Address address]
  (.toUint address))

(defn address->string
  "Converts the address to a zero-padded hex string."
  [^Address address]
  (.toString address))

(defn address=
  "Returns true if one or more addresses are all equal to each other."
  ([address]
   (address? address))
  ([address other]
   (and (address? address)
        (address? other)
        (.equals ^Address address ^Address other)))
  ([address other & others]
   (and (address= address other)
        (apply address= address others))))
