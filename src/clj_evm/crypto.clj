(ns clj-evm.crypto
  (:import [org.web3j.crypto Credentials WalletUtils]))

(def demo-wallet
  (WalletUtils/loadCredentials "Password123" "resources/demo-wallet.json"))


(comment


  )
