plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.androidx.navigationSafeArgs)
    implementation(libs.plugin.dagger.hilt)
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.kotlinter)
    implementation(libs.plugin.square.sqlDelight)
    implementation(libs.plugin.twitter.compose)
}
