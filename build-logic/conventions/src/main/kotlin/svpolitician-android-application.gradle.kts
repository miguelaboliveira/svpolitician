import com.miguelaboliveira.svpolitician.buildlogic.configureAndroidCommon
import com.miguelaboliveira.svpolitician.buildlogic.configureCompose
import com.miguelaboliveira.svpolitician.buildlogic.configureHilt
import com.miguelaboliveira.svpolitician.buildlogic.configureKotlin
import com.miguelaboliveira.svpolitician.buildlogic.libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jmailen.kotlinter")
}

configureKotlin()
configureAndroidCommon()
configureCompose()
configureHilt()

android {
    defaultConfig {
        targetSdk =
            libs
                .findVersion("android.targetSdk")
                .get()
                .toString()
                .toInt()
    }
}
