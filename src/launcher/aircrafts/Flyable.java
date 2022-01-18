package launcher.aircrafts;

public interface Flyable {

    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
