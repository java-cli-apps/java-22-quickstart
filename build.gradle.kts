plugins {
    application
}

val STARTER_APP = "Application"
val APP_NAME = System.getenv("APP_NAME")

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.picocli)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

distributions {
    create("scripts") {
        contents {
            from("src/main/java") {
                include("**/*.java")
                rename(STARTER_APP, APP_NAME)
                into("src")
            }
            from(configurations.runtimeClasspath) {
                into("lib")
            }
            from("bin") {
                include("*.sh")
                rename(STARTER_APP, APP_NAME)
                into("bin")
            }
        }
    }
}

tasks.named<Zip>("scriptsDistZip") {
    archiveFileName.set("${APP_NAME}.zip")
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("--source", "22", "--enable-preview")
}

application {
    mainClass.set("src/main/java/${STARTER_APP}.java")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
