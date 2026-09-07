plugins {
    kotlin("jvm")
    id("jacoco")
}


repositories {
    mavenCentral()
}

kotlin{
    jvmToolchain(21)
}
dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}