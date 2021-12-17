plugins{
    id("groovy")
}

repositories {
    google()
    mavenCentral()
}



dependencies {
    implementation(libs.build.gradle)
    implementation(libs.build.kotlin)
    implementation(gradleApi())
    implementation(localGroovy())
}