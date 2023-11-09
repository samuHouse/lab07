plugins {
    java
    id("org.danilopianini.gradle-java-qa") version "1.22.1"
}

repositories {
    mavenCentral()
}

dependencies {
    var junitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
    testLogging.showStandardStreams = true
}

tasks.javadoc {
    isFailOnError = false
}
