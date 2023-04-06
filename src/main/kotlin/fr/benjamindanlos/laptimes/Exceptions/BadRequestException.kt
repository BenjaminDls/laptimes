package fr.benjamindanlos.laptimes.Exceptions

import org.springframework.http.HttpStatus
class BadRequestException(_msg: String): LaptimesException(_msg){
	override fun getHttpCode(): HttpStatus {
		return HttpStatus.BAD_REQUEST
	}
}
