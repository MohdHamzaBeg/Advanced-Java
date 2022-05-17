import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

class User {
    public int id;
}

class Employee extends User {
    public String name;
    private String password;

    private void demo() {
        System.out.println("demo method is running now");
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", password=" + password + "]";
    }

    public void demoo() {
        System.out.println("demoo method is running now");
    }
}

public class App {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // 1.
        // Since we do not know the return type of the class Employee at the runtime and
        // how its behavior is gonna be, that's we why we are
        // using Class class of the reflection API to get the object of the called class
        // irrespect to its return type and behavior
        // Different use of CLass class is used here for different types of calling
        // methods.

        Class<Employee> clazz1 = Employee.class; // Since we know the name
        System.out.println(clazz1);
        // Class<?> clazz2 = Class.forName("Employee"); // Since we don't know the name
        // of the class
        // System.out.println(clazz2);
        // User u = new Employee();
        // Class <? extends User> clazz3 = u.getClass(); // Since we know that it is an
        // inherited class
        // System.out.println(clazz3);

        // 2.
        System.out.println(Arrays.asList(clazz1.getFields())); // Returns public fields
        System.out.println(Arrays.asList(clazz1.getDeclaredFields())); // Returns private fields too but only from the
                                                                       // inherited class

        // 3.
        // Arrays.asList(clazz1.getMethods()).forEach(System.out::println); // Returns
        // predeclared public methods
        Arrays.asList(clazz1.getDeclaredMethods()).forEach(System.out::println); // Returns user declare public and
                                                                                 // private methods

        // 4.
        var fieldname = clazz1.getField("name"); // Checks whethere the entered fields exist or not
        System.out.println(fieldname);
        var methodname = clazz1.getDeclaredMethod("demo");
        System.out.println(methodname); // Checks whether the entered methods exist or not

        // 5. 
        var methodname2 = clazz1.getDeclaredMethod("demoo"); // Calling the public method using the object returned by getdeclaredmethod
        methodname2.invoke(new Employee());
        methodname.setAccessible(true);
        methodname.invoke(new Employee());

        // 6.
        var user= new Employee(); // Instantiating an object of the class
        fieldname.set(user, "Moon Knight"); // Setting value of the field using the object
        System.out.println(user); // Printing the new value through object
    }
}