(ns sablono-conditional-bug.app
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(defn objekto [data owner]
  (reify
    om/IRender
    (render [_]
      (html [:p
             [:em "La datumoj estas " (pr-str data)]
             [:strong ", kaj la stato estas " (om/get-shared owner :foo)]]))))

(defn widget [data owner]
  (reify
    om/IRender
    (render [_]
      (html [:div
             (if (= [1 2 3] (vec (range 1 4)))
               (for [n (range 1 4)]
                 (om/build objekto (assoc data :n n) {:key :n}))
               [:h1 "vi estas konfuza"])]))))

(om/root widget
         {}
         {:target (. js/document (getElementById "app-container"))
          :shared {:foo "mi estas akcia stato"}})
