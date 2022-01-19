package launcher;

import java.util.concurrent.ThreadLocalRandom;

public final class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider(){};

    public static WeatherProvider getWeatherProvider() {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates){
        return weather[(coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight()) % 4];
    }
}
