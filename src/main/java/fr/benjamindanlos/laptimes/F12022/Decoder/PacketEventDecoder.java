package fr.benjamindanlos.laptimes.F12022.Decoder;

import fr.benjamindanlos.laptimes.F12022.Handler.PacketEventHandler;
import fr.benjamindanlos.laptimes.F12022.Packets.PacketHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PacketEventDecoder extends MessageToMessageDecoder<DatagramPacket> {

	@Autowired
	private PacketEventHandler handler;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        ByteBuf buffer = datagramPacket.content();
		PacketHeader packetHeader = new PacketHeader().fill(buffer);
		handler.handlePacket(packetHeader, buffer);
    }
}
