## Bluejay - Twitter Clone made with Clojure

Uses Clojure, Ring, Reitit, Integrant, Datomic Local.

## How to start

1. Install Clojure CLI
2. Run `clj -M:dev` to start the dev REPL.
3. Run `(go)` to start the server.

## How to do "reloaded workflow"

This project manages system dependencies with Integrant. Just like Stuart Sierra's Component, Integrant allows you to update the system without restarting the REPL. This is called ["reloaded workflow"](https://cognitect.com/blog/2013/06/04/clojure-workflow-reloaded).

Here's how you can do it:
1. When your dev REPL is running, head to `dev/user.clj` file.
2. Run `(go)` inside comment form to start the server.
3. Now, you can make changes to the system dependencies (e.g. ports, database connections, etc.) and run `(reset)` to reload the server.


## References

I got tons of tips from these wonderful resources:
1. [Sean Corfield's User Manager Example](https://github.com/seancorfield/usermanager-example?tab=readme-ov-file)
2. [Michaël Salihi's User Manager Example](https://github.com/prestancedesign/usermanager-reitit-example)
3. ["Abstract Clojure" article from Alex King](https://www.juxt.pro/blog/abstract-clojure/)
4. ["DDD Layered architecture in Clojure: A first try" article from Freek Paans](https://www.freekpaans.nl/2016/03/layered-architecture-in-clojure/)

