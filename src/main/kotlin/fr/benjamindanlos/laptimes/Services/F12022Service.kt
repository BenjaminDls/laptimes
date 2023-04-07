package fr.benjamindanlos.laptimes.Services

import fr.benjamindanlos.laptimes.Observer.Observer
import fr.benjamindanlos.laptimes.Observer.Subject
import fr.benjamindanlos.laptimes.UDP.PacketHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class F12022Service(
	@Value("\${game.port.f12022}")
	var port: Int,
	@Value("\${listenAddress}")
	val address: String
): Observer {
	override fun update(subject: Subject) {
		convertData(subject.getData())
	}

	final val packetHandler = PacketHandler(port, address)

	init{
		packetHandler.subscribe(this)
		packetHandler.startListening()
	}

	fun convertData(bytes: ByteArray?){
		System.out.println("received : "+bytes?.size)
	}
}
