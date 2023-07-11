package Testing;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    public static void main(String[] args) {
        // Set up test data
        String id = "12345";
        String name = "Bonita Acharya";
        String mobile = "0987654321";
        String billingAddress = "123 Main Street";
        String shippingAddress = "456 Elm Street";
        String email = "binita@example.com";
        String nationality = "nepali";

        // Invoke the login() method
        String result = login(id, name, mobile, billingAddress, shippingAddress, email, nationality);

        // Verify the result
        assertEquals("12345, Bonita Acharya, 0987654321, 123 Main Street, 456 Elm Street, binita@example.com, nepali", result);
    }

    public static String login(String id, String name, String mobile, String billingAddress, String shippingAddress, String email, String nationality) {
        return id + ", " + name + ", " + mobile + ", " + billingAddress + ", " + shippingAddress + ", " + email + ", " + nationality;
    }
}
