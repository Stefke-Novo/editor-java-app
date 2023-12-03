(ns window.editor-components
  (:require [functions.component-models :refer [button-model horizontal-panel-model label-model simple-panel-model text-field-model vertical-panel-model return-component form-model]]
            [functions.table :refer [change-table-content create-table get-data-from-table exclude-null]]
            [seesaw.core :refer [button]]
            [window.component-tree :refer [form-model-from-tree form-tree form-window get-selected-node-content update-selected-node window-map add-to-selected-node delete-selected-node]])
  (:import (clojure.lang MapEntry)))

;table for editing components
(def table-editor (create-table label-model))
(.getSize (create-table label-model))
(get-data-from-table table-editor)
(.getSize table-editor)
(.getSize table-editor)
(def tree-object (form-tree window-map))

;button for creating component in window
(def create-entity-btn
  (button
    :text "Create component"
    :halign :left
    :listen [:action (fn [_] (add-to-selected-node tree-object (assoc (return-component (get-data-from-table table-editor)) :model (exclude-null table-editor))))]))

(def delete-entity-btn
  (button
    :text "Delete component"
    :halign :left
    :listen [:action (fn [_] (delete-selected-node tree-object))]))
(defn map-entry [v]
  (MapEntry/create (first v) (last v)))
(def update-entity-btn
  (button
    :text "Update component"
    :halign :left
    :listen [:action (fn [_]
                       (update-selected-node tree-object (assoc (get-selected-node-content tree-object) :model (into {} (mapv map-entry (exclude-null table-editor))))))]))

(def read-entity-btn
  (button :text "Get entity from model"
          :halign :left
          :listen [:action (fn [_]
                             (change-table-content table-editor (form-model (get-selected-node-content tree-object))))]))
(def show-window-btn
  (button :text "Show window"
          :halign :left
          :listen [:action (fn [_] (eval (form-window (form-model-from-tree (.getRoot (.getModel tree-object))))))]))

(def entity-buttons
  [
   (button
     :text "New button"
     :listen
     [
      :action
      (fn [_]
        (change-table-content table-editor button-model))
      ])
   (button :text "New label"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor label-model))
            ])
   (button :text "New text field"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor text-field-model))
            ])
   (button :text "New horizontal panel"
            :listen
            [
             :action
             (fn [_]
               (change-table-content table-editor horizontal-panel-model)
               )
             ])
   (button :text "New vertical panel"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor vertical-panel-model))
            ])
   (button :text "New simple panel"
           :listen
           [
            :action
            (fn [_]
              (change-table-content table-editor simple-panel-model))
            ])
  ])

(contains? {:a 1} :a)
