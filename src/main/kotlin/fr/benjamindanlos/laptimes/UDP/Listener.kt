package fr.benjamindanlos.laptimes.UDP

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioDatagramChannel

class Listener(private val port: Int, private var address: String)
{

	@Throws(Exception::class)
	fun run(handler: PacketHandler) {
		val group = NioEventLoopGroup()
		try {
			val b = Bootstrap()
			b.group(group)
				.channel(NioDatagramChannel::class.java)
				.option(ChannelOption.SO_BROADCAST, false)
				.handler(handler)

			// Bind and start to accept incoming connections.
			System.out.printf(
				"waiting for message %s:%s\n",
				String.format(address),
				String.format(port.toString())
			)
			b.bind(address, port).sync().channel().closeFuture().await()
		}
		finally {
			print("In Server Finally")
		}
	}

}
