package launcher.aircrafts;

import launcher.Coordinates;
import launcher.WeatherTower;

public interface Flyable {

    void updateConditions();
    void registerTower(WeatherTower weatherTower);
    void changeCoordinates(int deltaLongitude, int deltaLatitude, int deltaHeight);
    Coordinates getCoordinates();
}
