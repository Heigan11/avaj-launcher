package launcher.aircrafts;

import launcher.Coordinates;
import launcher.WeatherTower;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    private static long idCounter = 0;

    protected Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId(){
        return ++idCounter;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
