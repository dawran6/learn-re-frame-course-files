(ns app.nav.views.nav-item
  (:require ["@smooth-ui/core-sc" :refer [Box]]))

(defn nav-item
  [{:keys [id href name] :as item}]
  (println item)
  [:> Box {:key id
           :as "a"
           :href href
           :ml 2
           :pb 10}
   name])
