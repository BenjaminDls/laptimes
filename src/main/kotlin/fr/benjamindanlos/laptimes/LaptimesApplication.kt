package fr.benjamindanlos.laptimes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LaptimesApplication

fun main(args: Array<String>) {
	runApplication<LaptimesApplication>(*args)
}
