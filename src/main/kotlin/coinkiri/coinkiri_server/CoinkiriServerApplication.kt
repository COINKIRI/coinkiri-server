package coinkiri.coinkiri_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoinkiriServerApplication

fun main(args: Array<String>) {
	runApplication<CoinkiriServerApplication>(*args)
}
