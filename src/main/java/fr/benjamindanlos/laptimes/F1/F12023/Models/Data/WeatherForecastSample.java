package fr.benjamindanlos.laptimes.F1.F12023.Models.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.SessionType;
import fr.benjamindanlos.laptimes.F1.F12023.Models.Enums.Weather;
import lombok.Getter;

@Getter
public class WeatherForecastSample {

    private SessionType sessionType;
    private short timeOffset;
    private Weather weather;
    private short trackTemperature;
    private short trackTemperatureChange;
    private short airTemperature;
    private short airTemperatureChange;
    private short rainPercentage;

    /**
     * Fill the current WeatherForecastSample with the raw bytes representation
     *
     * @param buffer buffer with the raw bytes representation
     * @return current filled WeatherForecastSample instance
     */
    public WeatherForecastSample fill(ByteBuf buffer) {
        this.sessionType = SessionType.valueOf(buffer.readUnsignedByte());
        this.timeOffset = buffer.readUnsignedByte();
        this.weather = Weather.valueOf(buffer.readUnsignedByte());
        this.trackTemperature = buffer.readByte();
        this.trackTemperatureChange = buffer.readByte();
        this.airTemperature = buffer.readByte();
        this.airTemperatureChange = buffer.readByte();
        this.rainPercentage = buffer.readUnsignedByte();
        return this;
    }

}
