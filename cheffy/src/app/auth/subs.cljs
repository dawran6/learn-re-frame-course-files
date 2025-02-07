(ns app.auth.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :logged-in?
 (fn [db _]
   (boolean (get-in db [:auth :uid]))))

(reg-sub
 :user-profile
 (fn [db _]
   (let [uid (get-in db [:auth :uid])]
     (get-in db [:users uid :profile]))))

(reg-sub
 :user
 (fn [db _]
   (let [uid (get-in db [:auth :uid])]
     (get-in db [:users uid]))))

(reg-sub
 :chef?
 (fn [db _]
   (let [uid (get-in db [:auth :uid])]
     (= :chef (get-in db [:users uid :role])))))

(reg-sub
 :user-image
 (fn [db [_ uid]]
   (get-in db [:users uid :profile :img])))
