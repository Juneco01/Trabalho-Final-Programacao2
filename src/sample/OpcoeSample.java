package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.*;
import java.util.Optional;

public class OpcoeSample{


    @FXML
    private JFXRadioButton radioPrimeiraCompra;

    @FXML
    private JFXRadioButton radioUltimaCompra;

    @FXML
    private JFXRadioButton radioQuantidade;

    @FXML
    private JFXRadioButton radioRemoveUltimo;

    @FXML
    private JFXRadioButton radioTerceiroCarro;

    @FXML
    private JFXRadioButton radioRemoveTerceiro;

    @FXML
    private Label labelOpcoes;


    @FXML
    void handleVoltarMenu(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menu_scene = new Scene(menu);
        Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menu_stage.setScene(menu_scene);
        menu_stage.show();

    }

    //Verifica qual opção o usuario escolheu, e seta os textos na tela
    @FXML
    void handleIrClick(ActionEvent event) throws IOException{
        int quantCarros = CadastroSample.carros.size();
        boolean verifica = quantCarros >= 1;
        if (verifica == false) {
            labelOpcoes.setFont(new Font("comicsans", 30));
            labelOpcoes .setText("Nenhum Carro Cadastrado");
        }else if(radioQuantidade.isSelected()){
            labelOpcoes.setFont(new Font("comicsans", 30));
            labelOpcoes.setText(quantCarros + " Carros cadastrados ");

        }else if (radioPrimeiraCompra.isSelected()){
            int indice = indiceCompra("Menor");
            labelOpcoes.setFont(new Font("comicsans", 15));
            labelOpcoes.setText("Proprietario: " + CadastroSample.carros.get(indice).getProprietario() +
                    " \nPlaca: " +  CadastroSample.carros.get(indice).getPlaca() +
                    " \nMarca: " + CadastroSample.carros.get(indice).getMarca() +
                    " \nModelo: " +  CadastroSample.carros.get(indice).getModelo() +
                    " \nData da Compra: " + CadastroSample.carros.get(indice).getDataCompra()
                );

        }else if (radioUltimaCompra.isSelected()){
            int indice = indiceCompra("Maior");
            labelOpcoes.setFont(new Font("comicsans", 15));
            labelOpcoes.setText("Proprietario: " + CadastroSample.carros.get(indice).getProprietario() +
                    " \nPlaca: " +  CadastroSample.carros.get(indice).getPlaca() +
                    " \nMarca: " + CadastroSample.carros.get(indice).getMarca() +
                    " \nModelo: " +  CadastroSample.carros.get(indice).getModelo() +
                    " \nData da Compra: " + CadastroSample.carros.get(indice).getDataCompra()
            );

        }else if( radioTerceiroCarro.isSelected()){

            if (CadastroSample.carros.size() >= 3){
                int indice = 2;
                labelOpcoes.setFont(new Font("comicsans", 15));
                labelOpcoes.setText("Terceiro Carro Cadastrado" +
                        "\n\nProprietario: " + CadastroSample.carros.get(indice).getProprietario() +
                        " \nPlaca: " +  CadastroSample.carros.get(indice).getPlaca() +
                        " \nMarca: " + CadastroSample.carros.get(indice).getMarca() +
                        " \nModelo: " +  CadastroSample.carros.get(indice).getModelo() +
                        " \nData da Compra: " + CadastroSample.carros.get(indice).getDataCompra()
                );
            }else{
                labelOpcoes.setFont(new Font("comicsans", 20));
                labelOpcoes.setText("O numero de carros cadastrados é menor que 3");
            }
        }else if(radioRemoveUltimo.isSelected()){

            int tamanho = CadastroSample.carros.size() - 1;

            boolean resultado  =confirmAlerta(tamanho);

            if (resultado) {
                Carro morto = CadastroSample.carros.remove(tamanho);
                excluiCSV();
                labelOpcoes.setFont(new Font("comicsans", 15));
                labelOpcoes.setText("!!!!Carro Removido!!!!" +
                        "\n\nProprietario: " + morto.getProprietario() +
                        " \nPlaca: " + morto.getPlaca() +
                        " \nMarca: " + morto.getMarca() +
                        " \nModelo: " + morto.getModelo() +
                        " \nData da Compra: " + morto.getDataCompra()
                );
            }
        }else if(radioRemoveTerceiro.isSelected()){

            if (CadastroSample.carros.size() >= 3) {

                if (confirmAlerta(2)) {
                    Carro morto = CadastroSample.carros.remove(2);
                    excluiCSV();
                    labelOpcoes.setFont(new Font("comicsans", 15));
                    labelOpcoes.setText("!!!!Carro Removido!!!!" +
                            "\n\nProprietario: " + morto.getProprietario() +
                            " \nPlaca: " + morto.getPlaca() +
                            " \nMarca: " + morto.getMarca() +
                            " \nModelo: " + morto.getModelo() +
                            " \nData da Compra: " + morto.getDataCompra()
                    );
                }
            }else{
                labelOpcoes.setFont(new Font("comicsans", 20));
                labelOpcoes.setText("O numero de carros cadastrados é menor que 3");
            }


        }else{
            labelOpcoes.setFont(new Font("comicsans", 30));
            labelOpcoes.setText("Selecione uma das opções");

        }


    }






    //Função que recebe uma data e transforma em dias (usada para verificar qual compra foi feita primeiro)
    public int transformaEmDias(String data){
        String Sano =  ""; String Smes = ""; String Sdia = "";
        Sano += data.charAt(0); Sano += data.charAt(1); Sano += data.charAt(2); Sano += data.charAt(3);
        Smes += data.charAt(5); Smes += data.charAt(6);
        Sdia += data.charAt(8); Sdia += data.charAt(9);

        int ano = Integer.parseInt(Sano);
        int mes = Integer.parseInt(Smes);
        int dias = Integer.parseInt(Sdia);


        int retorno = (ano * 365) + (mes * 31) + dias;


        return retorno;
    }

    //Retorna o indice da compra mais antiga
    public  int indiceCompra(String tipoCompra){

        int menorV = transformaEmDias(CadastroSample.carros.get(0).getDataCompra());
        int valor = 0;
        int indice = 0;


        if (tipoCompra.equals("Menor")) {
            for (Carro cars : CadastroSample.carros) {
                if (transformaEmDias(cars.getDataCompra()) < menorV) {
                    menorV = (transformaEmDias(cars.getDataCompra()));
                    indice = valor;
                }
                ++valor;
            }
        }else {
            for (Carro cars : CadastroSample.carros) {
                if (transformaEmDias(cars.getDataCompra()) > menorV) {
                    menorV = (transformaEmDias(cars.getDataCompra()));
                    indice = valor;
                }
                ++valor;
            }
        }

        return indice;



    }

    //Função que recria(atualiza) o csv após algum veiculo ser deletado.
    public void excluiCSV(){
        String tempFile = "temp.txt";
        File oldfile = new File("listagemcarros.csv");
        File newFile = new File(tempFile);


        try{

            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("Proprietario, Placa, Marca, Modelo, Chassi, Cor, Ano, DataCompra");
            for (Carro car : CadastroSample.carros){
                pw.println(car.getProprietario() + ", " + car.getPlaca()  + ", " +
                        car.getMarca() + ", " + car.getModelo() + ", " +
                        car.getChassi() + ", " + car.getCor() + ", " +
                        car.getAno() +  ", " + car.getDataCompra()
                );
            }

            pw.flush();
            pw.close();
            oldfile.delete();
            File dump = new File("listagemcarros.csv");
            newFile.renameTo(dump);



        }catch(Exception e){}

    }
    //Gera um alertBox caso o usuario queira sair do programa
    private boolean confirmAlerta(int indice){
        Carro aux = CadastroSample.carros.get(indice);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Remoção");
        alert.setHeaderText("Você realmente deseja remover: " + CadastroSample.carros.get(indice).getProprietario()+ " ?");
        alert.setContentText(
                "\n\nProprietario: " + aux.getProprietario() +
                " \nPlaca: " + aux.getPlaca() +
                " \nMarca: " + aux.getMarca() +
                " \nModelo: " + aux.getModelo() +
                " \nData da Compra: " + aux.getDataCompra()
        );


        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.get() == ButtonType.OK) return true;
        return false;


    }

}


