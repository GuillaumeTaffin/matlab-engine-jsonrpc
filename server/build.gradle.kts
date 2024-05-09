plugins {
    alias(libs.plugins.jvm)
    application
}

repositories {
    mavenCentral()
}

val matlabroot: String by properties

dependencies {
    implementation(libs.jsonrpc4j)
    implementation(libs.javax.servlet)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.0")
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
    mainClass = "com.gt.matlab.engine.rpc.ServerKt"
    applicationDefaultJvmArgs = listOf("-Djava.library.path=$matlabroot/bin/maca64")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    environment["DYLD_LIBRARY_PATH"] = "$matlabroot/bin/maca64"
}

tasks.register<Exec>("startMatlab") {
    val matlab = providers.gradleProperty("matlabroot").map { "$it/bin/matlab" }
    commandLine(matlab.get(), "-desktop")
}
