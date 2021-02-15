(ns web3clj.protocol
  (:import [java.util.concurrent ScheduledExecutorService]
           [org.web3j.protocol Web3j Web3jService]
           [org.web3j.protocol.core.methods.response TransactionReceipt]))

(defn web3j-build
  ([^Web3jService service]
   (Web3j/build service))
  ([^Web3jService service ^long polling-interval ^ScheduledExecutorService executor]
   (Web3j/build service polling-interval executor)))
