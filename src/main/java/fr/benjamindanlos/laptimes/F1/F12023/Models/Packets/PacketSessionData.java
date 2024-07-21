package fr.benjamindanlos.laptimes.F1.F12023.Models.Packets;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.PacketConstants;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.MarshalZone;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Data.WeatherForecastSample;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Formula;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.SafetyCarStatus;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.SessionType;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Track;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Weather;
import lombok.Getter;
import lombok.Setter;

/**
 * Session Packet
 * 
 * The session packet includes details about the current session in progress.
 * Frequency: 2 per second
 */
@Getter
@Setter
public class PacketSessionData extends Packet {

    private Weather weather;
    private short trackTemperature;
    private short airTemperature;
    private short totalLaps;
    private int trackLength;
    private SessionType sessionType;
    private Track trackId;
    private Formula formula;
    private int sessionTimeLeft;
    private int sessionDuration;
    private short pitSpeedLimit;
    private short gamePaused;
    private short isSpectating;
    private short spectatorCarIndex;
    private short sliProNativeSupport;
    private short numMarshalZones;
    private MarshalZone[] marshalZones = new MarshalZone[PacketConstants.MARSHAL_ZONES];
    private SafetyCarStatus safetyCarStatus;
    private short networkGame;
    private short numWeatherForecastSamples;
    private WeatherForecastSample[] weatherForecastSamples = new WeatherForecastSample[PacketConstants.WEATHER_FORECAST_SAMPLES];
	private short forecastAccuracy;
	private short aiDifficulty;
	private long seasonLinkIdentifier;
	private long weekendLinkIdentifier;
	private long sessionLinkIdentifier;
	private short pitstopWindowIdealLap;
	private short pitstopWindowLatestLap;
	private short pistopRejoinPOsition;
	private short steeringAssist;
	private short breakingAssist;
	private short gearboxAssist;
	private short pitAssist;
	private short pitReleaseAssist;
	private short ersAssist;
	private short drsAssist;
	private short dynamicRacingLine;
	private short dynamicRacingLineType;
	private short gameMode;
	private short ruleSet;
	private long timeOfDay;
	private short sessionLength;
	
	private short speedUnitsLeadPlayer;
	private short temperatureUnitsLeadPlayer;
	private short speedUnitsSecondaryPlayer;
	private short temperatureUnitsSecondaryPlayer;
	private short numSafetyCarPeriods;
	private short numVirtualSafetyCarPeriods;
	private short numRedFlagPeriods;

    @Override
    public Packet fill(ByteBuf buffer) {
		this.weather = Weather.valueOf(buffer.readUnsignedByte());
        this.trackTemperature = buffer.readByte();
        this.airTemperature = buffer.readByte();
        this.totalLaps = buffer.readUnsignedByte();
        this.trackLength = buffer.readUnsignedShortLE();
        this.sessionType = SessionType.valueOf(buffer.readUnsignedByte());
        this.trackId = Track.valueOf(buffer.readByte());
        this.formula = Formula.valueOf(buffer.readUnsignedByte());
        this.sessionTimeLeft = buffer.readUnsignedShortLE();
        this.sessionDuration = buffer.readUnsignedShortLE();
        this.pitSpeedLimit = buffer.readUnsignedByte();
        this.gamePaused = buffer.readUnsignedByte();
        this.isSpectating = buffer.readUnsignedByte();
        this.spectatorCarIndex = buffer.readUnsignedByte();
        this.sliProNativeSupport = buffer.readUnsignedByte();
        this.numMarshalZones = buffer.readUnsignedByte();
        for (int i = 0; i < this.numMarshalZones; i++) {
            MarshalZone mz = new MarshalZone();
            this.marshalZones[i] = mz.fill(buffer);
        }
        this.safetyCarStatus = SafetyCarStatus.valueOf(buffer.readUnsignedByte());
        this.networkGame = buffer.readUnsignedByte();
        this.numWeatherForecastSamples = buffer.readUnsignedByte();
        for (int i = 0; i < this.numWeatherForecastSamples; i++) {
            WeatherForecastSample wfs = new WeatherForecastSample();
            this.weatherForecastSamples[i] = wfs.fill(buffer);
        }
		this.forecastAccuracy=buffer.readUnsignedByte();
		this.aiDifficulty = buffer.readUnsignedByte();
		this.seasonLinkIdentifier = buffer.readUnsignedIntLE();
		this.weekendLinkIdentifier = buffer.readUnsignedIntLE();
		this.sessionLinkIdentifier = buffer.readUnsignedIntLE();
		this.pitstopWindowIdealLap = buffer.readUnsignedByte();
		this.pitstopWindowLatestLap = buffer.readUnsignedByte();
		this.pistopRejoinPOsition = buffer.readUnsignedByte();
		this.steeringAssist = buffer.readUnsignedByte();
		this.breakingAssist = buffer.readUnsignedByte();
		this.gearboxAssist = buffer.readUnsignedByte();
		this.pitAssist = buffer.readUnsignedByte();
		this.pitReleaseAssist = buffer.readUnsignedByte();
		this.ersAssist = buffer.readUnsignedByte();
		this.drsAssist = buffer.readUnsignedByte();
		this.dynamicRacingLine = buffer.readUnsignedByte();
		this.dynamicRacingLineType = buffer.readUnsignedByte();
		this.gameMode = buffer.readUnsignedByte();
		this.ruleSet = buffer.readUnsignedByte();
		this.timeOfDay = buffer.readUnsignedIntLE();
		this.sessionLength = buffer.readUnsignedByte();
		this.speedUnitsLeadPlayer = buffer.readUnsignedByte();
		this.temperatureUnitsLeadPlayer = buffer.readUnsignedByte();
		this.speedUnitsSecondaryPlayer = buffer.readUnsignedByte();
		this.temperatureUnitsSecondaryPlayer = buffer.readUnsignedByte();
		this.numSafetyCarPeriods = buffer.readUnsignedByte();
		this.numVirtualSafetyCarPeriods = buffer.readUnsignedByte();
		this.numRedFlagPeriods = buffer.readUnsignedByte();
        return this;
    }

}
