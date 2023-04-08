package fr.benjamindanlos.laptimes.UDP;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import jakarta.annotation.PreDestroy;
import java.net.InetSocketAddress;

public class UDP {
	private final EventLoopGroup group;
	private final Bootstrap bootstrap;

	public UDP(InetSocketAddress address, ChannelHandler... handlers) {
		this.group = new NioEventLoopGroup();
		this.bootstrap = new Bootstrap();
		this.bootstrap.group(group)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler( new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel channel)
							throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						for(ChannelHandler handler : handlers){
							pipeline.addLast(handler);
						}
					}
				} )
				.localAddress(address);
/*		Channel channel = bind();
		try {
			channel.closeFuture().sync();
		}
		catch (InterruptedException e){
			System.out.println(e);
			stop();
		}*/
	}

	public Channel bind() {
		System.out.println("Listening NOW !!");
		return this.bootstrap.bind().syncUninterruptibly().channel();
	}

	public void stop() {
		this.group.shutdownGracefully();
	}

	@PreDestroy
	protected void destroy() {
		stop();
	}
}
