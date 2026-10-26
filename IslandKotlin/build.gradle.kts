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
    // Source: https://mvnrepository.com/artifact/com.google.jimfs/jimfs
    testImplementation("com.google.jimfs:jimfs:1.3.1")
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