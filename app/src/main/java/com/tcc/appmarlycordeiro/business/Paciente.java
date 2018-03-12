package com.tcc.appmarlycordeiro.business;

public class Paciente {

    private String idPac;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String estado;
    private String cidade;
    private String sexo;
    private String observacao;

    public Paciente() {  }

    public String getIdPac() { return idPac; }
    public void setIdPac(String idpac) { this.idPac = idpac; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String toString(){ return nome; }

}
