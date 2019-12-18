package sample;

import javafx.beans.property.SimpleStringProperty;

public class Carro {
    private SimpleStringProperty proprietario = new SimpleStringProperty();
    private SimpleStringProperty placa = new SimpleStringProperty();
    private SimpleStringProperty cor = new SimpleStringProperty();
    private SimpleStringProperty marca = new SimpleStringProperty();
    private SimpleStringProperty modelo = new SimpleStringProperty();
    private SimpleStringProperty chassi = new SimpleStringProperty();
    private SimpleStringProperty ano = new SimpleStringProperty();
    private SimpleStringProperty dataCompra = new SimpleStringProperty();


    public String getProprietario() {
        return proprietario.get();
    }

    public void setProprietario(String proprietario) {
        this.proprietario.set(proprietario);
    }

    public String getPlaca() {
        return placa.get();
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }

    public String getCor() {
        return cor.get();
    }

    public void setCor(String cor) {
        this.cor.set(cor);
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getChassi() {
        return chassi.get();
    }

    public void setChassi(String chassi) {
        this.chassi.set(chassi);
    }

    public int getAno() {
        return Integer.parseInt(ano.get());
    }

    public void setAno(int ano) {
        this.ano.set(String.valueOf(ano));
    }

    public String getDataCompra() {
        return dataCompra.get();
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra.set(dataCompra);
    }
}
