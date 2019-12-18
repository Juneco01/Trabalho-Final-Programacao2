package sample;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File listaCarros = new File("/home/juneco/IdeaProjects/trabalho/listagemcarros.csv");
        try {
            Scanner leitor = new Scanner(listaCarros);
            String linhasDoArquivo;
            leitor.nextLine();
            while (leitor.hasNext()) {
                linhasDoArquivo = leitor.nextLine();


                String[] l = linhasDoArquivo.split(",");
                l[1] = l[1].replace(" ", "");
                l[2] = l[2].replace(" ", "");
                l[3] = l[3].replace(" ", "");
                l[4] = l[4].replace(" ", "");
                l[5] = l[5].replace(" ", "");
                l[6] = l[6].replace(" ", "");
                l[7] = l[7].replace(" ", "");
                int i = 0;
                for (Carro cars : CadastroSample.carros){

                    if (l[1].equals(cars.getPlaca())) {
                        i = 1;
                        //System.out.println("Proprietario L eh "  + l[0] + " Placa dele: " + l[1] + "  Placa dentro de carros: " + cars.getPlaca() );
                        break;
                    }
                }
                if (i == 1) continue;
                Carro car = new Carro();
                car.setProprietario(l[0]);
                car.setPlaca(l[1]);
                car.setMarca(l[2]);
                car.setModelo(l[3]);
                car.setChassi(l[4]);
                car.setCor(l[5]);
                car.setAno(Integer.parseInt(l[6]));
                car.setDataCompra(l[7]);
                CadastroSample.carros.add(car);
                System.out.println(CadastroSample.carros.size());
            }
            leitor.close();
        } catch (FileNotFoundException e) { System.out.println(e); }

    }





    @FXML
    void handleOpcoesClick(ActionEvent event) throws IOException {

        Parent telaOpcoes = FXMLLoader.load(getClass().getResource("opcoeSample.fxml"));
        setarTela(telaOpcoes,event);


    }

    @FXML
    void cadastrarHandleClick(ActionEvent event) throws IOException {
        Parent telaCadastro = FXMLLoader.load(getClass().getResource("cadastroSample.fxml"));
        setarTela(telaCadastro, event);
    }

 

    @FXML
    void showCarrosHandleClick(ActionEvent event) throws IOException{
        Parent telaShowCarros= FXMLLoader.load(getClass().getResource("ExibeCarrosSample.fxml"));
        setarTela(telaShowCarros, event);

    }

    
    void setarTela(Parent telaOpcoes, ActionEvent event){
        Scene telaOpcoes_scene = new Scene(telaOpcoes);
        Stage telaOpcoes_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        telaOpcoes_stage.setScene(telaOpcoes_scene);
        telaOpcoes_stage.show();

    }

    @FXML
    void handleSairClick(ActionEvent event) throws  IOException {


        if (confirmSaida()) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOnCloseRequest(e -> Platform.exit());
            System.exit(0);
        }
    }

    private boolean confirmSaida(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Janela de Confirmação");
        alert.setHeaderText("Você realmente deseja sair?");


        ButtonType botao1 = new ButtonType("Sim");
        ButtonType botao2 = new ButtonType("Não");

        alert.getButtonTypes().setAll(botao2,botao1);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == botao1) return true;
        return false;


    }



}


