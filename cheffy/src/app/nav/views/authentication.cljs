(ns app.nav.views.authentication
  (:require ["@smooth-ui/core-sc" :refer [Box]]
            [app.nav.views.nav-item :refer [nav-item]]))

(defn authenticated
  []
  (let [nav-items [{:id :saved
                    :name "Saved"
                    :href "#saved"}
                   {:id :recipes
                    :name "Recipes"
                    :href "#recipes"}
                   {:id :inbox
                    :name "Inbox"
                    :href "#inbox"}
                   {:id :profile
                    :name "Profile"
                    :href "#profile"}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1}
     (for [{:keys [id] :as item} nav-items]
       ^{:key id} [nav-item item])]))
