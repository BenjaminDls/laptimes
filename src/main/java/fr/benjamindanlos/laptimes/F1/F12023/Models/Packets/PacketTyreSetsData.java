package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.TyreSet;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class PacketTyreSetsData extends Packet{
	private short carIdx; // Index of the car this data relates to
	private TyreSet[] tyreSetData = new TyreSet[20]; // 13 (dry) + 7 (wet)
	private short fittedIdx; // Index into array of fitted tyre

	@Override
	public Packet fill(ByteBuf buffer){
		this.carIdx = buffer.readUnsignedByte();
		for(TyreSet tyreSet : this.tyreSetData){
			if(tyreSet==null){
				tyreSet = new TyreSet();
			}
			tyreSet.fill(buffer);
		}
		this.fittedIdx = buffer.readUnsignedByte();

		return this;
	}
}
