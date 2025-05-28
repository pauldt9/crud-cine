package Models;

public class Employee {

    private int idEmployee;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Employee(int idEmployee, String firstName, String lastName, String username, String password) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
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
}
