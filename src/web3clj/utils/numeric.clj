(ns web3clj.utils.numeric
  (:require [web3clj.abi.datatypes :refer [->big-integer]])
  (:import [org.web3j.utils Numeric]))

(defn encode-quantity
  "Encodes a quantity as a hex string.

  Cf. https://github.com/ethereum/wiki/wiki/JSON-RPC#hex-value-encoding"
  [value]
  (Numeric/encodeQuantity (->big-integer value)))
