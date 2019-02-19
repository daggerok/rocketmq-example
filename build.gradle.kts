/**
 * see https://docs.gradle.org/current/userguide/build_init_plugin.html
 *
 * gradle init --type=kotlin-application --dsl=kotlin --package=com.github.daggerok --project-name=rocketmq-example
 */

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.apache.rocketmq:rocketmq-client:4.4.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = System.getProperty("mainClass", "com.github.daggerok.AppKt")
}

defaultTasks("build", "installDist")

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}

tasks {
    "wrapper"(Wrapper::class) {
        gradleVersion = "5.2.1"
    }
}

// gradle tests output stdOut workaround
tasks {
    test {
        testLogging {
            showExceptions = true
            showStandardStreams = true
            useJUnitPlatform()
            useJUnit()
        }
    }
}
