help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; { printf("\033[36m%-30s\033[0m %s\n", $$1, $$2) }'

package: ## Packager l'application dans un fichier .zip
	./gradlew scriptsDistZip

install: .check-install-dir ## Installer le package de l'application
	unzip -q -d $(DEST_DIR) $(BUILD)/distributions/$(APP_NAME).zip

test: ## Tester l'application localement
	./gradlew run --args="--language French"

test-install: .check-install-dir ## Tester l'application installée
	PATH=$(DEST_DIR)/$(APP_DIR)/bin:$(PATH) $(APP_NAME).sh --language French

clean: ## Nettoyer le répertoire de build
	rm --force --recursive $(BUILD)

.check-install-dir:
	@test -n '$(DEST_DIR)' || { \
		echo "You need to specify an installation directory and an application name, for example 'DEST_DIR=~ APP_NAME=MyCmdLine make install'"; \
		exit 1; \
	}

APP_NAME := $(or $(APP_NAME), QuickStart)
APP_DIR := $(APP_NAME)
BUILD := build
