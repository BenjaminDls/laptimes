package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.*;
import lombok.Getter;

@Getter
public class CarStatusData {

    private TractionControl tractionControl;
    private short antiLockBrakes;
    private FuelMix fuelMix;
    private short frontBrakeBias;
    private short pitLimiterStatus;
    private float fuelInTank;
    private float fuelCapacity;
    private float fuelRemainingLaps;
    private int maxRPM;
    private int idleRPM;
    private short maxGears;
    private DrsAllowed drsAllowed;
    private int drsActivationDistance;
    private ActualTyreCompound actualTyreCompound;
    private VisualTyreCompound visualTyreCompound;
    private short tyresAgeLaps;
    private VehicleFiaFlag vehicleFiaFlags;
	private float enginePowerICE;
	private float enginePowerMGUK;
    private float ersStoreEnergy;
    private ErsDeployMode ersDeployMode;
    private float ersHarvestedThisLapMGUK;
    private float ersHarvestedThisLapMGUH;
    private float ersDeployedThisLap;
	private short networkPaused;

    /**
     * Fill the current CarStatusData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled CarStatusData instance
     */
    public CarStatusData fill(ByteBuf buffer) {
        this.tractionControl = TractionControl.valueOf(buffer.readUnsignedByte());
        this.antiLockBrakes = buffer.readUnsignedByte();
        this.fuelMix = FuelMix.valueOf(buffer.readUnsignedByte());
        this.frontBrakeBias = buffer.readUnsignedByte();
        this.pitLimiterStatus = buffer.readUnsignedByte();
        this.fuelInTank = buffer.readFloatLE();
        this.fuelCapacity = buffer.readFloatLE();
        this.fuelRemainingLaps = buffer.readFloatLE();
        this.maxRPM = buffer.readUnsignedShortLE();
        this.idleRPM = buffer.readUnsignedShortLE();
        this.maxGears = buffer.readUnsignedByte();
        this.drsAllowed = DrsAllowed.valueOf(buffer.readUnsignedByte());
        this.drsActivationDistance = buffer.readUnsignedShortLE();
        this.actualTyreCompound = ActualTyreCompound.valueOf(buffer.readUnsignedByte());
        this.visualTyreCompound = VisualTyreCompound.valueOf(buffer.readUnsignedByte());
        this.tyresAgeLaps = buffer.readUnsignedByte();
        this.vehicleFiaFlags = VehicleFiaFlag.valueOf(buffer.readByte());
		this.enginePowerICE = buffer.readFloatLE();
		this.enginePowerMGUK = buffer.readFloatLE();
        this.ersStoreEnergy = buffer.readFloatLE();
        this.ersDeployMode = ErsDeployMode.valueOf(buffer.readUnsignedByte());
        this.ersHarvestedThisLapMGUK = buffer.readFloatLE();
        this.ersHarvestedThisLapMGUH = buffer.readFloatLE();
        this.ersDeployedThisLap = buffer.readFloatLE();
		this.networkPaused = buffer.readUnsignedByte();
		
        return this;
    }
}
