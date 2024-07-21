package fr.benjamindanlos.laptimes.F1.Decoder;
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
	private fr.benjamindanlos.laptimes.F1.F12022.Handler.PacketHandler f12022handler;

	@Autowired
	private fr.benjamindanlos.laptimes.F1.F12023.Handler.PacketHandler f12023handler;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        ByteBuf buffer = datagramPacket.content();
		int packetFormat = buffer.readUnsignedShortLE();
		buffer.resetReaderIndex();
		switch (packetFormat){
			case 2022 -> f12022handler.handlePacket(buffer);
			case 2023 -> f12023handler.handlePacket(buffer);
			default -> log.warn("Received a packet in {} format, it is not yet implemented.", packetFormat);
		}
    }
}
