import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private char[] sipp = "XXXX".toCharArray();
    private String name = "Test Car";
    private double price = 999.9;
    private String supplier = "Test Supplier";
    private double rating = 10;
    private Car car;

    @BeforeEach
    void setup() {
        try {
            car = new Car(sipp, name, price, supplier, rating);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void testCar() {

        assertNotNull(car);
        assertEquals(name, car.getName());
        assertEquals(sipp, car.getSipp());
        assertEquals(price, car.getPrice());
        assertEquals(supplier, car.getSupplier());
        assertEquals(rating, car.getRating());

    }

    @Test
    void testDecodeSIPP() {

        assertEquals("Special", car.getCarType());
        assertEquals("UNKNOWN", car.getDoorsCarType());
        assertEquals("UNKNOWN", car.getTransmission());
        assertEquals("UNKNOWN", car.getFuel());
        assertEquals("UNKNOWN", car.getAircon());

        try {
            car = new Car("".toCharArray(), name, price, supplier, rating);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "SIPP format not recognisable");
        }

        try {
            car = new Car("ABCDE".toCharArray(), name, price, supplier, rating);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "SIPP format not recognisable");
        }


        try {
            car = new Car("MBMN".toCharArray(), name, price, supplier, rating);

            assertEquals("Mini", car.getCarType());
            assertEquals("2 doors", car.getDoorsCarType());
            assertEquals("Manual", car.getTransmission());
            assertEquals("Petrol", car.getFuel());
            assertEquals("No AC", car.getAircon());

        } catch (Exception e) {
            fail("Unexpected exception occurred");
            e.printStackTrace();
        }


    }

    @Test
    void testCalculateScore() {
        assertEquals(0, car.getVehicleScore());

        try {
            sipp = "XXMN".toCharArray(); // manual no AC
            car = new Car(sipp, name, price, supplier, rating);
            assertEquals(1, car.getVehicleScore());

            sipp = "XXMR".toCharArray(); // manual with AC
            car = new Car(sipp, name, price, supplier, rating);
            assertEquals(3, car.getVehicleScore());

            sipp = "XXAN".toCharArray(); // automatic no AC
            car = new Car(sipp, name, price, supplier, rating);
            assertEquals(5, car.getVehicleScore());

            sipp = "XXAR".toCharArray(); // automatic with AC
            car = new Car(sipp, name, price, supplier, rating);
            assertEquals(7, car.getVehicleScore());

        } catch (Exception e) {
            fail("Unexpected exception occurred");
            e.printStackTrace();
        }


    }
}
