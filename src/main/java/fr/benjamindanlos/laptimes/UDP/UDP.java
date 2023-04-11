package fr.benjamindanlos.laptimes.UDP;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class UDP {
	private final EventLoopGroup group;
	private final Bootstrap bootstrap;

	public UDP(InetSocketAddress address, ChannelHandler... handlers) {
		this.group = new NioEventLoopGroup();
		this.bootstrap = new Bootstrap();
		this.bootstrap.group(group)
				.channel(NioDatagramChannel.class)
				.option(ChannelOption.SO_BROADCAST, true)
				.handler( new ChannelInitializer<>() {
					@Override
					protected void initChannel(Channel channel){
						ChannelPipeline pipeline = channel.pipeline();
						for(ChannelHandler handler : handlers){
							pipeline.addLast(handler);
						}
					}
				} )
				.localAddress(address);
	}

	public Channel bind() {
		log.info("Listening on "+bootstrap.config().localAddress());
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
