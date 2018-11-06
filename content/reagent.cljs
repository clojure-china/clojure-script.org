
(ns example
  (:require [reagent.core :as r]))
(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "]
    "text."]])

(defn render-simple []
  (r/render [simple-component]
    (.-body js/document)))
