package launcher;

import launcher.aircrafts.AircraftFactory;
import launcher.aircrafts.Flyable;
import launcher.exceptions.WrongAircraftData;
import launcher.exceptions.WrongNumberOfSimulations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public static void main(String[] args) throws Exception{

        WeatherTower weatherTower = new WeatherTower();
        List<String> list = new ArrayList<>();

        int numberOfSimulations = 0;

        try {
            File file = new File("scenario.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            FileWriter myWriter = new FileWriter("simulation.txt");
            myWriter.close();
            while (line != null) {
                if (numberOfSimulations == 0){
                    try {
                        numberOfSimulations = Integer.parseInt(line);
                    }
                    catch (NumberFormatException e) {
                        numberOfSimulations = 0;
                    }
                    if (numberOfSimulations <= 0)
                        throw new WrongNumberOfSimulations("WrongNumberOfSimulations");
                } else {
                    String[] params = line.split(" ");
                    if (params.length != 5)
                        throw new WrongAircraftData("Wrong Number Of Params");
                    if (!params[0].equals("Baloon") && !params[0].equals("JetPlane") && !params[0].equals("Helicopter"))
                        throw new WrongAircraftData("Wrong Name Of Aircraft");
                    try {
                        Integer.parseInt(params[2]);
                        Integer.parseInt(params[3]);
                        Integer.parseInt(params[4]);
                    } catch (NumberFormatException e) {
                        throw new WrongAircraftData("Wrong Aircraft Data");
                    }
                    if (Integer.parseInt(params[2]) < 0 || Integer.parseInt(params[3]) < 0 || Integer.parseInt(params[4]) < 0) {
                        throw new WrongAircraftData("Wrong Aircraft Data");
                    }
                }
                line = reader.readLine();
                list.add(line);
            }
            reader.close();
        } catch (WrongAircraftData | WrongNumberOfSimulations | IOException e) {
            e.printStackTrace(System.out);
        }

        for (int i = 0; i < list.size() - 1; i++){
            String[] params = list.get(i).split(" ");
            Flyable flyable = AircraftFactory.newAircraft(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), Integer.parseInt(params[4]));
            flyable.registerTower(weatherTower);
        }

        for (int i = 0; i < numberOfSimulations; i++)
            weatherTower.changeWeather();
    }

}
