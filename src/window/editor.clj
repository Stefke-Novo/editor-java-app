(ns window.editor
  (:require [seesaw.core :refer [border-panel button flow-panel frame horizontal-panel label scrollable vertical-panel]]
            [seesaw.dev :refer [show-options]]
            [window.editor-components
             :refer
             [
              create-entity-btn
              delete-entity-btn
              entity-buttons
              read-entity-btn
              show-window-btn
              table-editor
              tree-object
              update-entity-btn
              ]])
  (:import (javax.swing JFrame)))

(def north-content (vertical-panel
                    :border [5 "Compound" 10]
                    :items
                    [
                     (scrollable table-editor :preferred-size [2800 :by 14] :vscroll :never)
                     (flow-panel :align :left :items [
                                               create-entity-btn
                                               update-entity-btn
                                               delete-entity-btn
                                               read-entity-btn
                                               show-window-btn
                                               ])
                     ]))

(defn create-editor-window []
  (let [
        editor
        (frame :title "new window" :visible? true
               :content (border-panel
                          :items [
                                  [
                                   (scrollable north-content
                                               :hscroll :as-needed
                                               :preferred-size [640 :by 200])
                                   :north]
                                  [(label
                                     :text "Label 2"
                                     :border [5 "Compound" 10])
                                   :south]
                                  [
                                   (vertical-panel
                                     :border [5 "Items" 10]
                                     :items
                                     entity-buttons)
                                   :east]
                                  [
                                   (scrollable tree-object :preferred-size [300 :by 500])
                                   :west]
                                  [(horizontal-panel
                                     :border [5 "Compound" 10]
                                     :items
                                     [
                                      (label "label 1")
                                      (label "label 2")
                                      ;(xyz-panel
                                      ;  :title "This is working frame"
                                      ;  :border 10
                                      ;  :background :aliceblue)
                                      ]) :center]
                                  ]))
        ]
    (.setExtendedState editor JFrame/MAXIMIZED_BOTH)
    editor))
(def editor (create-editor-window))
(show-options (scrollable north-content))
(show-options (button))
;(into (sorted-map) (mapv (fn [key value] (if (not (nil? value)) (conj [] key value)))
;                         (reduce (fn [v c] (conj v (read-string (.getHeaderValue c)))) [] (into [] (enumeration-seq (.getColumns (.getColumnModel table-editor)))))
;                         (get (into [] (.toArray (.getDataVector (cast DefaultTableModel (.getModel table-editor))))) 0)
;                         ))
(show-options (scrollable []))