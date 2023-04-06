package fr.benjamindanlos.laptimes.Exceptions

import org.springframework.http.HttpStatus
class NotFoundException(_msg: String): LaptimesException(_msg){
	override fun getHttpCode(): HttpStatus {
		return HttpStatus.NOT_FOUND
	}
}
