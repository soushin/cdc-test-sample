package pact_kotlin_provider

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author nsoushi
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping(value = "user")
open class UserController {

    @RequestMapping(value = "/{id}", method = arrayOf(RequestMethod.GET))
    open fun getContentList(@PathVariable id: Int): ResponseEntity<User> {
        return ResponseEntity(User("1192-User"), HttpStatus.OK)
    }
}

data class User(
        @JsonProperty("Name")
        val name: String
)