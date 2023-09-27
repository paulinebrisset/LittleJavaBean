import java.io.*;

public class DeserializationVehicle {

    public static void main(String[] args) {
        // Deserialize the Vehicle object from the file
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("vehicle.ser"))) {
            Vehicle deserializedVehicle = (Vehicle) inputStream.readObject();
            System.out.println("Vehicle deserialized successfully.");

            // You can now work with the deserialized Vehicle object
            System.out.println("Deserialized Vehicle Description:");
            System.out.println(deserializedVehicle.getDescription());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
