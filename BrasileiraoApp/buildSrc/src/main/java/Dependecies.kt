object Kotlin {
    object Versions {
        const val kotlin = "1.4.10"
    }

    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}

object Versions {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
}

object Lottie {
    object Versions {
        const val lottie = "3.4.4"
    }

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
}

object JUnit {
    object Versions {
        const val junit = "4.12"
    }

    const val core = "junit:junit:${Versions.junit}"
}

object Stetho {
    object Versions {
        const val stetho = "1.4.1"
    }

    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
}

object Room {
    object Versions {
        const val room = "2.2.5"
    }

    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val test = "androidx.room:room-testing:${Versions.room}"

}

object OkHttp {
    object Versions {
        const val okhttp = "3.14.9"
    }

    const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val mockWebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}

object Gson {
    object Versions {
        const val gson = "2.8.5"
    }

    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Glide {
    object Versions {
        const val glide = "4.11.0"
    }

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Coroutines {
    object Versions {
        const val coroutines = "1.3.9"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object Koin {
    object Versions {
        val koin = "2.1.6"
    }

    val core = "org.koin:koin-android:${Versions.koin}"
    val koinLifecycle = "org.koin:koin-android-scope:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
}


object Retrofit {
    object Versions {
        const val retrofit = "2.9.0"
    }

    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}

object Lifecycle {
    object Versions {
        const val lifecycle = "2.2.0"
        const val compiler = "2.2.0"
        const val extensions = "1.1.1"
        const val lifecycleTesting = "2.1.0"
        const val androidArchVersion = "2.2.0"
        const val navigation = "2.3.0"
    }

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val extensions = "android.arch.lifecycle:extensions:${Versions.extensions}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val compiler = "android.arch.lifecycler:compiler:${Versions.lifecycle}"
    const val test = "androidx.arch.core:core-testing:${Versions.lifecycleTesting}"
}

object AndroidX {
    object Versions {
        const val ktx = "1.3.1"
        const val appcompat = "1.2.0"
        const val design = "1.1.0"
        const val recyclerView = "1.1.0"
        const val swipeRefreshLayout = "1.1.0"
        const val transition = "1.3.1"
        const val annotation = "1.1.0"
        const val fragment = "1.2.5"
        const val viewPager = "1.0.0"
        const val collection = "1.1.0"
        const val constraintLayout = "2.0.1"
        const val databind = "3.5.0"
    }

    const val databindCompiler = "androidx.databinding:databinding-compile:${Versions.databind}"

    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val transition = "androidx.transition:transition:${Versions.transition}"
    const val annotations = "androidx.annotation:annotation:${Versions.annotation}"

    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val viewPager = "androidx.viewpager:viewpager:${Versions.viewPager}"

    const val collection = "androidx.collection:collection:${Versions.collection}"
}

object AndroidXTest {
    object Versions {
        const val test = "1.2.0"
        const val junitExt = "1.1.1"
        const val espresso = "3.2.0"
        const val junit = "4.12"
        const val swipeLayout = "1.1.0"
    }

    const val swipeLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeLayout}"

    const val junit = "junit:junit:${Versions.junit}"
    const val ext = "androidx.test.ext:junit:${Versions.junitExt}"
    const val rules = "androidx.test:rules:${Versions.test}"
    const val runner = "androidx.test:runner:${Versions.test}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val orchestrator = "androidx.test:orchestrator:${Versions.test}"
}

object GradlePlugins {
    object Versions {
        const val spotlessPlugin = "5.4.0"
    }

    const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotlessPlugin}"

}

object MockK {
    object Versions {
        const val mockk = "1.9.2"
        const val faker = "1.4.1"
    }

    const val core = "io.mockk:mockk:${Versions.mockk}"
    const val android = "io.mockk:mockk-android:${Versions.mockk}"
    const val faker = "io.github.serpro69:kotlin-faker:${Versions.faker}"
}

object CheckStyle {
    object Versions {
        const val ktlin = "0.39.0"
    }

    const val ktlin = "com.pinterest:ktlint:${Versions.ktlin}"
}
