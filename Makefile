help: ## Prints help for targets with comments
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

build-project: ## Builds project
	./gradlew build

build-chiseled: ## Builds project
	./gradlew chiseledBuild

refresh: ## Refresh dependencies
	./gradlew --refresh-dependencies

clean-cache: ## Cleans cache
	./gradlew --stop
	rm -rf $GRADLE_HOME/caches/transforms-*
	rm -rf $GRADLE_HOME/caches/build-cache-*
	./gradlew clean

stop: ## Stops all deamons
	./gradlew --stop

gen-sources: ## Generate sources
	./gradlew genSources

run-fabric-client: ## Runs fabric client
	./gradlew neoforge:1.21.4:runClient

run-forge-client: ## Runs forge client
	./gradlew forge:runClient

run-neoforge-client: ## Runs neoforge client
	./gradlew neoforge:runClient

run-fabric-server: ## Runs fabric server
	./gradlew fabric:runServer

run-forge-server: ## Runs forge server
	./gradlew forge:runServer

run-neoforge-server: ## Runs neoforge server
	./gradlew neoforge:runServer