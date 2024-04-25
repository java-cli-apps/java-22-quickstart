.DEFAULT_GOAL := help

package: .check-app-name ## Packager l'application dans un fichier .zip
	./gradlew scriptsDistZip

install: .check-app-name .check-install-dir ## Installer le package de l'application
	unzip -q -d $(DEST_DIR) $(BUILD)/distributions/$(APP_NAME).zip

test: .check-app-name ## Tester l'application localement
	./gradlew run --args="--language French"

test-install: .check-app-name .check-install-dir ## Tester l'application installée
	PATH=$(DEST_DIR)/$(APP_DIR)/bin:$(PATH) $(APP_NAME).sh --language French

clean: ## Nettoyer le répertoire de build
	rm --force --recursive $(BUILD)

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| sort \
		| awk 'BEGIN {FS = ":.*?## "}; { printf("\033[36m%-30s\033[0m %s\n", $$1, $$2) }'

.check-app-name:
ifndef APP_NAME
	$(error Please set the application name with the APP_NAME variable)
endif

.check-install-dir:
ifndef DEST_DIR
	$(error Please set the installation directory, for example DEST_DIR=~ make install)
endif

APP_DIR := $(APP_NAME)
BUILD := build
