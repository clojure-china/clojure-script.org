
(def m2 (js/Microsoft.Maps.Themes.BingTheme.))

(def my-object (js-obj "a" 1 "b" true "c" nil))

(def js-object #js {:a 1 :b 2})

(def my-array (js->clj (.-globalArray js/window)))
(def first-item (get my-array 0)) ;; 1

(def my-obj (js->clj (.-globalObject js/window)))
(def a (get my-obj "a")) ;; 1
