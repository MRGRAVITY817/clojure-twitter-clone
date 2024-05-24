(ns bluejay.api.applications.auth.auth-repository)

(defprotocol AuthRepository
  (create-account [_ email password]))
