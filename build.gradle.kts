import java.lang.Boolean

plugins {
    id("java")
    id("groovy")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.spockframework:spock-core:2.1-groovy-3.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = Boolean.getBoolean("all.logs")
        events(
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
        )
    }
}

sourceSets["main"].resources {
    srcDir("../gradle-includes")
    include("config/*")
}