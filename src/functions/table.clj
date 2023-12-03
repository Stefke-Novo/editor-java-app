(ns functions.table
  (:require [clojure.string :refer [blank?]]
            [seesaw.core :refer [config! table combobox]]
            [window.color-combobox :refer [create-combobox color-names color?]])
  (:import (clojure.lang Symbol)
           (java.util Vector)
           (javax.swing DefaultCellEditor JComboBox)
           (javax.swing.table DefaultTableColumnModel DefaultTableModel)))




;Create table model
(defn set-value [object row column value]
  (.set (cast Vector (get (into [] (.toArray (.getDataVector object))) row)) column value))

(defn get-value [object row column]
  (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector object))) row)))) column))

(defn create-table-model [model]
  (proxy [DefaultTableModel] [(to-array-2d (conj [] (reduce (fn [v [_ value]] (conj v value)) [] (into [] model)))) (to-array (reduce (fn [v [key _]] (conj v (str key))) [] (into [] model)))]

    (isCellEditable [rows _] (if (= rows 0) true false))

    (getValueAt [rows columns]
      ;(println "---")
      ;(println "GetValueAt type " (type (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) rows)))) columns)))
      ;(println "GetValueAt value " (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) rows)))) columns))
      ;(println "---")
      (get-value this rows columns)
      )

    (setValueAt [value rows columns]
      ;(println "---")
      ;(println "SetValueAt value " value)
      ;(println "SetValueAt type " (type value))
      ;(println "---")
      (if (color? value) (set-value this rows columns (keyword (get color-names value)))
        (if (or (boolean? value) (integer? value)) (set-value this rows columns value)
                                                   (if (blank? value)
                                                     (set-value this rows columns nil)
                                                     (if (= Symbol (type (read-string value)))
                                                       (set-value this rows columns value)
                                                       (set-value this rows columns (read-string value))))))
      )

    (getColumnClass [column]
      ;(println "Type " (type (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) 0)))) column)))
      (condp = (type (get-value this 0 column))
        Boolean Boolean
        Integer Integer
        Object)
      )
    ))

;get date from table
(defn get-data-from-table [table]
  (into (sorted-map) (mapv (fn [key value]
                             ;(if (not (nil? value)) (conj [] key value))
                              (conj [] key value)
                             )
                           (reduce (fn [v c] (conj v (read-string (.getHeaderValue c)))) [] (into [] (enumeration-seq (.getColumns (.getColumnModel table)))))
                           (get (into [] (.toArray (.getDataVector (cast DefaultTableModel (.getModel table))))) 0)
                           )))


(defn v-align-combobox [] (combobox
                            :model [":top" ":center" ":bottom"]
                            :selected-item ":center") )
(defn h-align-combobox [] (combobox
                            :model [":left" ":right" ":leading" ":trailing" ":center"]
                            :selected-item ":center") )
(defn drop-mode-combobox []
  (combobox
    :model
    [:insert :insert-cols :insert-rows :on :on-or-insert :on-or-insert-cols :on-or-insert-rows :use-selection]
    :selected-item :insert))
(defn set-combobox [table column-name combobox]
  (when (< -1 (.findColumn (cast DefaultTableModel (.getModel table)) (str column-name)))
    (.setCellEditor (.getColumn (.getColumnModel table) (.findColumn (cast DefaultTableModel (.getModel table)) (str column-name))) (DefaultCellEditor. ^JComboBox (combobox)))))
(defn configure-table [table]
  (do
    (set-combobox table :foreground create-combobox)
    (set-combobox table :background create-combobox)
    (set-combobox table :caret-color create-combobox)
    (set-combobox table :disabled-text-color create-combobox)
    (set-combobox table :v-text-position v-align-combobox)
    (set-combobox table :valign v-align-combobox)
    (set-combobox table :h-text-position h-align-combobox)
    (set-combobox table :halign h-align-combobox)
    (set-combobox table :drop-mode drop-mode-combobox)
    (.setRowHeight table 30)
    (map #(.setPreferredWidth % 50) (enumeration-seq (.getColumns (cast DefaultTableColumnModel (.getColumnModel table)))))))

;change table content
(defn change-table-content [table model]
  (.setModel table (create-table-model model))
  (configure-table table)
  (config! table)
  )

(defn create-table [model]
  (let [table-result (table :model (create-table-model model) :auto-resize :all-columns)]
    (configure-table table-result)
    (config! table-result)
    (println "Table size " (.getSize table-result))
    table-result))
(defn exclude-null [table]
  (reduce (fn [h k] (if (k h) h
                              (dissoc h k))) (get-data-from-table table) (keys (get-data-from-table table))))


