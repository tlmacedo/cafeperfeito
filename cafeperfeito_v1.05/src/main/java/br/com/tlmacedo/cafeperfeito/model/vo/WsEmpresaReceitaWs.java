package br.com.tlmacedo.cafeperfeito.model.vo;

import br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key;
import br.com.tlmacedo.cafeperfeito.model.dao.MunicipioDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.AtividadeReceitaFederalTipo;
import br.com.tlmacedo.cafeperfeito.service.ServiceMascara;
import br.com.tlmacedo.cafeperfeito.service.ServiceValidarDado;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-01-29
 * Time: 11:44
 */

public class WsEmpresaReceitaWs implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Atividade_principal> atividade_principal;
    private List<Atividades_secundarias> atividades_secundarias;
    private List<Qsa> qsa;
    private Billing billing;
    private Object extra;
    private String data_situacao;
    private String nome;
    private String uf;
    private String telefone;
    private String email;
    private String situacao;
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;
    private String municipio;
    private String abertura;
    private String natureza_juridica;
    private String fantasia;
    private String porte;
    private String cnpj;
    private String ultima_atualizacao;
    private String status;
    private String tipo;
    private String complemento;
    private String efr;
    private String motivo_situacao;
    private String situacao_especial;
    private String data_situacao_especial;
    private String capital_social;

    private String message;


    public WsEmpresaReceitaWs() {
    }

    public WsEmpresaReceitaWs(String data_situacao, String nome, String uf, String telefone, String email, String situacao, String bairro, String logradouro, String numero, String cep, String municipio, String abertura, String natureza_juridica, String fantasia, String porte, String cnpj, String ultima_atualizacao, String status, String tipo, String complemento, String efr, String motivo_situacao, String situacao_especial, String data_situacao_especial, String capital_social, String message) {
        this.data_situacao = data_situacao;
        this.nome = nome;
        this.uf = uf;
        this.telefone = telefone;
        this.email = email;
        this.situacao = situacao;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.municipio = municipio;
        this.abertura = abertura;
        this.natureza_juridica = natureza_juridica;
        this.fantasia = fantasia;
        this.porte = porte;
        this.cnpj = cnpj;
        this.ultima_atualizacao = ultima_atualizacao;
        this.status = status;
        this.tipo = tipo;
        this.complemento = complemento;
        this.efr = efr;
        this.motivo_situacao = motivo_situacao;
        this.situacao_especial = situacao_especial;
        this.data_situacao_especial = data_situacao_especial;
        this.capital_social = capital_social;
        this.message = message;
    }

    public List<Atividade_principal> getAtividade_principal() {
        return atividade_principal;
    }

    public List<InfoReceitaFederal> getInfoReceitaFederalList() {
        List<InfoReceitaFederal> list = new ArrayList<>();
        getAtividade_principal().stream()
                .forEach(principal -> list.add(new InfoReceitaFederal(
                        principal.getCode(), principal.getText(),
                        AtividadeReceitaFederalTipo.PRINCIPAL)));
        getAtividades_secundarias().stream()
                .forEach(secundaria -> list.add(new InfoReceitaFederal(
                        secundaria.getCode(), secundaria.getText(),
                        AtividadeReceitaFederalTipo.SECUNDARIA)));
        if (getCapital_social() != null)
            list.add(new InfoReceitaFederal(
                    "Capital social", getCapital_social(),
                    AtividadeReceitaFederalTipo.QSA));
        getQsa().stream()
                .forEach(qsa1 -> list.add(new InfoReceitaFederal(
                        qsa1.getQual(), qsa1.getNome(),
                        AtividadeReceitaFederalTipo.QSA)));
        return list;
    }

    public void setAtividade_principal(List<Atividade_principal> atividade_principal) {
        this.atividade_principal = atividade_principal;
    }

    public List<Atividades_secundarias> getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(List<Atividades_secundarias> atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public List<Qsa> getQsa() {
        return qsa;
    }

    public void setQsa(List<Qsa> qsa) {
        this.qsa = qsa;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getData_situacao() {
        return data_situacao;
    }

    public void setData_situacao(String data_situacao) {
        this.data_situacao = data_situacao;
    }

    public String getNome() {
        return nome.toUpperCase().trim();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<String> getTelefoneList() {
        List<String> list = ServiceValidarDado.getTelefoneList(getTelefone());
        return list;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getEmailList() {
        List<String> list = ServiceValidarDado.getEmailsList(getEmail());
        return list;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getBairro() {
        return bairro.toUpperCase().trim();
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro.toUpperCase().trim();
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero.trim();
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep.replaceAll("\\D", "").trim();
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Municipio getMunicipio_BD() {
        return new MunicipioDAO().getAll(Municipio.class, null, null, null, null).stream()
                .filter(municipio -> municipio.getDescricao().equals(getMunicipio()))
                .findFirst().orElse(new MunicipioDAO().getById(Municipio.class, 112L));
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getAbertura() {
        return abertura;
    }

    public LocalDateTime getAbertura_LocalDAteTime() {
        return LocalDate.parse(getAbertura(), Convert_Date_Key.DTF_DATA).atTime(0, 0, 0);
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getNatureza_juridica() {
        return natureza_juridica.toUpperCase().trim();
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public String getFantasia() {
        return fantasia.toUpperCase().trim();
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getCnpj() {
        return cnpj.replaceAll("\\D", "");
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(String ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComplemento() {
        return complemento.toUpperCase().trim();
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getMotivo_situacao() {
        return motivo_situacao;
    }

    public void setMotivo_situacao(String motivo_situacao) {
        this.motivo_situacao = motivo_situacao;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getCapital_social() {
        return ServiceMascara.getMoeda(capital_social, 2);
    }

    public void setCapital_social(String capital_social) {
        this.capital_social = capital_social;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WsEmpresaReceitaWs{" +
                "atividade_principal=" + atividade_principal +
                ", atividades_secundarias=" + atividades_secundarias +
                ", qsa=" + qsa +
                ", billing=" + billing +
                ", extra=" + extra +
                ", data_situacao='" + data_situacao + '\'' +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", situacao='" + situacao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                ", municipio='" + municipio + '\'' +
                ", abertura='" + abertura + '\'' +
                ", natureza_juridica='" + natureza_juridica + '\'' +
                ", fantasia='" + fantasia + '\'' +
                ", porte='" + porte + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", ultima_atualizacao='" + ultima_atualizacao + '\'' +
                ", status='" + status + '\'' +
                ", tipo='" + tipo + '\'' +
                ", complemento='" + complemento + '\'' +
                ", efr='" + efr + '\'' +
                ", motivo_situacao='" + motivo_situacao + '\'' +
                ", situacao_especial='" + situacao_especial + '\'' +
                ", data_situacao_especial='" + data_situacao_especial + '\'' +
                ", capital_social='" + capital_social + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

class Qsa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String qual;
    private String pais_origem;
    private String nome_rep_legal;
    private String qual_rep_legal;

    public Qsa() {
    }

    public Qsa(String nome, String qual, String pais_origem, String nome_rep_legal, String qual_rep_legal) {
        this.nome = nome;
        this.qual = qual;
        this.pais_origem = pais_origem;
        this.nome_rep_legal = nome_rep_legal;
        this.qual_rep_legal = qual_rep_legal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getPais_origem() {
        return pais_origem;
    }

    public void setPais_origem(String pais_origem) {
        this.pais_origem = pais_origem;
    }

    public String getNome_rep_legal() {
        return nome_rep_legal;
    }

    public void setNome_rep_legal(String nome_rep_legal) {
        this.nome_rep_legal = nome_rep_legal;
    }

    public String getQual_rep_legal() {
        return qual_rep_legal;
    }

    public void setQual_rep_legal(String qual_rep_legal) {
        this.qual_rep_legal = qual_rep_legal;
    }

    @Override
    public String toString() {
        return "Qsa{" +
                "nome='" + nome + '\'' +
                ", qual='" + qual + '\'' +
                ", pais_origem='" + pais_origem + '\'' +
                ", nome_rep_legal='" + nome_rep_legal + '\'' +
                ", qual_rep_legal='" + qual_rep_legal + '\'' +
                '}';
    }
}

class Atividade_principal implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String text;

    public Atividade_principal() {
    }

    public Atividade_principal(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Atividade_principal{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

class Atividades_secundarias implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String text;

    public Atividades_secundarias() {
    }

    public Atividades_secundarias(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Atividades_secundarias{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

class Billing implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean free;
    private boolean database;

    public Billing() {
    }

    public Billing(boolean free, boolean database) {
        this.free = free;
        this.database = database;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isDatabase() {
        return database;
    }

    public void setDatabase(boolean database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "free=" + free +
                ", database=" + database +
                '}';
    }
}