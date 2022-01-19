package launcher.aircrafts;

import launcher.Coordinates;
import launcher.WeatherTower;

public class Baloon extends Aircraft implements Flyable{

    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "RAIN":
                System.out.println("Baloon#" + this.name + "(" + this.id +"): Damn you rain! You messed up my baloon.");
                changeCoordinates(0,0,-5);
                break;
            case "FOG":
                System.out.println("Baloon#" + this.name + "(" + this.id +"): It's fog! Lower altitude!");
                changeCoordinates(0,0,-3);
                break;
            case "SUN":
                System.out.println("Baloon#" + this.name + "(" + this.id +"): Let's enjoy the good weather and take some pics.");
                changeCoordinates(2,0,4);
                break;
            case "SNOW":
                System.out.println("Baloon#" + this.name + "(" + this.id +"): It's snowing. We're gonna crash.");
                changeCoordinates(0,0,-15);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
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
            System.out.println("Baloon#" + this.name + "(" + this.id + ") landing. Longitude = " + longitude + ", Latitude = " + latitude + ".");
            System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.");
            height = 0;
        }

        if (height > 100)
            height = 100;

        this.coordinates = new Coordinates(longitude, latitude, height);
    }
}
