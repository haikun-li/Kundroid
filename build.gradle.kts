// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") apply false
    id ("com.android.library")  apply false
    id ("org.jetbrains.kotlin.android")  apply false
    id ("dagger.hilt.android.plugin")  apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}