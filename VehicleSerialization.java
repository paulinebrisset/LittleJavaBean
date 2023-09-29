import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleSerialization {

    public static void serialiazeAndUnpack(List<Vehicle> vehiclesList, String filename) {
        // serialze
        serializeVehiclesList(vehiclesList, filename);
        ArrayList<Vehicle> deserializedList = deserializeVehiclesList(filename);

        // deserizalize
        for (Vehicle vehicle : deserializedList) {
            System.out.println(vehicle.getDescription());
        }
    }

    public static void serializeVehiclesList(List<Vehicle> vehiclesList, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(vehiclesList);
            System.out.println("Vehicles serialized");
            for (Vehicle vehicle : vehiclesList) {
                System.out.println(vehicle.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Vehicle> deserializeVehiclesList(String filename) {
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object backObject = inputStream.readObject();
            // quick check
            if (backObject instanceof ArrayList<?>) {
                vehiclesList = (ArrayList<Vehicle>) backObject;
                System.out.println("Vehicles deserialized");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehiclesList;
    }
}
