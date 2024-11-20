plugins {
    id("java")
    id("com.google.cloud.tools.jib") version("3.4.4")
    id("io.sentry.jvm.gradle") version("4.13.0")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.dropwizard:dropwizard-bom:4.0.10"))
    implementation("io.dropwizard:dropwizard-core")
    implementation("io.dropwizard:dropwizard-json-logging")

    implementation("ru.vyarus:dropwizard-guicey:7.1.4")
    implementation("com.smoketurner:dropwizard-swagger:4.0.5-1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

jib {
    extraDirectories.setPaths("config/")
    to {
        image = "nathanduckett/${name}:latest"
        tags = setOf("latest", version.toString())
    }
    container {
        args = listOf("server", "service.yml")
    }
}

sentry {
    // Generates a JVM (Java, Kotlin, etc.) source bundle and uploads your source code to Sentry.
    // This enables source context, allowing you to see your source
    // code as part of your stack traces in Sentry.
    includeSourceContext = true

    org = "nduckett"
    projectName = name
    authToken = System.getenv("SENTRY_AUTH_TOKEN")
}
