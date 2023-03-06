object DataDependencies {

    object Room {
        private const val version = "2.4.2"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val runtime = "androidx.room:room-runtime:$version"
        const val paging = "androidx.room:room-paging:$version"
    }

    object SquareUp {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val converter = "com.squareup.retrofit2:converter-gson:2.6.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.0"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    }

}