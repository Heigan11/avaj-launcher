package launcher;

import launcher.aircrafts.Flyable;
import launcher.exceptions.Crash;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable){
        try {
            checkCrash(flyable);
        } catch (Crash e) {
            e.printStackTrace(System.out);
            System.exit (1);
        }
        observers.add(flyable);
    }

    public void unregister(Flyable flyable){
        observers.remove(flyable);
    }

    protected void conditionsChanged(){
        for (int i = 0; i < observers.size(); i++){
            observers.get(i).updateConditions();
            try {
                checkCrash(observers.get(i), i);
            } catch (Crash e) {
                e.printStackTrace(System.out);
                System.exit(1);
            }
        }
    }

    private void checkCrash(Flyable flyable) throws Crash {
        for (int i = 0; i < observers.size(); i++){
            if (flyable.getCoordinates().getLongitude() == observers.get(i).getCoordinates().getLongitude() &&
                    flyable.getCoordinates().getLatitude() == observers.get(i).getCoordinates().getLatitude() &&
                    flyable.getCoordinates().getHeight() == observers.get(i).getCoordinates().getHeight())
                throw new Crash("Crash!");
        }
    }

    private void checkCrash(Flyable flyable, int pos) throws Crash {
        for (int i = 0; i < observers.size(); i++){
            if (flyable.getCoordinates().getLongitude() == observers.get(i).getCoordinates().getLongitude() &&
                    flyable.getCoordinates().getLatitude() == observers.get(i).getCoordinates().getLatitude() &&
                    flyable.getCoordinates().getHeight() == observers.get(i).getCoordinates().getHeight()&&
                    i != pos)
                throw new Crash("Crash!");
        }
    }
}
