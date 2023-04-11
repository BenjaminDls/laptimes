package fr.benjamindanlos.laptimes.AssettoCorsa.Data;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarTelemetry extends Packet{
	private char identifier;
	private int size;

	private float speedKmh;
	private float speedMph;
	private float speedMs;

	private boolean isAbsEnabled;
	private boolean isAbsInAction;
	private boolean isTcInAction;
	private boolean isTcEnabled;
	private boolean isInPit;
	private boolean isEngineLimiterOn;
	private char undocumentedTwoBytes;

	private float accGVertical;
	private float accGHorizontal;
	private float accGFrontal;

	private int lapTime;
	private int lastLap;
	private int bestLap;
	private int lapCount;

	private float gas;
	private float brake;
	private float clutch;
	private float engineRPM;
	private float steer;
	private int gear;
	private float cgHeight;

	private float[] wheelAngularSpeed = {0,0,0,0};
	private float[] slipAngle = {0,0,0,0};
	private float[] slipAngleContactPatch = {0,0,0,0};
	private float[] slipRatio = {0,0,0,0};
	private float[] tyreSlip = {0,0,0,0};
	private float[] ndSlip = {0,0,0,0};
	private float[] load = {0,0,0,0};
	private float[] dy = {0,0,0,0};
	private float[] mz = {0,0,0,0};
	private float[] tyreDirtyLevel = {0,0,0,0};

	private float[] camberRAD = {0,0,0,0};
	private float[] tyreRadius = {0,0,0,0};
	private float[] tyreLoadedRadius = {0,0,0,0};
	private float[] suspensionHeight = {0,0,0,0};
	private float carPositionNormalized;
	private float carSlope;
	private float[] carCoordinates = {0,0,0,0};

	public CarTelemetry(ByteBuf buffer){
		this.fill(buffer);
	}
	@Override
	public CarTelemetry fill(ByteBuf buffer) {
		this.identifier = buffer.readChar();
		this.size = buffer.readInt();
		this.speedKmh = buffer.readFloat();
		this.speedMph = buffer.readFloat();
		this.speedMs = buffer.readFloat();
		this.isAbsEnabled = buffer.readBoolean();
		this.isAbsInAction = buffer.readBoolean();
		this.isTcInAction = buffer.readBoolean();
		this.isTcEnabled = buffer.readBoolean();
		this.isInPit = buffer.readBoolean();
		this.isEngineLimiterOn = buffer.readBoolean();
		this.accGVertical = buffer.readFloat();
		this.accGHorizontal = buffer.readFloat();
		this.accGFrontal = buffer.readFloat();
		this.lapTime = buffer.readInt();
		this.lastLap = buffer.readInt();
		this.bestLap = buffer.readInt();
		this.lapCount = buffer.readInt();
		this.gas = buffer.readFloat();
		this.brake = buffer.readFloat();
		this.clutch = buffer.readFloat();
		this.engineRPM = buffer.readFloat();
		this.steer = buffer.readFloat();
		this.gear = buffer.readInt();
		this.cgHeight = buffer.readFloat();
		this.wheelAngularSpeed[0] = buffer.readFloat();
		this.wheelAngularSpeed[1] = buffer.readFloat();
		this.wheelAngularSpeed[2] = buffer.readFloat();
		this.wheelAngularSpeed[3] = buffer.readFloat();
		this.slipAngle[0] = buffer.readFloat();
		this.slipAngle[1] = buffer.readFloat();
		this.slipAngle[2] = buffer.readFloat();
		this.slipAngle[3] = buffer.readFloat();
		this.slipAngleContactPatch[0] = buffer.readFloat();
		this.slipAngleContactPatch[1] = buffer.readFloat();
		this.slipAngleContactPatch[2] = buffer.readFloat();
		this.slipAngleContactPatch[3] = buffer.readFloat();
		this.slipRatio[0] = buffer.readFloat();
		this.slipRatio[1] = buffer.readFloat();
		this.slipRatio[2] = buffer.readFloat();
		this.slipRatio[3] = buffer.readFloat();
		this.tyreSlip[0] = buffer.readFloat();
		this.tyreSlip[1] = buffer.readFloat();
		this.tyreSlip[2] = buffer.readFloat();
		this.tyreSlip[3] = buffer.readFloat();
		this.ndSlip[0] = buffer.readFloat();
		this.ndSlip[1] = buffer.readFloat();
		this.ndSlip[2] = buffer.readFloat();
		this.ndSlip[3] = buffer.readFloat();
		this.load[0] = buffer.readFloat();
		this.load[1] = buffer.readFloat();
		this.load[2] = buffer.readFloat();
		this.load[3] = buffer.readFloat();
		this.dy[0] = buffer.readFloat();
		this.dy[1] = buffer.readFloat();
		this.dy[2] = buffer.readFloat();
		this.dy[3] = buffer.readFloat();
		this.mz[0] = buffer.readFloat();
		this.mz[1] = buffer.readFloat();
		this.mz[2] = buffer.readFloat();
		this.mz[3] = buffer.readFloat();
		this.tyreDirtyLevel[0] = buffer.readFloat();
		this.tyreDirtyLevel[1] = buffer.readFloat();
		this.tyreDirtyLevel[2] = buffer.readFloat();
		this.tyreDirtyLevel[3] = buffer.readFloat();
		this.camberRAD[0] = buffer.readFloat();
		this.camberRAD[1] = buffer.readFloat();
		this.camberRAD[2] = buffer.readFloat();
		this.camberRAD[3] = buffer.readFloat();
		this.tyreRadius[0] = buffer.readFloat();
		this.tyreRadius[1] = buffer.readFloat();
		this.tyreRadius[2] = buffer.readFloat();
		this.tyreRadius[3] = buffer.readFloat();
		this.tyreLoadedRadius[0] = buffer.readFloat();
		this.tyreLoadedRadius[1] = buffer.readFloat();
		this.tyreLoadedRadius[2] = buffer.readFloat();
		this.tyreLoadedRadius[3] = buffer.readFloat();
		this.suspensionHeight[0] = buffer.readFloat();
		this.suspensionHeight[1] = buffer.readFloat();
		this.suspensionHeight[2] = buffer.readFloat();
		this.suspensionHeight[3] = buffer.readFloat();
		this.carPositionNormalized = buffer.readFloat();
		this.carSlope = buffer.readFloat();
		this.carCoordinates[0] = buffer.readFloat();
		this.carCoordinates[1] = buffer.readFloat();
		this.carCoordinates[2] = buffer.readFloat();
		return this;
	}
}
