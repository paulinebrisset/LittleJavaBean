import java.io.*;

public class DeserializationVehicle {

    public static void main(String[] args) {
        // Deserialize the Vehicle object from vehicle.ser
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("vehicle.ser"))) {
            Vehicle deserializedVehicle = (Vehicle) inputStream.readObject();
            System.out.println("Deserialized Vehicle Description:");
            System.out.println(deserializedVehicle.getDescription());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
