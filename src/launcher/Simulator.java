package launcher;

import launcher.aircrafts.AircraftFactory;
import launcher.aircrafts.Flyable;

import java.io.*;

public class Simulator {

    public static void main(String[] args) throws Exception{

        WeatherTower weatherTower = new WeatherTower();

        int numberOfSimulations = 0;

        try {
            File file = new File("scenario.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                if (numberOfSimulations == 0){
                    try {
                        numberOfSimulations = Integer.parseInt(line);
                    }
                    catch (NumberFormatException e) {
                        numberOfSimulations = 0;
                    }
                } else {
                    String[] params = line.split(" ");
                    Flyable flyable = AircraftFactory.newAircraft(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), Integer.parseInt(params[4]));
                    flyable.registerTower(weatherTower);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numberOfSimulations; i++)
            weatherTower.changeWeather();
    }

}
