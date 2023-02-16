plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    implementation(projects.data.userpreferences.public)
    implementation(libs.javax.inject)
}
