plugins {
    application
}

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
                rename("Application", "QuickStart")
                into("src")
            }
            from(configurations["runtimeClasspath"]) {
                into("lib")
            }
            from("bin") {
                include("Application.sh")
                rename("Application", "QuickStart")
                into("bin")
            }
        }
    }
}

tasks.named<Zip>("scriptsDistZip") {
    archiveFileName.set("QuickStart.zip")
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("--source", "22", "--enable-preview")
}

application {
    mainClass.set("src/main/java/Application.java")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
