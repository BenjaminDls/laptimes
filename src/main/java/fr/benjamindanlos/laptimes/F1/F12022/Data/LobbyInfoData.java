package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.PacketConfig;
import fr.benjamindanlos.laptimes.F1.F12022.PacketUtils;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Nationality;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.ReadyStatus;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Team;
import lombok.Data;

@Data
public class LobbyInfoData {

    public static final int SIZE = 52;

    public static final int NAME_LENGTH = 48;

    private short aiControlled;
    private Team teamId;
    private Nationality nationality;
    private String name;
    private ReadyStatus readyStatus;

    /**
     * Fill the current LobbyInfoData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled LobbyInfoData instance
     */
    public LobbyInfoData fill(ByteBuf buffer) {
        this.aiControlled = buffer.readUnsignedByte();
        this.teamId = Team.valueOf(PacketConfig.getSeason(), buffer.readUnsignedByte());
        this.nationality = Nationality.valueOf(buffer.readUnsignedByte());
        this.name = PacketUtils.readString(buffer, LobbyInfoData.NAME_LENGTH);
        this.readyStatus = ReadyStatus.valueOf(buffer.readUnsignedByte());
        return this;
    }

    @Override
    public String toString() {
        return "LobbyInfoData[" +
                ",aiControlled=" + this.aiControlled +
                ",teamId=" + this.teamId +
                ",nationality=" + this.nationality +
                ",name=" + this.name +
                ",readyStatus=" + this.readyStatus +
                "]";
    }
}
