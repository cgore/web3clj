(ns clj-evm.crypto
  (:import [org.web3j.crypto Credentials WalletUtils]))

(defn load-credentials [password source-file]
  (WalletUtils/loadCredentials  password source-file))

(def demo-wallet
  (load-credentials "Password123" "resources/demo-wallet.json"))


(comment
  (.getAddress demo-wallet)

  )
