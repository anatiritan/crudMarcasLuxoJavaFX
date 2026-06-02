package com.template;

public class MarcasLuxoDTO {

    private int idMarca;
    private String nomeMarca;
    private String estilista;
    private String paisOrigem;
    private int anoFundacao;
    private String tipo;

    // Método para pegar o id da marca
    public int getIdMarca() {
        return idMarca;
    }

    // Método para definir o id da marca
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    // Retorna o nome da marca
    public String getNomeMarca() {
        return nomeMarca;
    }

    // Define o nome da marca
    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }

    // Retorna o nome do estilista
    public String getEstilista() {
        return estilista;
    }

    // Define o estilista
    public void setEstilista(String estilista) {
        this.estilista = estilista;
    }

    // Retorna o país de origem
    public String getPaisOrigem() {
        return paisOrigem;
    }

    // Define o país de origem
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    // Retorna o ano de fundação
    public int getAnoFundacao() {
        return anoFundacao;
    }

    // Define o ano de fundação
    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    // Retorna o tipo da marca
    public String getTipo() {
        return tipo;
    }

    // Define o tipo da marca
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}