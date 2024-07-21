package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.PacketUtils;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Nationality;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Platform;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.ReadyStatus;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Team;
import lombok.Getter;

@Getter
public class LobbyInfoData {

    private short aiControlled;
    private Team teamId;
    private Nationality nationality;
	private Platform platform;
    private String name;
	private short carNumber;
    private ReadyStatus readyStatus;

    /**
     * Fill the current LobbyInfoData with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled LobbyInfoData instance
     */
    public LobbyInfoData fill(ByteBuf buffer) {
        this.aiControlled = buffer.readUnsignedByte();
        this.teamId = Team.valueOf(buffer.readUnsignedByte());
        this.nationality = Nationality.valueOf(buffer.readUnsignedByte());
		this.platform = Platform.valueOf(buffer.readUnsignedByte());
        this.name = PacketUtils.readString(buffer, PacketConstants.NAME_LENGTH);
		this.carNumber = buffer.readUnsignedByte();
        this.readyStatus = ReadyStatus.valueOf(buffer.readUnsignedByte());
        return this;
    }

}
