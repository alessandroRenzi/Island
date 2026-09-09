plugins {
    //Gradle plugin that aggregate coverage from submodules
    id("jacoco-report-aggregation")
    //kotlin
    kotlin("jvm") version "2.3.0" apply false
}

//repository for root
repositories {
    mavenCentral()
}

//general configuration
subprojects{
    group = "io.github.alessandrorenzi"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencies{
    //get the cover from both submodules
    jacocoAggregation(project(":IslandJava"))
    jacocoAggregation(project(":IslandKotlin"))
}

reporting {
    reports {
        create<JacocoCoverageReport>("testCodeCoverageReport") {
            testSuiteName = "test"
        }
    }
}