package fr.benjamindanlos.laptimes.Exceptions

import org.springframework.http.HttpStatus

abstract class LaptimesException(val msg: String): RuntimeException(){
	abstract fun getHttpCode(): HttpStatus
	fun getMessages(): String { return msg }
}
