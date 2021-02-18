(ns web3clj.tx
  (:require [web3clj.utils :as utils])
  (:import [org.web3j.tx Transfer]
           [org.web3j.tx.gas DefaultGasProvider]))

(defn send-funds
  ([web3j credentials recipient-address amount]
   (send-funds web3j credentials recipient-address
               (utils/amount->value amount)
               (utils/amount->unit  amount)))
  ([web3j credentials recipient-address value unit]
   (Transfer/sendFunds web3j credentials recipient-address
                       value unit)))

(defn send-funds! [& rest]
  (.send (apply send-funds rest)))
