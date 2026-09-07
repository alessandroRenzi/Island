plugins {
    id("java")
}

group = "io.github.alessandrorenzi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Junit 5
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //Private utility for checking and file manipulation
    implementation("commons-io:commons-io:2.16.1")
}

tasks.test {
    useJUnitPlatform()
}