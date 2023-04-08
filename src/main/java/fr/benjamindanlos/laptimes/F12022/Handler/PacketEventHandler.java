/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import fr.benjamindanlos.laptimes.F12022.Enums.PacketId;
import fr.benjamindanlos.laptimes.F12022.Packets.Packet;

public class PacketEventHandler extends SimpleChannelInboundHandler<Packet> {

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
		System.out.println("received packet : "+PacketId.valueOf(msg.getHeader().getPacketId().getValue()));
    }

}
