package com.tcc.appmarlycordeiro.business;

public class Consulta {

    private String idConsulta;
    private String nomePaciente;
    private String tipoConsulta;
    private String nomeTerapeuta;
    private String dataConsulta;
    private String horaConsulta;
    private String observacaoConsulta;

    public Consulta() {  }

    public String getIdConsulta() { return idConsulta; }
    public void setIdConsulta(String idConsulta) { this.idConsulta = idConsulta; }

    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String paciente) { this.nomePaciente = paciente; }

    public String getTipoConsulta() { return tipoConsulta; }
    public void setTipoConsulta(String tipoConsulta) { this.tipoConsulta = tipoConsulta; }

    public String getNomeTerapeuta() { return nomeTerapeuta; }
    public void setNomeTerapeuta(String nomeTerapeuta) { this.nomeTerapeuta = nomeTerapeuta; }

    public String getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(String dataConsulta) { this.dataConsulta = dataConsulta; }

    public String getHoraConsulta() { return horaConsulta; }
    public void setHoraConsulta(String horaConsulta) { this.horaConsulta = horaConsulta; }

    public String getObservacaoConsulta() { return observacaoConsulta; }
    public void setObservacaoConsulta(String observacaoConsulta) { this.observacaoConsulta = observacaoConsulta; }
}