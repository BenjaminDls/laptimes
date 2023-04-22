package fr.benjamindanlos.laptimes.F1.F12022.Data;

import io.netty.buffer.ByteBuf;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.SessionType;
import fr.benjamindanlos.laptimes.F1.F12022.Enums.Weather;
import lombok.Data;

@Data
public class WeatherForecastSample {

    public static final int SIZE = 5;

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

	@Override
	public String toString() {
		return "WeatherForecastSample{" +
				"sessionType=" + sessionType +
				", timeOffset=" + timeOffset +
				", weather=" + weather +
				", trackTemperature=" + trackTemperature +
				", trackTemperatureChange=" + trackTemperatureChange +
				", airTemperature=" + airTemperature +
				", airTemperatureChange=" + airTemperatureChange +
				", rainPercentage=" + rainPercentage +
				'}';
	}
}
