package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Employee {

    private int idEmployee;
    private String firstName;
    private String lastName;
    private String employeeType;
    private String username;
    private String password;

    public Employee(){

    }

    public Employee(int idEmployee, String firstName, String lastName, String employeeType, String username, String password) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.employeeType = employeeType;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("employeeType"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employees;
    }

    public static Employee getEmployee(int id) {
        Employee employee = null;
        String query = "SELECT * FROM employees WHERE idEmployee = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                employee = new Employee(
                        rs.getInt("idEmployee"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("employeeType"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public static int addEmployee(Employee employee) {

        int id = 0;
        String query = "INSERT INTO employees " + "(firstName,lastName,employeeType,username,password)"
                + "VALUES (?,?,?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, employee.getFirstName());
            pst.setString(2, employee.getLastName());
            pst.setString(3, employee.getEmployeeType());
            pst.setString(4, employee.getUsername());
            pst.setString(5, employee.getPassword());

            created = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean updateEmployee(Employee employee) {
        String query = "UPDATE employees SET firstName = ?,lastName = ?," +
                "employeeType = ?,username = ?,password = ? WHERE idEmployee = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, employee.getFirstName());
            pst.setString(2, employee.getLastName());
            pst.setString(3, employee.getEmployeeType());
            pst.setString(4, employee.getUsername());
            pst.setString(5, employee.getPassword());
            pst.setInt(6, employee.getIdEmployee());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteEmployee(int id) {

        int deleted = 0;

        String query = "DELETE FROM employees WHERE idEmployee = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement();
        ) {
            deleted = st.executeUpdate(query);
            System.out.println(deleted);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deleted > 0;

    }

}
