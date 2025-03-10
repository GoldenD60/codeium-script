plugins {
    id("java")
}

group = "org.goldend60.codeiumscript"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.12.1")
    implementation("org.antlr:antlr4-runtime:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}