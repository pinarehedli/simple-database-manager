import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationDao {
    private Connection connection;
    private final String table = "orient.employees";

    public OperationDao() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "user",
                    "password");
        } catch (SQLException e) {
            System.out.println("Error occurred during connection");
        }
    }

    public void createTable() {
        String query = "CREATE TABLE " + table + " (" +
                "id     SERIAL PRIMARY KEY," +
                "first_name     VARCHAR(50)," +
                "last_name      VARCHAR(50)," +
                "position       VARCHAR(50)," +
                "age            INT," +
                "salary         DECIMAL(10, 2)" +
                ");";

        try (var stmt = connection.prepareStatement(query)) {
            stmt.execute();
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String firstName, String lastName, String position, int age, double salary) {
        String query = "INSERT INTO " + table + " (first_name, last_name, position, age, salary) VALUES (?, ?, ?, ?, ?);";

        try (var stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, position);
            stmt.setInt(4, age);
            stmt.setDouble(5, salary);

            if (stmt.executeUpdate() > 0) {
                System.out.println("Row added successfully");
            } else {
                System.out.println("Something went wrong");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM " + table + " WHERE id = ?";

        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            if (stmt.executeUpdate() > 0) {
                System.out.println("Row deleted successfully");
            } else {
                System.out.println("Invalid ID!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getByID(int id) {
        String query = "SELECT first_name, last_name, position, age, salary FROM " + table + " WHERE id = ?";

        try (var stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);

            ResultSet result = stmt.executeQuery();
            result.next();
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String position = result.getString("position");
            int age = result.getInt("age");
            double salary = result.getDouble("salary");

            System.out.println("\n--- Employee information ---\n");
            System.out.println("First name: " + firstName);
            System.out.println("Last name: " + lastName);
            System.out.println("Position: " + position);
            System.out.println("Age: " + age);
            System.out.println("Salary: " + salary);

        } catch (SQLException e) {
            System.out.println("Invalid ID!");
        }
    }

    public void showAllEmployees() {
        String query = "SELECT * FROM " + table;
        try (var stmt = connection.prepareStatement(query)) {

            ResultSet result = stmt.executeQuery();
            System.out.printf("%5s %-10s %-15s %-25s %10s %15s%n", "id", "Name", "Surname", "Position", "Age", "Salary");
            System.out.println("   ----------------------------------------------------------------------------------");
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String position = result.getString("position");
                int age = result.getInt("age");
                double salary = result.getDouble("salary");
                System.out.printf("%5d %-10s %-15s %-25s %10d %15.2f%n", id, firstName, lastName, position, age, salary);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
