plugins {
    //Gradle plugin that aggregate coverage from submodules
    id("jacoco-report-aggregation")
    //Coveralss plugin
    id("com.github.nbaztec.coveralls-jacoco") version "1.2.20"
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

coverallsJacoco{
    //Pointing to Jacoco xml report for coveralls
    reportPath ="${layout.buildDirectory.get()}/reports/jacoco/testCodeCoverageReport/testCodeCoverageReport.xml"
}