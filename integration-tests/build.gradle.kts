plugins {
    alias(libs.plugins.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(project(":server"))
    testImplementation(libs.jsonrpc4j)
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

val matlabroot by properties
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    environment["DYLD_LIBRARY_PATH"] = "$matlabroot/bin/maca64"
}