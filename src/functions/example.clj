(ns functions.example
  (:gen-class
    :name functions.example.ElementTableModel
    :extends javax.swing.table.DefaultTableModel
    :constructors {[clojure.lang.PersistentHashMap] [java.lang.Class java.lang.Class]}
    :init init
    :state state
    :main false
    :exposes-methods {
                      isCellEditable -is-cell-editable,
                      getValueAt     -get-value-at
                      }))
(def state (atom {:rows [] :columns []}))

(defn -hello [_]
  "Hello")

(defn -init [model]
  [[(to-array-2d (conj [] (reduce (fn [v [_ value]] (conj v (str value))) [] (into [] model)))) (to-array (reduce (fn [v [key _]] (conj v (str key))) [] (into [] model)))] {}])

(defn -get-state [_] @state)

(defn -get-rows [_] (:rows @state))
(defn -set-rows [_ rows] (swap! state assoc :rows rows))
(defn -get-columns [_] (:columns state))
(defn -set-columns [_ columns] (swap! state assoc :columns columns))
(defn -is-cell-editable [_ row _]
  (if (not (zero? row))
              true
              false))
;(get (get (:rows state) row) column)
(defn -get-value-at [row column]
  (let [element (get (get (:rows state) row) column)]
    (condp = (get (:columns state) column)
      (:drag-enabled? :enabled? :focusable? :visible?) element
      ;(:background :border :bounds :font :foreground :h-next-position :halign :icon :id :listen :maximum-size :minimum-size :popup :size :text :tip :v-text-position :valign) (str String element)
      (str element)
      ))
  )