(ns web3clj.utils
  (:import [java.math BigDecimal]
           [org.web3j.utils Convert Convert$Unit]))

(def wei    Convert$Unit/WEI)
(def kwei   Convert$Unit/KWEI)
(def mwei   Convert$Unit/MWEI)
(def gwei   Convert$Unit/GWEI)
(def szabo  Convert$Unit/SZABO)
(def finney Convert$Unit/FINNEY)
(def ether  Convert$Unit/ETHER)
(def kether Convert$Unit/KETHER)
(def mether Convert$Unit/METHER)
(def gether Convert$Unit/GETHER)

(defn ->big-decimal [number]
  (cond (string? number) (BigDecimal. number)
        (number? number) (BigDecimal. number)))

(defn ->wei
  [number ^Convert$Unit unit]
  (Convert/toWei (->big-decimal number) unit))

(defn kwei->wei   [kwei-amount]   (->wei kwei-amount   kwei))
(defn mwei->wei   [mwei-amount]   (->wei mwei-amount   mwei))
(defn gwei->wei   [gwei-amount]   (->wei gwei-amount   gwei))
(defn szabo->wei  [szabo-amount]  (->wei szabo-amount  szabo))
(defn finney->wei [finney-amount] (->wei finney-amount finney))
(defn ether->wei  [ether-amount]  (->wei ether-amount  ether))
(defn kether->wei [kether-amount] (->wei kether-amount kether))
(defn mether->wei [mether-amount] (->wei mether-amount mether))
(defn gether->wei [gether-amount] (->wei gether-amount gether))

(defn <-wei
  [number ^Convert$Unit unit]
  (Convert/fromWei (->big-decimal number) unit))

(defn wei->kwei   [kwei-amount]   (<-wei kwei-amount   kwei))
(defn wei->mwei   [mwei-amount]   (<-wei mwei-amount   mwei))
(defn wei->gwei   [gwei-amount]   (<-wei gwei-amount   gwei))
(defn wei->szabo  [szabo-amount]  (<-wei szabo-amount  szabo))
(defn wei->finney [finney-amount] (<-wei finney-amount finney))
(defn wei->ether  [ether-amount]  (<-wei ether-amount  ether))
(defn wei->kether [kether-amount] (<-wei kether-amount kether))
(defn wei->mether [mether-amount] (<-wei mether-amount mether))
(defn wei->gether [gether-amount] (<-wei gether-amount gether))
