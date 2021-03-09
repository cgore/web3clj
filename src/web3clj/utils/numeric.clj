(ns web3clj.utils.numeric
  (:require [web3clj.abi.datatypes :refer [->big-integer]])
  (:import [org.web3j.utils Numeric]))

(defn encode-quantity
  "Encodes a quantity as a hex string.

  Cf. https://github.com/ethereum/wiki/wiki/JSON-RPC#hex-value-encoding"
  [value]
  (Numeric/encodeQuantity (->big-integer value)))

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
