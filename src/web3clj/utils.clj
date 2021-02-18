(ns web3clj.utils
  (:import [java.math BigDecimal]
           [org.web3j.utils Convert Convert$Unit]))

(def WEI    Convert$Unit/WEI)
(def KWEI   Convert$Unit/KWEI)
(def MWEI   Convert$Unit/MWEI)
(def GWEI   Convert$Unit/GWEI)
(def SZABO  Convert$Unit/SZABO)
(def FINNEY Convert$Unit/FINNEY)
(def ETHER  Convert$Unit/ETHER)
(def KETHER Convert$Unit/KETHER)
(def METHER Convert$Unit/METHER)
(def GETHER Convert$Unit/GETHER)

(defn ->big-decimal [number]
  (cond (string? number) (BigDecimal. number)
        (number? number) (BigDecimal. number)))

(defn wei    [amount] {WEI    (->big-decimal amount)})
(defn kwei   [amount] {KWEI   (->big-decimal amount)})
(defn mwei   [amount] {MWEI   (->big-decimal amount)})
(defn gwei   [amount] {GWEI   (->big-decimal amount)})
(defn szabo  [amount] {SZABO  (->big-decimal amount)})
(defn finney [amount] {FINNEY (->big-decimal amount)})
(defn ether  [amount] {ETHER  (->big-decimal amount)})
(defn kether [amount] {KETHER (->big-decimal amount)})
(defn mether [amount] {METHER (->big-decimal amount)})
(defn gether [amount] {GETHER (->big-decimal amount)})

(defn ->wei
  [number ^Convert$Unit unit]
  (Convert/toWei (->big-decimal number) unit))

(defn kwei->wei   [kwei-amount]   (->wei kwei-amount   KWEI))
(defn mwei->wei   [mwei-amount]   (->wei mwei-amount   MWEI))
(defn gwei->wei   [gwei-amount]   (->wei gwei-amount   GWEI))
(defn szabo->wei  [szabo-amount]  (->wei szabo-amount  SZABO))
(defn finney->wei [finney-amount] (->wei finney-amount FINNEY))
(defn ether->wei  [ether-amount]  (->wei ether-amount  ETHER))
(defn kether->wei [kether-amount] (->wei kether-amount KETHER))
(defn mether->wei [mether-amount] (->wei mether-amount METHER))
(defn gether->wei [gether-amount] (->wei gether-amount GETHER))

(defn <-wei
  [number ^Convert$Unit unit]
  (Convert/fromWei (->big-decimal number) unit))

(defn wei->kwei   [kwei-amount]   (<-wei kwei-amount   KWEI))
(defn wei->mwei   [mwei-amount]   (<-wei mwei-amount   MWEI))
(defn wei->gwei   [gwei-amount]   (<-wei gwei-amount   GWEI))
(defn wei->szabo  [szabo-amount]  (<-wei szabo-amount  SZABO))
(defn wei->finney [finney-amount] (<-wei finney-amount FINNEY))
(defn wei->ether  [ether-amount]  (<-wei ether-amount  ETHER))
(defn wei->kether [kether-amount] (<-wei kether-amount KETHER))
(defn wei->mether [mether-amount] (<-wei mether-amount METHER))
(defn wei->gether [gether-amount] (<-wei gether-amount GETHER))
