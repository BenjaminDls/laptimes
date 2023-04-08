package fr.benjamindanlos.laptimes.AssettoCorsa.Handler;

import fr.benjamindanlos.laptimes.F12022.Enums.PacketId;
import fr.benjamindanlos.laptimes.AssettoCorsa.Data.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DataHandler extends SimpleChannelInboundHandler<Packet> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception
	{
		//do stuff with the data
		System.out.println("received packet : "+ msg.getClass());
	}
}
