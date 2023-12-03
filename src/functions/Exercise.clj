(ns functions.Exercise
  (:require [functions.component-models :refer [label-model]]
            [functions.table :refer [create-table]]
            [seesaw.core :refer [frame horizontal-panel label scrollable]]
            [seesaw.dev :refer [show-options]])
  (:import (javax.swing.table DefaultTableModel)))

;(defn set-value [object row column value]
;  (.set (cast Vector (get (into [] (.toArray (.getDataVector object))) row)) column value))
;
;(defn get-value [object row column]
;  (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector object))) row)))) column))
;
;(defn create-table-model [model]
;  (proxy [DefaultTableModel] [(to-array-2d (conj [] (reduce (fn [v [_ value]] (conj v value)) [] (into [] model)))) (to-array (reduce (fn [v [key _]] (conj v (str key))) [] (into [] model)))]
;
;    (isCellEditable [rows _] (if (= rows 0) true false))
;
;    (getValueAt [rows columns]
;      ;(println "---")
;      ;(println "GetValueAt type " (type (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) rows)))) columns)))
;      ;(println "GetValueAt value " (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) rows)))) columns))
;      ;(println "---")
;      (get-value this rows columns)
;      )
;
;    (setValueAt [value rows columns]
;      ;(println "---")
;      ;(println "SetValueAt value " value)
;      ;(println "SetValueAt type " (type value))
;      ;(println "---")
;      (if (or (boolean? value)(integer? value)) (set-value this rows columns value)
;        (if (blank? value)
;          (set-value this rows columns nil)
;          (if (= Symbol (type (read-string value)))
;            (set-value this rows columns value)
;            (set-value this rows columns (read-string value)))))
;      )
;
;    (getColumnClass [column]
;      ;(println "Type " (type (get (into [] (.toArray (cast Vector (get (into [] (.toArray (.getDataVector this))) 0)))) column)))
;      (condp = (type (get-value this 0 column))
;        Boolean Boolean
;        Integer Integer
;        Object)
;      )
;    ))
;(def ex-table-model (create-table-model label-model))
;(def ex-table (table :model ex-table-model))
(def ex-table (create-table label-model))
(frame :visible? true
       :content (horizontal-panel :items [
                                          ;(scrollable (table :model (DefaultTableModel. (to-array-2d (conj [] (reduce (fn [v [_ value]] (conj v (str value))) [] (into [] label-model)))) (to-array (reduce (fn [v [key _]] (conj v (str key))) [] (into [] label-model))))))
                                          (scrollable ex-table)
                                          ]))

(.getDataVector (.getModel ex-table))
(.getColumnName)
(clojure.reflect/reflect (DefaultTableModel) )
(show-options (label))