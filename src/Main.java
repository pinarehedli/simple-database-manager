import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OperationDao operation = new OperationDao();
        Scanner sc = new Scanner(System.in);

        //Creating table only one usage
        operation.createTable();

        //Operations start
        menu();
        System.out.println("Enter your selection: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1:
                //Adding a new employee
                System.out.println("Enter employee's first name: ");
                String firstName = sc.nextLine();
                System.out.println("Enter employee's last name: ");
                String lastName = sc.nextLine();
                System.out.println("Enter employee's position: ");
                String position = sc.nextLine();
                System.out.println("Enter employee's age: ");
                int age = Integer.parseInt(sc.nextLine());
                System.out.println("Enter employee's salary: ");
                double salary = Double.parseDouble(sc.nextLine());
                operation.add(firstName, lastName, position, age, salary);
                break;
            case 2:
                //Deleting an employee by ID
                System.out.println("Enter employee's ID: ");
                int id1 = Integer.parseInt(sc.nextLine());
                operation.delete(id1);
                break;
            case 3:
                //Getting information about an employee by ID
                System.out.println("Enter employee's ID: ");
                int id2 = Integer.parseInt(sc.nextLine());
                operation.getByID(id2);
                break;
            case 4:
                //Displaying all the employees in database
                operation.showAllEmployees();
                break;
            default:
                System.out.println("Invalid selection!");

        }
    }

    public static void menu() {
        System.out.println("\n--- Database Operations ---\n");
        System.out.println("1. Add a new employee");
        System.out.println("2. Delete an employee");
        System.out.println("3. Get employee by ID");
        System.out.println("4. Show all employees");
    }
}
