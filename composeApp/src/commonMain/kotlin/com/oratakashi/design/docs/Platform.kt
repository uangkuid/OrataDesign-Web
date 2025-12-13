package com.oratakashi.design.docs

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform