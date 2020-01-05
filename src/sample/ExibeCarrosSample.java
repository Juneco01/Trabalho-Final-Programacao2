package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;




import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExibeCarrosSample implements Initializable {
    @FXML
    private TableView<Carro> tableView;



    //Initialize da classe que exibe os veiculos, cria a table view e percorre a arrayList com todas as informações
    @Override
    public void initialize(URL url, ResourceBundle rb){

        TableColumn<Carro,String> propCol = new TableColumn<>("PROPRIETARIO");
        TableColumn<Carro,String> placaCol = new TableColumn<>("PLACA");
        TableColumn<Carro,String> corCol = new TableColumn<>("COR");
        TableColumn<Carro,String> marcaCol = new TableColumn<>("MARCA");
        TableColumn<Carro,String> modeloCol = new TableColumn<>("MODELO");
        TableColumn<Carro,String> chassiCol = new TableColumn<>("CHASSI");
        TableColumn<Carro,String> anoCol = new TableColumn<>("ANO");
        TableColumn<Carro,String> dataCompraCol = new TableColumn<>("DATACOMPRA");

        tableView.getColumns().addAll(propCol,placaCol,marcaCol,modeloCol,anoCol,chassiCol,corCol, dataCompraCol);

        final ObservableList<Carro> dados = FXCollections.observableArrayList();
        for (Carro carro : CadastroSample.carros){
            dados.add(carro);
        }

        propCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("proprietario"));
        placaCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("placa"));
        corCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("cor"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("marca"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("modelo"));
        chassiCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("chassi"));
        dataCompraCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("dataCompra"));
        anoCol.setCellValueFactory(new PropertyValueFactory<Carro,String>("ano"));

        tableView.setItems(dados);
    }









    @FXML
    void handleVoltarClick(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene menu_scene = new Scene(menu);
        Stage menu_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        menu_stage.setScene(menu_scene);
        menu_stage.show();
    }
}
