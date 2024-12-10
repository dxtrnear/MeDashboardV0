plugins {
    kotlin("jvm") version "1.8.20"
    id("org.jetbrains.compose") version "1.4.0"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Compose Desktop
    implementation(compose.desktop.currentOs)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
    implementation(compose.ui)
    implementation(compose.preview)

    // Kotlin standard library
    implementation(kotlin("stdlib"))

    // PDF Generation
    implementation("com.itextpdf:itext7-core:7.2.3")

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("junit:junit:4.13.2")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}