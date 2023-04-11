package fr.benjamindanlos.laptimes.AssettoCorsa.Handler;

import fr.benjamindanlos.laptimes.AssettoCorsa.Data.AssettoCorsaDataTypes;
import fr.benjamindanlos.laptimes.AssettoCorsa.Data.LapdataExtended;
import fr.benjamindanlos.laptimes.Entities.Games;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Utils.Tools;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class DataHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		ByteBuf data = msg.content();
		//the first value is an int of the type of data to follow
		int identifier = data.readIntLE();
		switch (identifier) {
			case AssettoCorsaDataTypes.LAPDATA -> {
				try {
					LapdataExtended lapdataExtended = new LapdataExtended(data);
					log.info("Received LapData : " + lapdataExtended.getDriverName()+" @ "+lapdataExtended.getTrackName()+" in "+lapdataExtended.getCarName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			case AssettoCorsaDataTypes.TELEMETRY -> {
				try {
					//CarTelemetry telemetry = new CarTelemetry(data);
					log.info("Received Telemetry");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			default -> log.info("How did you get there ??");
		}
	}

/*

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf data = ctx.alloc().buffer(100);
		InetSocketAddress socketAddress = new InetSocketAddress("192.168.1.10", 9996);
		data.writeBytes("110".getBytes());
		ctx.write(new DatagramPacket(data, socketAddress));
		ctx.flush();
		Thread.sleep(1000);
		data = ctx.alloc().buffer(100);
		data.writeBytes("112".getBytes());
		ctx.write(new DatagramPacket(data, socketAddress));
		ctx.flush();
	}
*/

	private void handleLapdata(LapdataExtended lapdataExtended){
		Laptime entity = new Laptime();
		entity.setCar(lapdataExtended.getCarName());
		entity.setGame(Games.AssettoCorsa);
		entity.setDriver(lapdataExtended.getDriverName());
		entity.setTrack(lapdataExtended.getTrackName());
		entity.setLaptime(lapdataExtended.getTime()/1000);
		entity.setLaptimeString(Tools.laptimeToString(entity.getLaptime()));
		entity.setDate(LocalDateTime.now());

	}
}
