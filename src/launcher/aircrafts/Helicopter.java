package launcher.aircrafts;

import launcher.Coordinates;
import launcher.WeatherTower;

public class Helicopter extends Aircraft implements Flyable{

    private WeatherTower weatherTower;

    protected Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "RAIN":
                System.out.println("Helicopter#" + this.name + "(" + this.id +"): It's raining. Helicopter is wet.");
                changeCoordinates(5,0,0);
                break;
            case "FOG":
                System.out.println("Helicopter#" + this.name + "(" + this.id +"): It's fog. I don't see you.");
                changeCoordinates(1,0,0);
                break;
            case "SUN":
                System.out.println("Helicopter#" + this.name + "(" + this.id +"): This is hot.");
                changeCoordinates(10,0,2);
                break;
            case "SNOW":
                System.out.println("Helicopter#" + this.name + "(" + this.id +"): My rotor is going to freeze!");
                changeCoordinates(0,0,-12);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
    }

    @Override
    public void changeCoordinates(int deltaLongitude, int deltaLatitude, int deltaHeight){

        int longitude = this.coordinates.getLongitude();
        int latitude = this.coordinates.getLatitude();
        int height = this.coordinates.getHeight();

        longitude += deltaLongitude;
        latitude += deltaLatitude;
        height += deltaHeight;

        if (height <= 0) {
            this.weatherTower.unregister(this);
            System.out.println("Helicopter#" + this.name + "(" + this.id + ") landing. Longitude = " + longitude + ", Latitude = " + latitude + ".");
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower.");
            height = 0;
        }

        if (height > 100)
            height = 100;

        this.coordinates = new Coordinates(longitude, latitude, height);
    }
}
