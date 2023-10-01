package special;

import controllers.PassController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Data_base_Con;
import main.Main;
import main.Protect_constants;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class User extends Protect_constants {

    @FXML
    private Button button_save;

    @FXML
    private TextField signUpPhoneNumber;

    @FXML
    private TextField text_address;

    @FXML
    private TextField text_first_name;

    @FXML
    private TextField text_last_name;

    @FXML
    private TextField text_login;

    @FXML
    private Text text_mistake;

    @FXML
    private TextField text_password;

    @FXML
    private TextField text_second_name;


    private static String pass = "";

    private boolean flag = true;
    private static String last_name = "";

    private static String first_name = "";

    private static String second_name = "";

    private static String address = "";

    private static String phone = "";

    private static String login = "";

    private String phone_number;



    private String password;

    private String car_now;

    private String car_old;

    private String cars;

    private String work_time;

    private String salary;

    private String service_count;

    private String name;

    private String status; // 0 - block 1 - active

    private String post; // 0 - client 1 - employee 2 - admin

    // client
    public User(String last_name, String first_name,
                String second_name, String address,
                String phone_number, String login,
                String password, String post,
                String car_now, String car_old) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.phone_number = phone_number;
        this.login = login;
        this.password = password;
        this.post = post;
        this.car_now = car_now;
        this.car_old = car_old;
    }

    // client
    public User(String last_name, String first_name,
                String second_name, String address,
                String phone_number, String login,
                String password, String cars,
                String service_count, String post, String name) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.phone_number = phone_number;
        this.login = login;
        this.password = password;
        this.cars = cars;
        this.service_count = service_count;
        this.post = post;
        this.name = name;
    }

    //employee
    public User(String last_name, String first_name,
                String second_name, String address,
                String login, String password,
                String post, String work_time,
                String salary, String service_count,
                String name, int x) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.login = login;
        this.password = password;
        this.post = post;
        this.work_time = work_time;
        this.salary = salary;
        this.service_count = service_count;
        this.name = name;
    }

    //employee
    public User(String last_name, String first_name,
                String second_name, String address,
                String login, String password,
                String post, String work_time,
                String salary) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.second_name = second_name;
        this.address = address;
        this.login = login;
        this.password = password;
        this.post = post;
        this.work_time = work_time;
        this.salary = salary;
    }

    public static void add()
            throws SQLException, ClassNotFoundException {
        String insertNew = "INSERT INTO " + CLIENTS_TABLE + " (" + CLIENTS_LAST_NAME + ", " +
                CLIENTS_FIRST_NAME + ", " + CLIENTS_SECOND_NAME + ", " + CLIENTS_ADDRESS + ", " +
                CLIENTS_LOGIN + ", " + CLIENTS_PASSWORD + ", " + CLIENTS_PHONE_NUMBER + ", status" +
                ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = Data_base_Con.getInstance().prepareStatement(insertNew);
        preparedStatement.setString(1, last_name);
        preparedStatement.setString(2, first_name);
        preparedStatement.setString(3, second_name);
        preparedStatement.setString(4, address);
        preparedStatement.setString(5, login);
        preparedStatement.setString(6, pass);
        preparedStatement.setString(7, phone);
        preparedStatement.setInt(8, 1);
        preparedStatement.executeUpdate();
        Main.changeScene("admin_clients.fxml");
    }

    // admin


    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPost() {
        return post;
    }

    public String getCar_now() {
        return car_now;
    }

    public String getCar_old() {
        return car_old;
    }

    public String getWork_time() {
        return work_time;
    }

    public String getSalary() {
        return salary;
    }

    public String getService_count() {
        return service_count;
    }

    public String getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }


    public void setName(String name) {
        this.name = name;
    }


    @FXML
    void initialize() {

        // сохранение
        button_save.setOnAction(actionEvent -> {

            last_name = text_last_name.getText().trim();
            first_name = text_first_name.getText().trim();
            second_name = text_second_name.getText().trim();
            address = text_address.getText().trim();
            phone = signUpPhoneNumber.getText().trim();
            login = text_login.getText().trim();
            pass = text_password.getText().trim();

            String lowerCase;
            // проверка фамилии на пустоту
            if (last_name.equals("")) {
                System.out.println("Error: empty last name.");
                text_mistake.setText("Вы не ввели фамилию!");
                flag = false;
            }
            else {
                lowerCase = text_last_name.getText().trim().toLowerCase();
                last_name = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
            }

            // проверка имени на пустоту
            if (first_name.equals("")) {
                System.out.println("Error: empty first name.");
                text_mistake.setText("Вы не ввели имя!");
                flag = false;
            }
            else {
                lowerCase = text_first_name.getText().trim().toLowerCase();
                first_name = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
            }

            try {
                lowerCase = text_second_name.getText().trim().toLowerCase();
                second_name = lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
            } catch (Exception ignored) {}

            // проверка логина на пустоту
            if (login.equals("")) {
                System.out.println("Error: empty login.");
                text_mistake.setText("Вы не ввели логин!");
                flag = false;
            }

            // проверка логина на повторение
            if (!Objects.equals(login, "")) {
                try {
                    String query_clients = "SELECT * FROM " + CLIENTS_TABLE + " WHERE " + CLIENTS_LOGIN + " =?";
                    String query_employees = "SELECT * FROM " + EMPLOYEES_TABLE + " WHERE " + EMPLOYEES_LOGIN + " =?";

                    PreparedStatement statement = Data_base_Con.getInstance().prepareStatement(query_clients);
                    statement.setString(1, login);
                    ResultSet result = statement.executeQuery();
                    if (result.next()) {
                        System.out.println("Error: login already exist.");
                        text_mistake.setText("Логин уже существует!");
                        flag = false;
                    }

                    statement = Data_base_Con.getInstance().prepareStatement(query_employees);
                    statement.setString(1, login);
                    result = statement.executeQuery();
                    if (result.next()) {
                        System.out.println("Error: login already exist.");
                        text_mistake.setText("Логин уже существует!");
                        flag = false;
                    }

                } catch (SQLException | ClassNotFoundException e) { throw new RuntimeException(e); }
            }

            // проверка номера на пустоту
            if (phone.equals("")) {
                System.out.println("Error: empty phone number.");
                text_mistake.setText("Вы не ввели номер телефона!");
                flag = false;
            }

            // проверка номера телефона на корректность
            if (!Objects.equals(phone, "")) {
                try {
                    String regex = "(?:\\+\\d{1,3}|8)[\\s-]?\\(?\\d{3}\\)?[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}";
                    if (!phone.matches(regex)) {
                        System.out.println("Error: incorrect phone number");
                        text_mistake.setText("Некорректный номер телефона!");
                        flag = false;
                    }
                } catch (Exception e) {
                    System.out.println("Error: incorrect phone number.");
                    text_mistake.setText("Некорректный номер телефона!");
                    flag = false;
                }
            }

            // проверка пароля на пустоту
            if (pass.equals("")) {
                System.out.println("Error: empty pass.");
                text_mistake.setText("Вы не ввели пароль!");
                flag = false;
            }


            // проверка пароля пользователя
            if (flag) {
                PassController.setId(9);
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pass.fxml"));
                Scene scene;
                try {
                    scene = new Scene(fxmlLoader.load(), 400, 250);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
