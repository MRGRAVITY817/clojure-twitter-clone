(ns bluejay.api.applications.repo.user-repo)

(defprotocol UserRepository
  (create-user [_ email password-hash username])
  (get-users [_]))
