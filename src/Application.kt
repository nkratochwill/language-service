package com.nkratochwill.language_service

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/langcheck") {
            val text = call.request.queryParameters["text"] ?: ""
            if(text=="")call.respond(emptyMap<String,Any>())
                else {
                val result = withContext(Dispatchers.IO) { languagecheck(text) }
                call.respond(result)
            }
        }

    }
}

