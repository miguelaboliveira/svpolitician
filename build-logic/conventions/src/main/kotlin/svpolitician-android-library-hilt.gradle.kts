import com.miguelaboliveira.svpolitician.buildlogic.configureHilt

plugins {
    id("svpolitician-android-library")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

configureHilt()
