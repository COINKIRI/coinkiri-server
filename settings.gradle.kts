plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "coinkiri-server"
include("coinkiri-api")
include("coinkiri-core")
include("coinkiri-common")
