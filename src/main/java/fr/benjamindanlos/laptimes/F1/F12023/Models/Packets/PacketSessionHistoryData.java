package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.LapHistory;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.TyreStintHistory;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
class PacketSessionHistoryData extends Packet{
	private short carIdx; // Index of the car this lap data relates to
	private short numLaps; // Num laps in the data (including current partial lap)
	private short numTyreStints; // Number of tyre stints in the data
	private short bestLapTimeLapNum; // Lap the best lap time was achieved on
	private short bestSector1LapNum; // Lap the best Sector 1 time was achieved on
	private short bestSector2LapNum; // Lap the best Sector 2 time was achieved on
	private short bestSector3LapNum; // Lap the best Sector 3 time was achieved on
	LapHistory[] lapHistoryData = new LapHistory[PacketConstants.MAX_LAP_DATA]; // 100 laps of data max
	TyreStintHistory[] tyreStintsHistoryData = new TyreStintHistory[PacketConstants.TYRE_STINTS];
	
    @Override
    public Packet fill(ByteBuf buffer) {
		this.carIdx = buffer.readUnsignedByte();
		this.numLaps = buffer.readUnsignedByte();
		this.numTyreStints = buffer.readUnsignedByte();
		this.bestLapTimeLapNum = buffer.readUnsignedByte();
		this.bestSector1LapNum = buffer.readUnsignedByte();
		this.bestSector2LapNum = buffer.readUnsignedByte();
		this.bestSector3LapNum = buffer.readUnsignedByte();
		for(var value : this.lapHistoryData){
			if(value==null){
				value = new LapHistory();
			}
			value.fill(buffer);
		}
		for(var value : this.tyreStintsHistoryData){
			if(value==null){
				value = new TyreStintHistory();
			}
			value.fill(buffer);
		}


		return this;
	}
}
