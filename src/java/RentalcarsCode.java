import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentalcarsCode {

    public static void main(String[] args) {
        List<Car> cars;
        try {
            cars = parseJSON("vehicles.json");

            // Part 1.1
            System.out.println("\n\nPart 1.1:");
            printCars(cars);

            // Part 1.2
            System.out.println("\n\nPart 1.2:");
            printCarSpecs(cars);

            // Part 1.3
            System.out.println("\n\nPart 1.3:");
            printHighestRatedFirst(cars);

            // Part 1.4
            System.out.println("\n\nPart 1.4:");
            printHighestTotalScoreFirst(cars);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void printCars(List<Car> cars) {
        int index = 1;

        Collections.sort(cars, new Car());
        for (Car c : cars) {
            System.out.println(index + ". " + c);
            index++;
        }

    }

    public static void printCarSpecs(List<Car> cars) throws FileNotFoundException, UnsupportedEncodingException {
        int index = 1;
        PrintWriter writer = new PrintWriter("car_specs.txt", "UTF-8");

        for (Car c : cars) {
            System.out.println(index + ". " + c.getName() + " - " + c.specsToString());
            writer.println(index + ". " + c.getName() + " - " + c.specsToString());
            index++;
        }
        writer.close();
    }

    public static void printHighestRatedFirst(List<Car> cars) throws FileNotFoundException, UnsupportedEncodingException {

        // sorting
        Collections.sort(cars, (a, b) -> a.getRating() < b.getRating() ? 1 : a.getRating() == b.getRating() ? 0 : -1);

        PrintWriter writer = new PrintWriter("highest_rating.txt", "UTF-8");

        int index = 1;
        for (Car c : cars) {
            System.out.println(index + ". " + c.getName() + " - "
                    + c.getCarType() + " - "
                    + c.getSupplier()  + " - "
                    + c.getRating() );
            writer.println(index + ". " + c.getName() + " - "
                    + c.getCarType() + " - "
                    + c.getSupplier()  + " - "
                    + c.getRating() );
            index++;
        }
        writer.close();

    }

    public static void printHighestTotalScoreFirst(List<Car> cars) throws FileNotFoundException, UnsupportedEncodingException {

        // sorting
        Collections.sort(cars, (a, b) -> a.getRating()+(double)a.getVehicleScore() < b.getRating()+(double)b.getVehicleScore() ? 1 : a.getRating()+(double)a.getVehicleScore() == b.getRating()+(double)b.getVehicleScore() ? 0 : -1);

        PrintWriter writer = new PrintWriter("highest_total_score.txt", "UTF-8");

        int index = 1;
        for (Car c : cars) {
            System.out.println(index + ". " + c.getName() + " - "
                    + c.getVehicleScore() + " - "
                    + c.getRating()  + " - "
                    + (c.getRating()+(double)c.getVehicleScore()) );
            writer.println(index + ". " + c.getName() + " - "
                    + c.getVehicleScore() + " - "
                    + c.getRating()  + " - "
                    + (c.getRating()+(double)c.getVehicleScore()) );
            index++;
        }
        writer.close();

    }

    public static List<Car> parseJSON(String filename) throws Exception {

        List<Car> cars = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(filename));

            JSONObject jsonObject = (JSONObject) obj;
            JSONObject search = (JSONObject) jsonObject.get("Search");

            JSONArray vehicleList = (JSONArray) search.get("VehicleList");

            for(int i = 0; i < vehicleList.size(); i++) {
                JSONObject vehicle = (JSONObject) vehicleList.get(i);

                String sippStr = (String)vehicle.get("sipp");
                char[] sipp = sippStr.toCharArray();

                Car newCar = new Car(sipp,
                        (String) vehicle.get("name"),
                        ((Number)vehicle.get("price")).doubleValue(),
                        (String) vehicle.get("supplier"),
                        ((Number)vehicle.get("rating")).doubleValue()
                );
                cars.add(newCar);

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return cars;

    }
}
