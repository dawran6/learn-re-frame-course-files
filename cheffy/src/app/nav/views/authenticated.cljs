(ns app.nav.views.authenticated
  (:require [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box]]
            [app.nav.views.nav-item :refer [nav-item]]
            [app.router :as router]))

(defn authenticated
  []
  (let [active-nav @(rf/subscribe [:active-nav])
        nav-items [{:id :saved
                    :name "Saved"
                    :href (router/path-for :saved)
                    :dispatch #(rf/dispatch [:set-active-nav :saved])}
                   {:id :recipes
                    :name "Recipes"
                    :href (router/path-for :recipes)
                    :dispatch #(rf/dispatch [:set-active-nav :recipes])}
                   {:id :inboxes
                    :name "Inbox"
                    :href (router/path-for :inboxex)
                    :dispatch #(rf/dispatch [:set-active-nav :inboxes])}
                   {:id :become-a-chef
                    :name "Chef"
                    :href (router/path-for :become-a-chef)
                    :dispatch #(rf/dispatch [:set-active-nav :become-a-chef])}
                   {:id :profile
                    :name "Profile"
                    :href (router/path-for :profile)
                    :dispatch #(rf/dispatch [:set-active-nav :profile])}]]
    [:> Box {:display "flex"
             :justify-content "flex-end"
             :py 1}
     (for [{:keys [id name href dispatch]} nav-items]
       [nav-item {:key id
                  :id id
                  :name name
                  :href href
                  :dispatch dispatch
                  :active-nav active-nav}])]))
