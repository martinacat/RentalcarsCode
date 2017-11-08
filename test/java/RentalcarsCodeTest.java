import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RentalcarsCodeTest {
    @Test
    void testParseJSON() throws Exception {
        List<Car> cars;
        cars = RentalcarsCode.parseJSON("vehicles.json");
        assertNotNull(cars);
        assertFalse(cars.isEmpty());
        assertTrue(cars.get(0).getName().contains("Ford Focus"));
        assertEquals(31, cars.size());

        cars = RentalcarsCode.parseJSON("test.json");
        assertFalse(cars.get(0).getName().contains("Ford Focus"));
        assertEquals(2, cars.size());
    }

}