package fr.benjamindanlos.laptimes

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration(exclude = [SecurityAutoConfiguration::class])
@ComponentScan
class LaptimesApplication

fun main(args: Array<String>) {
	//System.out.println(InetAddress.getLocalHost())
	//val listener = Listener(9999)
	//listener.run()
	runApplication<LaptimesApplication>(*args)
}
