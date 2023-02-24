plugins {
    id("svpolitician-jvm-library")
}

dependencies {
    implementation(projects.core.userpreferences.public)
    implementation(libs.javax.inject)
}
