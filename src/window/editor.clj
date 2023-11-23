(ns window.editor
  (:require [seesaw.core :refer [frame border-panel label horizontal-panel xyz-panel vertical-panel tree scrollable]]
            [seesaw.dev :refer [show-options]]
            [window.editor-components
             :refer
             [
              table-editor
              create-entity-btn
              update-entity-btn
              delete-entity-btn
              entity-buttons
              ]]
            [window.component-tree :refer [form-tree, window-map]])
  (:import (javax.swing JFrame)))
(def east-content (vertical-panel
                    :border [5 "Compound" 10]
                    :items
                    [
                     (scrollable table-editor)
                     create-entity-btn
                     update-entity-btn
                     delete-entity-btn
                     ]))

(defn create-editor-window []
  (let [
        editor
        (frame :title "new window"
               :visible? true
               :content (border-panel
                          :items [
                                  [(horizontal-panel
                                     :border [5 "Items" 10]
                                     :items
                                     entity-buttons)
                                   :north]
                                  [(label
                                     :text "Label 2"
                                     :border [5 "Compound" 10])
                                   :south]
                                  [
                                   (scrollable east-content :hscroll :never)
                                   :east]
                                  [(scrollable (form-tree window-map)
                                               :maximum-size [640 :by 100]
                                               :hscroll :as-needed)
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
(show-options (scrollable east-content))