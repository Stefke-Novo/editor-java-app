(ns window.editor-components
  (:require [seesaw.core :refer [table button config! scrollable]]
            [seesaw.dev :refer [show-options]]
            [functions.table :refer [change-table-content]]
            [functions.component-models :refer [button-model label-model text-field-model horizontal-panel-model vertical-panel-model simple-panel-model]]))

;table for editing components
(def table-editor (table :auto-resize :all-columns))
(show-options (table))
;button for creating component in window
(def create-entity-btn
  (button
    :text "Create component"
    :halign :left))
(def delete-entity-btn
  (button
    :text "Delete component"
    :halign :left))
(def update-entity-btn
  (button
    :text "Update component"
    :halign :left))

(def entity-buttons
  [
   (button
     :text "New button"
     :listen
     [
      :action
      (fn [_]
        (change-table-content table-editor button-model ["name" "value"]))
      ])
   (button :text "New label"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor label-model ["name" "value"]))
            ])
   (button :text "New text field"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor text-field-model ["name" "value"]))
            ])
   (button :text "New horizontal panel"
            :listen
            [
             :action
             (fn [_]
               (change-table-content table-editor horizontal-panel-model ["name" "value"])
               )
             ])
   (button :text "New vertical panel"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor vertical-panel-model ["name" "value"]))
            ])
   (button :text "New simple panel"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor simple-panel-model ["name" "value"]))
            ])
  ])
