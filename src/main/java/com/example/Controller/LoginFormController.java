package com.example.Controller;

import com.example.ClientHandler.ClientHandler;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class LoginFormController {

    public AnchorPane rootNode;

    @FXML
    private JFXTextField txtName;

    private ServerSocket serverSocket;
    static String name;
    static String filePath;

    private void closeEverything(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void joinOnAction(ActionEvent event) throws IOException {
        name = txtName.getText();

        if(name.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please enter your user name").show();
            return;
        }

        txtName.clear();


        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/ChatRoomForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(name);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    public void nameOnAction(ActionEvent event) throws IOException {
         joinOnAction(event);
    }
}
