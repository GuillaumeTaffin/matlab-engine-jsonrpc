plugins {
    alias(libs.plugins.jvm)
    application
}

repositories {
    mavenCentral()
}

val matlabroot: String by properties

dependencies {
    implementation("com.github.briandilley.jsonrpc4j:jsonrpc4j:1.6")
    implementation(files("$matlabroot/extern/engines/java/jar/engine.jar"))

    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

application {
    mainClass = "org.example.AppKt"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    environment["DYLD_LIBRARY_PATH"] = "$matlabroot/bin/maca64"
}

tasks.register<Exec>("startMatlab") {
    val matlab = providers.gradleProperty("matlabroot").map { "$it/bin/matlab" }
    commandLine(matlab.get(), "-desktop")

    doLast {
        Thread.sleep(5000)
    }
}
