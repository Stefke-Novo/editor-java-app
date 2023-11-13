(ns functions.table
  (:require [seesaw.core :refer [table config!]])
  (:import (javax.swing.table DefaultTableModel TableModel)))

;Create table model
(defn create-table-model [columns rows]
  (let [data-model (proxy [DefaultTableModel] []
                     (isCellEditable [_ col]
                       (if (= col 1)
                         true
                         false)))]
    (.setDataVector data-model (to-array-2d (into [] rows)) (to-array columns))
    data-model))

;Create table
(defn create-table [columns rows]
  (table :model (cast TableModel (create-table-model columns rows)) :auto-resize :off))

;get date from table
(defn get-data-from-table [table]
  (.getDataVector table))

;change table content
(defn change-table-content [table rows columns]
  (.setDataVector (.getModel @table) (to-array-2d (into [] rows)) (to-array columns))
  (config! @table))



;Primeri kreiranja proxy-ja
;(def example (proxy [DefaultTableModel] []
;               (isCellEditable [row column]
;                 (if (= column 1)
;                   true
;                   false))))

;(proxy [DefaultTableModel] []
;  (isCellEditable [row column]
;    true))

;Ubacivanje podataka u table model
;(.setDataVector example (to-array-2d (into [] label-model)) (to-array ["Name" "Value"]))

;Kreirnaje tabele
;(create-table ["Name" "Value"] label-model)

