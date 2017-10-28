import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RentalcarsCode {

    public static void main(String[] args) {

        List<Car> cars = new ArrayList<Car>();

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("vehicles.json"));

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




    }
}
