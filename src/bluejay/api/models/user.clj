(ns bluejay.api.models.user)

(def ^:private user-db-name "users")
(def ^:private user-schema
  [{:db/ident       :user/email
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :user/password
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one}

   {:db/ident       :user/username
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity}

   {:db/ident       :user/created-at
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one}

   {:db/ident       :user/updated-at
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one}])

(def user-db-spec {:db-name user-db-name
                   :schema  user-schema})
