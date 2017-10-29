import java.util.Comparator;

public class Car implements Comparator<Car> {
    private char[] sipp;
    private String name;
    private double price;
    private String supplier;
    private double rating;

    // from SIPP
    private String carType;
    private String doorsCarType;
    private String transmission;
    private String fuel;
    private String aircon;

    private int vehicleScore;

    public Car(){

    }

    public Car(char[] s, String n, double pri, String suppl, double rat) {
        sipp = s;
        name = n;
        price = pri;
        supplier = suppl;
        rating = rat;

        this.decodeSIPP();
        this.calculateScore();
    }

    private void decodeSIPP() {

        switch (this.sipp[0]) {
            case 'M':
                carType = "Mini";
                break;
            case 'E':
                carType = "Economy";
                break;
            case 'C':
                carType = "Compact";
                break;
            case 'I':
                carType = "Intermediate";
                break;
            case 'S':
                carType = "Standard";
                break;
            case 'F':
                carType = "Full size";
                break;
            case 'P':
                carType = "Premium";
                break;
            case 'L':
                carType = "Luxury";
                break;
            case 'X':
                carType = "Special";
                break;
            default:
                carType = "UNKNOWN";
                break;
        }

        
        switch (this.sipp[1]) {
            case 'B':
                doorsCarType = "2 doors";
                break;
            case 'C':
                doorsCarType = "4 doors";
                break;
            case 'D':
                doorsCarType = "5 doors";
                break;
            case 'W':
                doorsCarType = "Estate";
                break;
            case 'T':
                doorsCarType = "Convertible";
                break;
            case 'F':
                doorsCarType = "SUV";
                break;
            case 'P':
                doorsCarType = "Pick up";
                break;
            case 'V':
                doorsCarType = "Passenger Van";
                break;
            default:
                doorsCarType = "UNKNOWN";
                break;

        }

        switch (this.sipp[2]) {
            case 'M':
                transmission = "Manual";
                break;
            case 'A':
                transmission = "Automatic";
                break;
            default:
                transmission = "UNKNOWN";
                break;
        }

        switch (this.sipp[3]) {
            case 'N':
                fuel = "Petrol";
                aircon = "No AC";
                break;
            case 'R':
                fuel = "Petrol";
                aircon = "AC";
                break;
            default:
                fuel = "UNKNOWN";
                aircon = "UNKNOWN";
                break;
        }





    }


    private void calculateScore() {
        vehicleScore = 0;
        if (this.transmission == "Manual")
            vehicleScore++;
        else if (this.transmission == "Automatic")
            vehicleScore += 5;

        if (this.aircon == "AC")
            vehicleScore += 2;
    }

    @Override
    public int compare(Car c1, Car c2) {
        return (int)(c1.price - c2.price);
    }

    // getters

    public char[] getSipp() {
        return sipp;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getRating() {
        return rating;
    }

    public String getCarType() {
        return carType;
    }

    public int getVehicleScore() {
        return vehicleScore;
    }

    // printing utility

    public String specsToString() {
        return (new String(this.sipp) + " - "
                + this.carType + " - "
                + this.doorsCarType + " - "
                + this.transmission + " - "
                + this.fuel + " - "
                + this.aircon);
    }

    public String toString() {
        return name + " - £" + price;
    }



}
