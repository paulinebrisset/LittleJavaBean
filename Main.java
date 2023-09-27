import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class Main {
    public static void main(String[] args) {

        // Explore and display properties and methods using BeanInfo
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(Vehicle.class);

            // Explore and display properties
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
}
