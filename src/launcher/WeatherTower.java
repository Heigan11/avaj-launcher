package launcher;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates){
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather(){
        this.conditionsChanged();
    }
}
