package com.fakhrirasyids.chemu

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform