package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.model.dao.MunicipioDAO;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-30
 * Time: 16:09
 */

public class WsEnderecoCepPostmon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String complemento = "";
    private String bairro;
    private String cidade;
    private String logradouro;
    private Estado_info estado_info;
    private String cep;
    private String estado;
    private Cidade_info cidade_info;

    public WsEnderecoCepPostmon() {
    }

    public WsEnderecoCepPostmon(String complemento, String bairro, String cidade, String logradouro, Estado_info estado_info, String cep, String estado) {
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.estado_info = estado_info;
        this.cep = cep;
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro.toUpperCase().trim();
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Municipio getCidade_DB() {
        return new MunicipioDAO().getAll(Municipio.class, null, null, null, null).stream()
                .filter(municipio -> municipio.getIbge_codigo().equals(getCidade_info().getCodigo_ibge()))
                .findFirst().orElse(new MunicipioDAO().getById(Municipio.class, 112L));
    }

    public String getLogradouro() {
        return logradouro.toUpperCase().trim();
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Estado_info getEstado_info() {
        return estado_info;
    }

    public void setEstado_info(Estado_info estado_info) {
        this.estado_info = estado_info;
    }

    public String getCep() {
        return cep.replaceAll("\\D", "");
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cidade_info getCidade_info() {
        return cidade_info;
    }

    public void setCidade_info(Cidade_info cidade_info) {
        this.cidade_info = cidade_info;
    }

    @Override
    public String toString() {
        return "WsEnderecoCepPostmon{" +
                "complemento='" + complemento + '\'' +
                "bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", estado_info=" + estado_info +
                ", cep='" + cep + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade_info=" + cidade_info +
                '}';
    }
}

class Estado_info implements Serializable {
    private static final long serialVersionUID = 1L;

    private String area_km2;
    private String codigo_ibge;
    private String nome;

    public Estado_info() {
    }

    public Estado_info(String area_km2, String codigo_ibge, String nome) {
        this.area_km2 = area_km2;
        this.codigo_ibge = codigo_ibge;
        this.nome = nome;
    }

    public String getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(String area_km2) {
        this.area_km2 = area_km2;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(String codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estado_info{" +
                "area_km2='" + area_km2 + '\'' +
                ", codigo_ibge='" + codigo_ibge + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}

class Cidade_info implements Serializable {
    private static final long serialVersionUID = 1L;

    private String area_km2;
    private String codigo_ibge;

    public Cidade_info() {
    }

    public Cidade_info(String area_km2, String codigo_ibge) {
        this.area_km2 = area_km2;
        this.codigo_ibge = codigo_ibge;
    }

    public String getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(String area_km2) {
        this.area_km2 = area_km2;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(String codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    @Override
    public String toString() {
        return "Cidade_info{" +
                "area_km2='" + area_km2 + '\'' +
                ", codigo_ibge='" + codigo_ibge + '\'' +
                '}';
    }
}
