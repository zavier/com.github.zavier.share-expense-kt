package com.github.zavier.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val age: Int)

fun Route.customerRouting() {
    route("/customer") {
        get {
            call.respond(User("zhangsan", 20))
        }
        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            call.respond(User("zhangsan", 24))
        }
        post {
            val customer = call.receive<User>()
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
    }
}