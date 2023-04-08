/*
 * Copyright Paolo Patierno.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package fr.benjamindanlos.laptimes.F12022.Packets;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F12022.PacketConfig;
import fr.benjamindanlos.laptimes.F12022.PacketConstants;
import fr.benjamindanlos.laptimes.F12022.Data.MarshalZone;
import fr.benjamindanlos.laptimes.F12022.Data.WeatherForecastSample;
import fr.benjamindanlos.laptimes.F12022.Enums.Formula;
import fr.benjamindanlos.laptimes.F12022.Enums.SafetyCarStatus;
import fr.benjamindanlos.laptimes.F12022.Enums.SessionType;
import fr.benjamindanlos.laptimes.F12022.Enums.Track;
import fr.benjamindanlos.laptimes.F12022.Enums.Weather;
import lombok.Data;

/**
 * Session Packet
 * 
 * The session packet includes details about the current session in progress.
 * Frequency: 2 per second
 */
@Data
public class PacketSessionData extends Packet {

    // 251
    public static final int SIZE = PacketHeader.SIZE + 
                                    19 + 
                                    MarshalZone.SIZE * PacketConstants.MARSHAL_ZONES + 
                                    3 + 
                                    WeatherForecastSample.SIZE * PacketConstants.WEATHER_FORECAST_SAMPLES;

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
    private List<MarshalZone> marshalZones = new ArrayList<>(PacketConstants.MARSHAL_ZONES);
    private SafetyCarStatus safetyCarStatus;
    private short networkGame;
    private short numWeatherForecastSamples;
    private List<WeatherForecastSample> weatherForecastSamples = new ArrayList<>(PacketConstants.WEATHER_FORECAST_SAMPLES);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Session[");
        sb.append(super.toString());
        sb.append(",weather=" + this.weather);
        sb.append(",trackTemperature=" + this.trackTemperature);
        sb.append(",airTemperature=" + this.airTemperature);
        sb.append(",totalLaps=" + this.totalLaps);
        sb.append(",trackLength=" + this.trackLength);
        sb.append(",sessionType=" + this.sessionType);
        sb.append(",trackId=" + this.trackId);
        sb.append(",formula=" + this.formula);
        sb.append(",sessionTimeLeft=" + this.sessionTimeLeft);
        sb.append(",sessionDuration=" + this.sessionDuration);
        sb.append(",pitSpeedLimit=" + this.pitSpeedLimit);
        sb.append(",gamePaused=" + this.gamePaused);
        sb.append(",isSpectating=" + this.isSpectating);
        sb.append(",spectatorCarIndex" + this.spectatorCarIndex);
        sb.append(",sliProNativeSupport=" + this.sliProNativeSupport);
        sb.append(",numMarshalZones=" + this.numMarshalZones);
        sb.append(",marshalZones=");
        for (MarshalZone mz: marshalZones) {
            sb.append(mz.toString() + ",");
        }
        sb.append("safetyCarStatus=" + this.safetyCarStatus);
        sb.append(",networkGame=" + this.networkGame);
        sb.append(",numWeatherForecastSamples=" + this.numWeatherForecastSamples);
        sb.append(",weatherForecastSamples=");
        for (WeatherForecastSample wfs : weatherForecastSamples) {
            sb.append(wfs.toString() + ",");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Packet fill(ByteBuf buffer) {
		//super.fill(buffer);
        this.weather = Weather.valueOf(buffer.readUnsignedByte());
        this.trackTemperature = buffer.readByte();
        this.airTemperature = buffer.readByte();
        this.totalLaps = buffer.readUnsignedByte();
        this.trackLength = buffer.readUnsignedShortLE();
        this.sessionType = SessionType.valueOf(buffer.readUnsignedByte());
        this.trackId = Track.valueOf(PacketConfig.getSeason(), buffer.readByte());
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
            this.marshalZones.add(mz.fill(buffer));
        }
        this.safetyCarStatus = SafetyCarStatus.valueOf(buffer.readUnsignedByte());
        this.networkGame = buffer.readUnsignedByte();
        this.numWeatherForecastSamples = buffer.readUnsignedByte();
        for (int i = 0; i < this.numWeatherForecastSamples; i++) {
            WeatherForecastSample wfs = new WeatherForecastSample();
            this.weatherForecastSamples.add(wfs.fill(buffer));
        }
        return this;
    }

    @Override
    public ByteBuf fillBuffer(ByteBuf buffer) {
        super.fillBuffer(buffer);

        buffer.writeByte(this.weather.getValue());
        buffer.writeByte(this.trackTemperature);
        buffer.writeByte(this.airTemperature);
        buffer.writeByte(this.totalLaps);
        buffer.writeShortLE(this.trackLength);
        buffer.writeByte(this.sessionType.getValue());
        buffer.writeByte(this.trackId.getValue());
        buffer.writeByte(this.formula.getValue());
        buffer.writeShortLE(this.sessionTimeLeft);
        buffer.writeShortLE(this.sessionDuration);
        buffer.writeByte(this.pitSpeedLimit);
        buffer.writeByte(this.gamePaused);
        buffer.writeByte(this.isSpectating);
        buffer.writeByte(this.spectatorCarIndex);
        buffer.writeByte(this.sliProNativeSupport);
        buffer.writeByte(this.numMarshalZones);
        for (MarshalZone mz : this.marshalZones) {
            mz.fillBuffer(buffer);
        }
        buffer.writeByte(this.safetyCarStatus.getValue());
        buffer.writeByte(this.networkGame);
        buffer.writeByte(this.numWeatherForecastSamples);
        for (WeatherForecastSample wfs : this.weatherForecastSamples) {
            wfs.fillBuffer(buffer);
        }
        return buffer;
    }
}
