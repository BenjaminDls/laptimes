package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.DrsAllowed;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ErsDeployMode;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.FuelMix;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.TractionControl;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ActualTyreCompound;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.VehicleFiaFlag;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.VisualTyreCompound;
import lombok.Data;


import java.util.Arrays;

@Data
public class CarStatusData {

    public static final int SIZE = 60;

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
    private short tyresWear[] = new short[PacketConstants.TYRES];
    private ActualTyreCompound actualTyreCompound;
    private VisualTyreCompound visualTyreCompound;
    private short tyresAgeLaps;
    private short tyresDamage[] = new short[PacketConstants.TYRES];
    private short frontLeftWingDamage;
    private short frontRightWingDamage;
    private short rearWingDamage;
    private short drsFault;
    private short engineDamage;
    private short gearBoxDamage;
    private VehicleFiaFlag vehicleFiaFlags;
    private float ersStoreEnergy;
    private ErsDeployMode ersDeployMode;
    private float ersHarvestedThisLapMGUK;
    private float ersHarvestedThisLapMGUH;
    private float ersDeployedThisLap;

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
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.tyresWear[j] = buffer.readUnsignedByte();
        }
        this.actualTyreCompound = ActualTyreCompound.valueOf(buffer.readUnsignedByte());
        this.visualTyreCompound = VisualTyreCompound.valueOf(buffer.readUnsignedByte());
        this.tyresAgeLaps = buffer.readUnsignedByte();
        for (int j = 0; j < PacketConstants.TYRES; j++) {
            this.tyresDamage[j] = buffer.readUnsignedByte();
        }
        this.frontLeftWingDamage = buffer.readUnsignedByte();
        this.frontRightWingDamage = buffer.readUnsignedByte();
        this.rearWingDamage = buffer.readUnsignedByte();
        this.drsFault = buffer.readUnsignedByte();
        this.engineDamage = buffer.readUnsignedByte();
        this.gearBoxDamage = buffer.readUnsignedByte();
        this.vehicleFiaFlags = VehicleFiaFlag.valueOf(buffer.readByte());
        this.ersStoreEnergy = buffer.readFloatLE();
        this.ersDeployMode = ErsDeployMode.valueOf(buffer.readUnsignedByte());
        this.ersHarvestedThisLapMGUK = buffer.readFloatLE();
        this.ersHarvestedThisLapMGUH = buffer.readFloatLE();
        this.ersDeployedThisLap = buffer.readFloatLE();
        return this;
    }

    @Override
    public String toString() {
        return "CarStatusData[tractionControl=" + this.tractionControl +
                ",antiLockBrakes=" + this.antiLockBrakes +
                ",fuelMix=" + this.fuelMix +
                ",frontBrakeBias=" + this.frontBrakeBias +
                ",pitLimiterStatus=" + this.pitLimiterStatus +
                ",fuelInTank=" + this.fuelInTank +
                ",fuelCapacity=" + this.fuelCapacity +
                ",fuelRemainingLaps=" + this.fuelRemainingLaps +
                ",maxRPM=" + this.maxRPM +
                ",idleRPM=" + this.idleRPM +
                ",maxGears=" + this.maxGears +
                ",drsAllowed=" + this.drsAllowed +
                ",drsActivationDistance=" + this.drsActivationDistance +
                ",tyresWear=" + Arrays.toString(this.tyresWear) +
                ",actualTyreCompound=" + this.actualTyreCompound +
                ",visualTyreCompound=" + this.visualTyreCompound +
                ",tyresAgeLaps=" + this.tyresAgeLaps +
                ",tyresDamage=" + Arrays.toString(this.tyresDamage) +
                ",frontLeftWingDamage=" + this.frontLeftWingDamage +
                ",frontRightWingDamage=" + this.frontRightWingDamage +
                ",rearWingDamage=" + this.rearWingDamage +
                ",drsFault=" + this.drsFault +
                ",engineDamage=" + this.engineDamage +
                ",gearBoxDamage=" + this.gearBoxDamage +
                ",vehicleFiaFlags=" + this.vehicleFiaFlags +
                ",ersStoreEnergy=" + this.ersStoreEnergy +
                ",ersDeployMode=" + this.ersDeployMode +
                ",ersHarvestedThisLapMGUK=" + this.ersHarvestedThisLapMGUK +
                ",ersHarvestedThisLapMGUH=" + this.ersHarvestedThisLapMGUH +
                ",ersDeployedThisLap=" + this.ersDeployedThisLap +
                "]";
    }
}
