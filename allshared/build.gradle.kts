plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kmmbridge)
    alias(libs.plugins.skie)
    alias(libs.plugins.cocoapods)
    `maven-publish`
}

kotlin {

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":breeds"))
            api(project(":analytics"))
        }
    }

    cocoapods {
        summary = "KMMBridgeCocoaPodsQuickStart"
        homepage = "https://github.com/hu5980/KMMBridgeCocoaPodsQuickStart"
        ios.deploymentTarget = "13.5"
        version = "0.2.0"
        extraSpecAttributes["libraries"] = "'c++', 'sqlite3'"
        license = "BSD"
        extraSpecAttributes["swift_version"] = "\"5.0\"" // <- SKIE Needs this!
        framework {
            export(project(":analytics"))
            isStatic = true
        }
    }
}

addGithubPackagesRepository()

kmmbridge {
    mavenPublishArtifacts()
    // Must be the SSH url
    cocoapods("git@github.com:hu5980/HYSpec.git")
}