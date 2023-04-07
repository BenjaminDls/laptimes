package fr.benjamindanlos.laptimes.UDP

import fr.benjamindanlos.laptimes.Observer.Observer
import fr.benjamindanlos.laptimes.Observer.Subject
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.socket.DatagramPacket
import lombok.Getter
import kotlin.concurrent.thread

class PacketHandler(@Getter val port: Int, address: String)
		: Subject, SimpleChannelInboundHandler<DatagramPacket>(){
	var lastData: ByteArray? = null
	var listener: Listener? = null
	override var subscribed: ArrayList<Observer> = ArrayList()

	override fun channelRead0(ctx: ChannelHandlerContext?, msg: DatagramPacket?) {
		if(msg==null)throw IllegalArgumentException("msg")

		val buf = msg.content()
		val rcvPktLength = buf.readableBytes()
		val rcvPktBuf = ByteArray(rcvPktLength)
		buf.readBytes(rcvPktBuf)
		//System.out.println("received:\""+String(rcvPktBuf, StandardCharsets.UTF_8)+"\"")
		lastData=rcvPktBuf
		notif()
	}
	fun startListening(){
		thread(start = true) {
			listener?.run(this)
		}
	}

	override fun getData(): ByteArray? {
		return lastData;
	}

	init {
	    listener = Listener(port, address)
	}

}
