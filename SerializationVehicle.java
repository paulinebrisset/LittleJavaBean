import java.io.*;

public class SerializationVehicle {

    public static void main(String[] args) {
        // Create a Vehicle instance
        Vehicle vehicle = new Vehicle();

        // Serialize the Vehicle object to file vehicle.ser
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehicle.ser"))) {
            outputStream.writeObject(vehicle);
            System.out.println("Vehicle serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
