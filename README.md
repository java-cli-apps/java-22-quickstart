# Un template d'Application en Ligne de Commande (CLI) utilisant Java 22 et la JEP 458

Le template **java-22-quickstart** permet de démarrer une nouvelle application en commande ligne avec Java et Gradle.

Ce template nécessite d'utiliser Java 22, ou une version supérieure, car il utilise la [JEP 458](https://openjdk.org/jeps/458) 
qui permet de lancer plusieurs fichiers sources sans les compiler préalablement.

Le package de l'application comprend les sources ainsi que ses dépendances.

Pour démarrer une nouvelle application en utilisant ce template, vous pouvez suivre les étapes suivantes.

## Changer le nom de l'application

Pour changer le nom de l'application, initialement nommée _Quickstart_, il faut modifier la variable APP_NAME dans
le fichier `.envrc`, par exemple comme suit :

## Lancer l'application localement

```bash
make test
```

```console
./gradlew run --args="--language French"
Bonjour 🇫🇷
```

## Construire le package de l'application

```bash
make package
```

```console
./gradlew scriptsDistZip
```

### Shell de lancement

Le script de lancement [Application.sh](bin/Application.sh), dont le rôle est de lancer le fichier
[Application.java](src/main/java/Application.java), est renommé lors de la construction du package en `MyCmdLine.sh`.

Cela permet d'ajouter plusieurs applications dans le `PATH` et donc d'invoquer directement `MyCmdLine.sh`.

## Installer l'application

```bash
DEST_DIR=/home/user make install
```

```console
unzip -q -d /home/user build/distributions/QuickStart.zip
```

## Lancer l'application installée

```bash
DEST_DIR=/home/user make test-install
```

```console
PATH=/home/user/QuickStart/bin:/usr/lib/jvm/jdk-22/bin:/home/fopy/.local/bin:... QuickStart.sh --language French
Bonjour 🇫🇷
```

Il ne nous reste plus qu'à :

- Implémenter notre métier dans [Application.java](src/main/java/Application.java)
- Ajouter dans le répertoire [src/main/java](src/main/java) les fichiers qui déclarent les classes utilisées par `Application.java`
- Ajouter les caractéristiques de nos dépendances dans [gradle/libs.versions.toml](gradle/libs.versions.toml) et [build.gradle.kts](build.gradle.kts)

## Autres templates

Si vous n'avez pas Java 22, vous pouvez utiliser le template [basic-java-quickstart](https://github.com/java-cli-apps/basic-java-quickstart)
qui ne requiert que la version 11 de Java. Il est alors nécessaire que tout le code Java réside dans le même fichier
comme décrit dans la [JEP 330](https://openjdk.org/jeps/330).
