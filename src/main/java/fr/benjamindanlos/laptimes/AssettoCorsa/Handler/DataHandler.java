package fr.benjamindanlos.laptimes.AssettoCorsa.Handler;

import fr.benjamindanlos.laptimes.AssettoCorsa.Data.AssettoCorsaDataTypes;
import fr.benjamindanlos.laptimes.AssettoCorsa.Data.LapdataExtended;
import fr.benjamindanlos.laptimes.Entities.Games;
import fr.benjamindanlos.laptimes.Entities.Laptime;
import fr.benjamindanlos.laptimes.Repository.LaptimeRepository;
import fr.benjamindanlos.laptimes.Utils.Tools;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DataHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	@Autowired
	private LaptimeRepository laptimeRepository;

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
					handleLapdata(lapdataExtended);
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

	private void handleLapdata(LapdataExtended lapdataExtended){
		Laptime entity = new Laptime();
		entity.setCar(lapdataExtended.getCarName());
		entity.setGame(Games.AssettoCorsa);
		entity.setDriver(lapdataExtended.getDriverName());
		entity.setTrack(lapdataExtended.getTrackName());
		entity.setLaptime(lapdataExtended.getTime()/1000.0f);
		entity.setLaptimeString(Tools.laptimeToString(entity.getLaptime()));
		entity.setDate(LocalDateTime.now());

		try{
			laptimeRepository.save(entity);
		}
		catch (Exception e){
			log.error("Failed to save lapdata in db"+e.getMessage());
			e.printStackTrace();
		}

	}
}
