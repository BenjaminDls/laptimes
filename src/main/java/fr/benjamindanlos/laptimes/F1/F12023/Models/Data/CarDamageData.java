package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class CarDamageData {
	private float[] tyresWear = new float[PacketConstants.TYRES]; // Tyre wear (percentage)
	private short[] tyresDamage = new short[PacketConstants.TYRES]; // Tyre damage (percentage)
	private short[] brakesDamage = new short[PacketConstants.TYRES]; // Brakes damage (percentage)
	private short frontLeftWingDamage; // Front left wing damage (percentage)
	private short frontRightWingDamage; // Front right wing damage (percentage)
	private short rearWingDamage; // Rear wing damage (percentage)
	private short floorDamage; // Floor damage (percentage)
	private short diffuserDamage; // Diffuser damage (percentage)
	private short sidepodDamage; // Sidepod damage (percentage)
	private short drsFault; // Indicator for DRS fault, 0 = OK, 1 = fault
	private short ersFault; // Indicator for ERS fault, 0 = OK, 1 = fault
	private short gearBoxDamage; // Gear box damage (percentage)
	private short engineDamage; // Engine damage (percentage)
	private short engineMGUHWear; // Engine wear MGU-H (percentage)
	private short engineESWear; // Engine wear ES (percentage)
	private short engineCEWear; // Engine wear CE (percentage)
	private short engineICEWear; // Engine wear ICE (percentage)
	private short engineMGUKWear; // Engine wear MGU-K (percentage)
	private short engineTCWear; // Engine wear TC (percentage)
	private short engineBlown; // Engine blown, 0 = OK, 1 = fault
	private short engineSeized; // Engine seized, 0 = OK, 1 = fault

	public CarDamageData fill(ByteBuf buffer){
		for(int index = 0; index < this.tyresWear.length; index++){
			this.tyresWear[index] = buffer.readFloatLE();
		}
		for(int index = 0; index < this.tyresDamage.length; index++){
			this.tyresDamage[index] = buffer.readUnsignedByte();
		}
		for(int index = 0; index < this.brakesDamage.length; index++){
			this.brakesDamage[index] = buffer.readUnsignedByte();
		}
		this.frontLeftWingDamage = buffer.readUnsignedByte();
		this.frontRightWingDamage = buffer.readUnsignedByte();
		this.rearWingDamage = buffer.readUnsignedByte();
		this.floorDamage = buffer.readUnsignedByte();
		this.diffuserDamage = buffer.readUnsignedByte();
		this.sidepodDamage = buffer.readUnsignedByte();
		this.drsFault = buffer.readUnsignedByte();
		this.ersFault = buffer.readUnsignedByte();
		this.gearBoxDamage = buffer.readUnsignedByte();
		this.engineDamage = buffer.readUnsignedByte();
		this.engineMGUHWear = buffer.readUnsignedByte();
		this.engineESWear = buffer.readUnsignedByte();
		this.engineICEWear = buffer.readUnsignedByte();
		this.engineMGUKWear = buffer.readUnsignedByte();
		this.engineTCWear = buffer.readUnsignedByte();
		this.engineBlown = buffer.readUnsignedByte();
		this.engineSeized = buffer.readUnsignedByte();

		return this;
	}
}
