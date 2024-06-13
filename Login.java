package com.example.proje;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login {
    @FXML
    private Button cancelButon;
    @FXML
    private Button loginButon;
    @FXML
    private TextField sifreText;
    @FXML
    private TextField kullaniciText;
    @FXML
    private Label kontrol;

    public void cancelButonAction(ActionEvent e) {
        Stage stage = (Stage) cancelButon.getScene().getWindow();
        stage.close();
    }

    public void loginButonAction(ActionEvent e) {
        if (!kullaniciText.getText().isBlank() && !sifreText.getText().isBlank()) {
            validateLogin();
        } else {
            kontrol.setText("Kullanıcı adı ve şifre girin.");
        }
    }

    public void validateLogin() {
        DatabaseConnection connectionHelper = new DatabaseConnection();
        Connection connection = connectionHelper.getConnection();
        if (connection == null) {
            kontrol.setText("ERROR");
            return;}
        String query = "SELECT count(1) FROM kullanici WHERE kullaniciAdi = ? AND sifre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, kullaniciText.getText());
            preparedStatement.setString(2, sifreText.getText());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) == 1) {
                kontrol.setText("Giriş başarılı.");
                switchToHelloView();
            } else {
                kontrol.setText("Giriş yapılamadı.");}
        } catch (SQLException e) {
            kontrol.setText("error");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }}}

    private void switchToHelloView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) loginButon.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
