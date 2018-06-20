package com.tcc.appmarlycordeiro.business;

public class Evento {

    private String idEvento;
    private String tipoEvento;
    private String nomeTerapeuta;
    private String localEvento;
    private String localUrl;
    private String dataEvento;
    private String horaEvento;
    private String observacaoEvento;

    public Evento() {  }

    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getNomeTerapeuta() { return nomeTerapeuta; }
    public void setNomeTerapeuta(String nomeTerapeuta) { this.nomeTerapeuta = nomeTerapeuta; }

    public String getLocalEvento() { return localEvento; }
    public void setLocalEvento(String localEvento) { this.localEvento = localEvento; }

    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }

    public String getHoraEvento() { return horaEvento; }
    public void setHoraEvento(String horaEvento) { this.horaEvento = horaEvento; }

    public String getObservacaoEvento() { return observacaoEvento; }
    public void setObservacaoEvento(String observacaoEvento) { this.observacaoEvento = observacaoEvento; }

    public String getLocalUrl() { return localUrl; }
    public void setLocalUrl(String localUrl) { this.localUrl = localUrl; }
}
