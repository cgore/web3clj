(ns web3clj.tx
  (:require [web3clj.utils :as utils])
  (:import [org.web3j.protocol.core.methods.response TransactionReceipt]
           [org.web3j.protocol Web3j]
           [org.web3j.tx Transfer]
           [org.web3j.tx.gas DefaultGasProvider]))

(defn send-funds
  ([^Web3j web3j credentials recipient-address amount]
   (send-funds web3j credentials recipient-address
               (utils/amount->value amount)
               (utils/amount->unit  amount)))
  ([^Web3j web3j credentials recipient-address value unit]
   (Transfer/sendFunds web3j credentials recipient-address
                       value unit)))

(defn send-funds! [& rest]
  (.send (apply send-funds rest)))

(defn tx-hash [^TransactionReceipt tx-receipt]
  (.getTransactionHash tx-receipt))

(defn ok? [^TransactionReceipt tx-receipt]
  (.isStatusOK tx-receipt))
