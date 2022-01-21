package launcher.aircrafts;

import launcher.Coordinates;
import launcher.WeatherTower;
import launcher.WriteToFile;

public class JetPlane extends Aircraft implements Flyable{

    private WeatherTower weatherTower;

    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "RAIN":
                WriteToFile.writeToFile("JetPlane#" + this.name + "(" + this.id +"): It's raining. Better watch out for lightings.");
                changeCoordinates(5,0,0);
                break;
            case "FOG":
                WriteToFile.writeToFile("JetPlane#" + this.name + "(" + this.id +"): I can't see anything.");
                changeCoordinates(1,0,0);
                break;
            case "SUN":
                WriteToFile.writeToFile("JetPlane#" + this.name + "(" + this.id +"): Where is my sun glasses?");
                changeCoordinates(10,0,2);
                break;
            case "SNOW":
                WriteToFile.writeToFile("JetPlane#" + this.name + "(" + this.id +"): OMG! Winter is coming!");
                changeCoordinates(0,0,-7);
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        WriteToFile.writeToFile("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
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
            WriteToFile.writeToFile("JetPlane#" + this.name + "(" + this.id + ") landing. Longitude = " + longitude + ", Latitude = " + latitude + ".");
            WriteToFile.writeToFile("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered from weather tower.");
            height = 0;
        }

        if (height > 100)
            height = 100;

        this.coordinates = new Coordinates(longitude, latitude, height);
    }
}
