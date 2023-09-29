import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test listeners
        testUpdate();
        // Test specificities of trucks
        // testTruckUpdate();
        // Test BeanInfo : explore informations
        // testBeanInfo();
        // Test serialize and deserialize a single vehicle
        // testSerialization();
        // Test serialize and desesialize a list of vehicles
        // testMultipleSerialization();
    }

    public static List<Vehicle> getVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        Car car = new Car();
        Vehicle vehicle = new Vehicle();
        Truck truck = new Truck();

        car.setBrand("Renault");
        car.setModel("Symbol");
        car.setColor("Blue");

        vehicle.setBrand("Citroën");
        vehicle.setModel("C3");
        vehicle.setColor("Red");

        truck.setBrand("Iveco");
        truck.setModel("Master");
        truck.setColor("Yellow");

        vehicleList.add(car);
        vehicleList.add(vehicle);
        vehicleList.add(truck);
        return vehicleList;
    }

    public static void testMultipleSerialization() {
        // Create a Vehicle instance
        List<Vehicle> vehicleList = getVehicleList();
        // static method
        VehicleSerialization.serialiazeAndUnpack(vehicleList, "vehicles.ser");
    }

    public static void testSerialization() {
        // Create a Vehicle instance
        List<Vehicle> vehicleList = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Renault");
        vehicle.setModel("Clio");
        vehicle.setColor("Red");
        vehicleList.add(vehicle);
        // Static
        VehicleSerialization.serialiazeAndUnpack(vehicleList, "vehicle.ser");
    }

    public static void testBeanInfo() {
        // Explore and display properties and methods using BeanInfo
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Vehicle.class);
            System.out.println("Properties of Vehicle:");
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                System.out.println("Property Name: " + descriptor.getName());
                System.out.println("Property Type: " + descriptor.getPropertyType().getName());
                System.out.println();
            }

            // Explore and display methods (excluding the ones inherited)
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
        Vehicle car = new Vehicle();

        // Set properties for it
        car.setBrand("Renault");
        car.setModel("Clio");
        car.setColor("Red");
        System.out.println("Vehicle 1 Description (with values):");
        System.out.println(car.getDescription()); // Displays new values for the vehicle

        System.out.println("Property change is triggered :");
        // Add property change listeners to monitor property changes
        car.addPropertyChangeListener(evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
            System.out.println("New value: " + evt.getNewValue());
        });

        // Change a property to trigger a property change event
        car.setColor("Blue");
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

        // Add property change listeners
        truck1.addPropertyChangeListener(evt -> {
            System.out.println("Property changed: " + evt.getPropertyName());
            System.out.println("New value: " + evt.getNewValue());
        });

        // Test propertychange trigger
        truck1.setColor("Green");
        truck1.setLoadCapacity(55);
    }
}
