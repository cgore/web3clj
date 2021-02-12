(ns clj-evm.crypto
  (:require [clj-evm.abi :as abi])
  (:import [org.web3j.crypto Credentials WalletUtils]))

(defn load-credentials [^String password ^String source-file]
  (WalletUtils/loadCredentials  password source-file))

(def demo-wallet
  (load-credentials "Password123" "resources/demo-wallet.json"))

(defn address-string [^Credentials credentials]
  (.getAddress credentials))

(defn address [credentials]
  (-> credentials address-string abi/->address))

(comment
  (address demo-wallet)
  )
