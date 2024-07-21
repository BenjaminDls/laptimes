package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

@Getter
public class PacketMotionExtendedData extends Packet{
	// Extra player car ONLY data
    private Float[] suspensionPosition = new Float[PacketConstants.WHEELS]; // Note: All wheel arrays have the following order:
    private Float[] suspensionVelocity = new Float[PacketConstants.WHEELS]; // RL, RR, FL, FR
    private Float[] suspensionAcceleration = new Float[PacketConstants.WHEELS]; // RL, RR, FL, FR
    private Float[] wheelSpeed = new Float[PacketConstants.WHEELS]; // Speed of each wheel
    private Float[] wheelSlipRatio = new Float[PacketConstants.WHEELS]; // Slip ratio for each wheel
    private Float[] wheelSlipAngle = new Float[PacketConstants.WHEELS]; // Slip angles for each wheel
    private Float[] wheelLatForce = new Float[PacketConstants.WHEELS]; // Lateral forces for each wheel
    private Float[] wheelLongForce = new Float[PacketConstants.WHEELS]; // Longitudinal forces for each wheel
    private float heightOfCOGAboveGround; // Height of centre of gravity above ground
    private float localVelocityX; // Velocity in local space – metres/s
    private float localVelocityY; // Velocity in local space
    private float localVelocityZ; // Velocity in local space
    private float angularVelocityX; // Angular velocity x-component – radians/s
    private float angularVelocityY; // Angular velocity y-component
    private float angularVelocityZ; // Angular velocity z-component
    private float angularAccelerationX; // Angular acceleration x-component – radians/s/s
    private float angularAccelerationY; // Angular acceleration y-component
    private float angularAccelerationZ; // Angular acceleration z-component
    private float frontWheelsAngle; // Current front wheels angle in radians
    private Float[] wheelVertForce = new Float[PacketConstants.WHEELS]; // Vertical forces for each wheel

	@Override
	public Packet fill(ByteBuf buffer){
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.suspensionPosition[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.suspensionVelocity[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.suspensionAcceleration[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelSpeed[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelSlipRatio[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelSlipAngle[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelLatForce[i] = buffer.readFloatLE();
		}
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelLongForce[i] = buffer.readFloatLE();
		}
		this.heightOfCOGAboveGround = buffer.readFloatLE();
		this.localVelocityX = buffer.readFloatLE();
		this.localVelocityY = buffer.readFloatLE();
		this.localVelocityZ = buffer.readFloatLE();
		this.angularVelocityX = buffer.readFloatLE();
		this.angularVelocityY = buffer.readFloatLE();
		this.angularVelocityZ = buffer.readFloatLE();
		this.angularAccelerationX = buffer.readFloatLE();
		this.angularAccelerationY = buffer.readFloatLE();
		this.angularAccelerationZ = buffer.readFloatLE();
		this.frontWheelsAngle = buffer.readFloatLE();
		for(int i = 0; i < PacketConstants.WHEELS; i++){
			this.wheelVertForce[i] = buffer.readFloatLE();
		}
		
		return this;
	}
}
