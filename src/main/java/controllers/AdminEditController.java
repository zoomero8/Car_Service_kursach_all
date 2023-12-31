package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Protect_constants;
import main.Data_base_Con;
import main.Main;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class AdminEditController extends Protect_constants {

    @FXML
    private Button button_save;

    @FXML
    private TextField text_login;

    @FXML
    private TextField text_pass;

    @FXML
    private Text text_mistake;

    private static String old_login;

    private static String login;

    private static String pass;

    private static boolean flag = true;

    @FXML
    void initialize() {

        AdminEditController.old_login = AdminMainController.getLogin();

        // активация кнопки сохранения
        button_save.setOnAction(actionEvent -> {

            AdminEditController.login = text_login.getText().trim();
            AdminEditController.pass = text_pass.getText().trim();

            // логин
            if (!Objects.equals(AdminEditController.login, "")) {

                try {
                    String query = "SELECT * FROM " + CLIENTS_TABLE + " WHERE " + CLIENTS_LOGIN + " =?";
                    PreparedStatement preparedStatement = Data_base_Con.getInstance().prepareStatement(query);
                    preparedStatement.setString(1, AdminEditController.login);
                    ResultSet result = preparedStatement.executeQuery();
                    if (result.next()) {
                        System.out.println("Error: login already exist.");
                        text_mistake.setText("Логин уже существует!");
                        flag = false;
                    }

                    query = "SELECT * FROM " + EMPLOYEES_TABLE + " WHERE " + EMPLOYEES_LOGIN + " =?";
                    preparedStatement = Data_base_Con.getInstance().prepareStatement(query);
                    preparedStatement.setString(1, AdminEditController.login);
                    result = preparedStatement.executeQuery();
                    if (result.next()) {
                        System.out.println("Error: login already exist.");
                        text_mistake.setText("Логин уже существует!");
                        flag = false;
                    }

                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("Error: incorrect login.");
                    text_mistake.setText("Логин введён некорректно!");
                    flag = false;
                }

            }

            if (flag) {
                PassController.setId(6);
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

    public static void save() throws SQLException, ClassNotFoundException {

        // логин
        if (!Objects.equals(login, "")) {
            String sqlAlterTable = "UPDATE " + EMPLOYEES_TABLE + " SET " +
                    EMPLOYEES_LOGIN + " = '" + AdminEditController.login + "' WHERE " +
                    EMPLOYEES_LOGIN + " = '" + AdminEditController.old_login + "';";
            Connection connection = Data_base_Con.getInstance();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlAlterTable);
            System.out.println("Success!");
            AdminMainController.getUser().setLogin(login);
            AdminEditController.old_login = AdminEditController.login;
        }


        // пароль
        if (!Objects.equals(pass, "")) {
            String sqlAlterTable = "UPDATE " + EMPLOYEES_TABLE + " SET " +
                    EMPLOYEES_PASSWORD + " = '" + AdminEditController.pass + "' WHERE " +
                    EMPLOYEES_LOGIN + " = '" + AdminEditController.old_login + "';";
            Connection connection = Data_base_Con.getInstance();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlAlterTable);
            System.out.println("Success!");
            AdminMainController.getUser().setPassword(pass);
            PassController.setPassword(pass);
        }

        Main.changeScene("admin_main.fxml");

    }

}
