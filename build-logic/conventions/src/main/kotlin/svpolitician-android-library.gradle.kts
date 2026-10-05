import com.miguelaboliveira.svpolitician.buildlogic.configureAndroidCommon
import com.miguelaboliveira.svpolitician.buildlogic.configureKotlin
import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("com.android.library")
    id("org.jmailen.kotlinter")
}

configureKotlin()
configureAndroidCommon()

dependencies {
    implementation(libs.findLibrary("kotlinx.coroutinesAndroid").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
    androidTestImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
}
