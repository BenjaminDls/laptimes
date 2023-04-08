/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Handler;

import fr.benjamindanlos.laptimes.F12022.Packets.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PacketEventHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
	{
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet decodedPacket) throws Exception
	{
		//do stuff with the data
		System.out.println("received packet : "+ decodedPacket.toString());
    }

}
