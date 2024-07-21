package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.CarTelemetryData;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.MfdPanel;
import lombok.Getter;

/**
 * Car Telemetry Packet
 * 
 * This packet details telemetry for all the cars in the race. It details
 * various values that would be recorded on the car such as speed, throttle
 * application, DRS etc.
 * Frequency: Rate as specified in menus
 */
@Getter
public class PacketCarTelemetryData extends Packet {

    private CarTelemetryData[] carTelemetryData = new CarTelemetryData[PacketConstants.CARS];
    private MfdPanel mfdPanelIndex;
    private MfdPanel mfdPanelIndexSecondaryPlayer;
    private short suggestedGear;

    @Override
    public Packet fill(ByteBuf buffer) {
        //super.fill(buffer);
        for (int i = 0; i < PacketConstants.CARS; i++) {
            CarTelemetryData ctd = new CarTelemetryData();
            this.carTelemetryData[i] = ctd.fill(buffer);
        }
        this.mfdPanelIndex = MfdPanel.valueOf(buffer.readUnsignedByte());
        this.mfdPanelIndexSecondaryPlayer = MfdPanel.valueOf(buffer.readUnsignedByte());
        this.suggestedGear = buffer.readByte();
        return this;
    }

}
