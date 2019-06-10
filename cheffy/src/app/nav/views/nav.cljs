(ns app.nav.views.nav
  (:require [app.nav.views.authentication :refer [authenticated]]))

(defn nav
  []
  (let [user true]
    (if user
      authenticated
      "public")))
