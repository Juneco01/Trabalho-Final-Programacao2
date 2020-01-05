package sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.regex.*;




import java.io.IOException;
import java.util.ArrayList;

public class CadastroSample {

    public static ArrayList<Carro> carros = new ArrayList<Carro>();
    @FXML
    private JFXTextField placaField;
    @FXML
    private JFXTextField propField;
    @FXML
    private JFXTextField marcaField;
    @FXML
    private JFXTextField modeloField;
    @FXML
    private JFXTextField corField;
    @FXML
    private JFXTextField chassiField;
    @FXML
    private DatePicker dataCompraField;
    @FXML
    private JFXTextField anoField;
    @FXML
    private Label mensagemCadastro;






    @FXML
    void handleVoltarClick(ActionEvent event) throws IOException {

        if (confirmSaida()) {
            Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene menu_scene = new Scene(menu);
            Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            menu_stage.setScene(menu_scene);
            menu_stage.show();
        }
    }

    //Função que lida com o cadastro dos carros
    @FXML
    void handleCadastrarClick(ActionEvent event) throws  IOException{
        try {

            int controle = 1;

            Carro novoCarro = criaCarro();

            if (!verificaPlaca(novoCarro.getPlaca())) {

                mensagemCadastro.setText("Placa Invalida");
                placaField.clear();
                controle = 0;
            } else if (!verificaCarroIgual(novoCarro.getPlaca())) {

                mensagemCadastro.setText("O Carro já foi cadastrado");
                placaField.clear();
                controle = 0;

            }

            boolean resultado = confirmAlerta(novoCarro);
            if (resultado) {
                if (controle == 1) {
                    gravaCsv(novoCarro);
                    carros.add(novoCarro);
                    mensagemCadastro.setText("Carro Cadastrado");
                    deletaField();
                }
            }
        }catch (RuntimeException e){
            mensagemCadastro.setText("Por favor insira as informações Corretamente");
        }
    }

    //Função que seta todos os atributos de um objeto carro, retorna o carro no final.
    Carro criaCarro(){

        Carro car = new Carro();
        car.setAno(Integer.parseInt(anoField.getText()));
        car.setCor(corField.getText());
        car.setChassi(chassiField.getText());
        car.setMarca(marcaField.getText());
        car.setModelo(modeloField.getText());
        car.setPlaca(placaField.getText());
        car.setProprietario(propField.getText());
        car.setDataCompra(dataCompraField.getValue().toString());
        return car;
    }

    //Limpa os textFields apos um cadastro
    void deletaField(){

        corField.clear();
        chassiField.clear();
        propField.clear();
        anoField.clear();
        marcaField.clear();
        modeloField.clear();
        placaField.clear();
        dataCompraField.getEditor().clear();

    }

    //Função que verifica a placa com a expressão regular
    boolean verificaPlaca(String placa){

        Pattern pattern  = Pattern.compile("[A-Z]{3}[-][0-9]{4}");
        Matcher matcher = pattern.matcher(placa);
        return matcher.matches();

    }

    //Verifica se não já existe um carro igual (com a mesma placa)
    boolean verificaCarroIgual(String placa){

        for (Carro cars : this.carros){
            System.out.println("Placa do for : " + cars.getPlaca() + "Placa da String: " + placa);
            if (cars.getPlaca().equals(placa)) {
                return false;
            }
        }

        return true;
    };

    //Adiciona o novo carro no arquivo de texto
    void gravaCsv(Carro car){

        try {
            FileWriter fw = new FileWriter("listagemcarros.csv",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(car.getProprietario() + ", " + car.getPlaca()  + ", " +
                       car.getMarca() + ", " + car.getModelo() + ", " +
                        car.getChassi() + ", " + car.getCor() + ", " +
                        car.getAno() +  ", " + car.getDataCompra()
            );

            pw.flush();
            pw.close();

        }catch (Exception e){

        }

    }

    //Gera um alertBox de confirmação de cadastro
    private boolean confirmAlerta(Carro aux){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Você realmente deseja Adicionar: " + aux.getProprietario()+ " ?");
        alert.setContentText(
                "\n\nProprietario: " + aux.getProprietario() +
                        " \nPlaca: " + aux.getPlaca() +
                        " \nMarca: " + aux.getMarca() +
                        " \nModelo: " + aux.getModelo() +
                        " \nAno: " + aux.getAno() +
                        " \nChassi: " + aux.getChassi() +
                        " \nCor: " + aux.getCor() +
                        " \nData da Compra: " + aux.getDataCompra()
        );


        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == ButtonType.OK) return true;
        return false;


    }

    //alertbox de confirmação de saida
    private boolean confirmSaida(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Janela de Confirmação");
        alert.setHeaderText("Você realmente deseja Voltar?");
        alert.setContentText("Dados não salvos serão perdidos");

        ButtonType botao1 = new ButtonType("Sim");
        ButtonType botao2 = new ButtonType("Não");

        alert.getButtonTypes().setAll(botao2,botao1);

        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == botao1) return true;
        return false;


    }




}
