(ns clj-evm.abi
  (:import [org.web3j.abi.datatypes Address]))

(defn ->address [^String string]
  (Address. string))
