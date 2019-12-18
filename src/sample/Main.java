package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application{




    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SystemCar");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {

                if (!confirmSaida())
                    event.consume();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }


    private boolean confirmSaida() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Janela de Confirmação");
        alert.setHeaderText("Você realmente deseja sair?");

        ButtonType botao1 = new ButtonType("Sim");
        ButtonType botao2 = new ButtonType("Não");

        alert.getButtonTypes().setAll(botao2, botao1);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == botao1) return true;
        return false;
    }

}
