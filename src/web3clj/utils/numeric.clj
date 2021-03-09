(ns web3clj.utils.numeric
  (:require [web3clj.abi.datatypes :refer [->big-integer]])
  (:import [org.web3j.utils Numeric]))

(defn encode-quantity [value]
  (Numeric/encodeQuantity (->big-integer value)))
