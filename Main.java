import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test listeners
        testUpdate();
        // Test specificities of trucks
        testTruckUpdate();
        // Test BeanInfo : explore informations
        testBeanInfo();
        // Test serialize and deserialize a single vehicle
        testSerialization();
        // Test serialize and desesialize a list of vehicles
        testMultipleSerialization();
    }

    public static void testMultipleSerialization() {
        List<Vehicle> vehicleList = new ArrayList<>();
        Car vehicle1 = new Car();
        Vehicle vehicle2 = new Vehicle();
        Truck vehicle3 = new Truck();

        vehicle1.setBrand("Renault");
        vehicle1.setModel("Symbol");
        vehicle1.setColor("Blue");

        vehicle2.setBrand("Citroën");
        vehicle2.setModel("C3");
        vehicle2.setColor("Red");

        vehicle3.setBrand("Iveco");
        vehicle3.setModel("Master");
        vehicle3.setColor("Yellow");

        vehicleList.add(vehicle1);
        vehicleList.add(vehicle2);
        vehicleList.add(vehicle3);

        // Serialize the collection of vehicles to a file
        for (Vehicle vehicle : vehicleList) {
            serialiazeAndUnpack(vehicle);
        }
    }

    public static void testSerialization() {
        // Create a Vehicle instance
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Renault");
        vehicle.setModel("Clio");
        vehicle.setColor("Red");
        serialiazeAndUnpack(vehicle);
    }

    public static void serialiazeAndUnpack(Vehicle vehicle) {
        // Serialize the Vehicle in vehicle.serialized
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("vehicle.ser"))) {
            outputStream.writeObject(vehicle);
            System.out.println("Vehicle serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the Vehicle from the vehicle.serialized
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("vehicle.ser"))) {
            Vehicle deserializedVehicle = (Vehicle) inputStream.readObject();
            System.out.println("Vehicle deserialized.");
            System.out.println("Deserialized Vehicle:");
            System.out.println(deserializedVehicle.getDescription());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testBeanInfo() {
        // Explore and display properties and methods using BeanInfo
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Vehicle.class);// Explore and display properties
            System.out.println("Properties of Vehicle:");
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                System.out.println("Property Name: " + descriptor.getName());
                System.out.println("Property Type: " + descriptor.getPropertyType().getName());
                System.out.println();
            }

            // Explore and display methods (excluding the ones inherited from Object class)
            System.out.println("Methods of Vehicle:");
            java.lang.reflect.Method[] methods = Vehicle.class.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                if (!method.getDeclaringClass().equals(Object.class)) {
                    System.out.println("Method Name: " + method.getName());
                    System.out.println("Return Type: " + method.getReturnType().getName());
                    System.out.println();
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public static void testUpdate() {
        // Create Vehicle instance with no parameters
        Vehicle vehicle1 = new Vehicle();
        System.out.println("Truck 1 Description:");
        System.out.println(vehicle1.getDescription()); // Normally empty

        // Set properties for the truck
        vehicle1.setBrand("Renault");
        vehicle1.setModel("Clio");
        vehicle1.setColor("Red");
        System.out.println("\nTruck 1 Description (with values):");
        System.out.println(vehicle1.getDescription()); // Displays customized values for the truck

        // Add property change listeners to monitor property changes
        vehicle1.addPropertyChangeListener(evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
            System.out.println("New value: " + evt.getNewValue());
        });

        // Change a property to trigger a property change event
        vehicle1.setColor("Blue");
    }

    // Heritage test
    public static void testTruckUpdate() {
        // Create Trick with no parameters in the custructor
        Truck truck1 = new Truck();
        System.out.println("Truck 1 Description:");
        System.out.println(truck1.getDescription()); // Displays default values (empty)

        // Set properties
        truck1.setBrand("Iveco");
        truck1.setModel("Master");
        truck1.setColor("Red");
        truck1.setLoadCapacity(20.5);
        System.out.println("\nTruck description with values:");
        System.out.println(truck1.getDescription());

        // Add property change listeners to monitor property changes
        truck1.addPropertyChangeListener(evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
            System.out.println("New value: " + evt.getNewValue());
        });

        // Change a property to trigger propertychange
        truck1.setColor("Green");
        truck1.setLoadCapacity(55);
    }
}
