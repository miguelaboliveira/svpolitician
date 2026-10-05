import com.miguelaboliveira.svpolitician.buildlogic.configureKotlin
import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jmailen.kotlinter")
}

configureKotlin()

dependencies {
    implementation(libs.findLibrary("kotlinx.coroutinesCore").get())
    testImplementation(libs.findLibrary("kotlinx.coroutinesTest").get())
    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("kotlin.test").get())
    testImplementation(libs.findLibrary("kotlin.testJunit").get())
}
