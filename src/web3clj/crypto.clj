(ns web3clj.crypto
  (:require [web3clj.abi :as abi])
  (:import [org.web3j.crypto Credentials WalletUtils]))

(defn load-credentials [^String password ^String source-file]
  (WalletUtils/loadCredentials  password source-file))

(def demo-wallet
  (load-credentials "Password123" "resources/demo-wallet.json"))

(defn address-string [^Credentials credentials]
  (.getAddress credentials))

(defn address [^Credentials credentials]
  (-> credentials address-string abi/->address))
