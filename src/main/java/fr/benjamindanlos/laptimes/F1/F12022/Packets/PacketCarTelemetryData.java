package fr.benjamindanlos.laptimes.F1.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12022.Data.CarTelemetryData;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.MfdPanel;
import lombok.Data;

/**
 * Car Telemetry Packet
 * 
 * This packet details telemetry for all the cars in the race. It details
 * various values that would be recorded on the car such as speed, throttle
 * application, DRS etc.
 * Frequency: Rate as specified in menus
 */
@Data
public class PacketCarTelemetryData extends Packet {

    // 1307
    public static final int SIZE = PacketHeader.SIZE +
                                    CarTelemetryData.SIZE * PacketConstants.CARS +
                                    7;
    
    private List<CarTelemetryData> carTelemetryData = new ArrayList<>(PacketConstants.CARS);
    private MfdPanel mfdPanelIndex;
    private MfdPanel mfdPanelIndexSecondaryPlayer;
    private short suggestedGear;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CarTelemetry[");
        sb.append(super.toString());
        sb.append(",carTelemetryData=");
        for (CarTelemetryData c : carTelemetryData) {
            sb.append(c.toString() + ",");
        }
        sb.append(",mfdPanelIndex=" + this.mfdPanelIndex);
        sb.append(",mfdPanelIndexSecondaryPlayer=" + this.mfdPanelIndexSecondaryPlayer);
        sb.append(",suggestedGear=" + this.suggestedGear);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarTelemetryData ctd = new CarTelemetryData();
            this.carTelemetryData.add(ctd.fill(buffer));
        }
        this.mfdPanelIndex = MfdPanel.valueOf(buffer.readUnsignedByte());
        this.mfdPanelIndexSecondaryPlayer = MfdPanel.valueOf(buffer.readUnsignedByte());
        this.suggestedGear = buffer.readByte();
        return this;
    }

}
