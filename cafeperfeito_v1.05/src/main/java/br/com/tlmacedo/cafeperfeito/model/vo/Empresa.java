package br.com.tlmacedo.cafeperfeito.model.vo;


import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.ClassificacaoJuridica;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.EnderecoTipo;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.SituacaoNoSistema;
import br.com.tlmacedo.cafeperfeito.service.ServiceConsultaWebServices;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Empresa")
@Table(name = "empresa")
public class Empresa extends RecursiveTreeObject<Empresa> implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty(0);
    private BooleanProperty lojaSistema = new SimpleBooleanProperty(false);
    private IntegerProperty classifJuridica = new SimpleIntegerProperty(0);
    private StringProperty cnpj = new SimpleStringProperty("");
    private BooleanProperty ieIsento = new SimpleBooleanProperty(false);
    private StringProperty ie = new SimpleStringProperty("");
    private StringProperty razao = new SimpleStringProperty("");
    private StringProperty fantasia = new SimpleStringProperty("");
    private BooleanProperty cliente = new SimpleBooleanProperty(true);
    private BooleanProperty fornecedor = new SimpleBooleanProperty(false);
    private BooleanProperty transportadora = new SimpleBooleanProperty(false);
    private IntegerProperty situacao = new SimpleIntegerProperty(0);
    private Usuario usuarioCadastro = new Usuario();
    private ObjectProperty<LocalDateTime> dataCadastro = new SimpleObjectProperty<>(LocalDateTime.now());
    private Usuario usuarioAtualizacao = new Usuario();
    private ObjectProperty<LocalDateTime> dataAtualizacao = new SimpleObjectProperty<>(LocalDateTime.now());
    private ObjectProperty<LocalDateTime> dataAbetura = new SimpleObjectProperty<>(LocalDateTime.now());
    private StringProperty naturezaJuridica = new SimpleStringProperty("");
    private StringProperty observacoes = new SimpleStringProperty("");
    private ObjectProperty<BigDecimal> limite = new SimpleObjectProperty<>(BigDecimal.ZERO);
    private IntegerProperty prazo = new SimpleIntegerProperty(0);
    private BooleanProperty diaUtil = new SimpleBooleanProperty(false);

    private List<Endereco> enderecoList = new ArrayList<>();
    private List<EmailHomePage> emailHomePageList = new ArrayList<>();
    private List<Telefone> telefoneList = new ArrayList<>();
    private List<Contato> contatoList = new ArrayList<>();
    private List<InfoReceitaFederal> infoReceitaFederalList = new ArrayList<>();
    private List<EmpresaProdutoValor> empresaProdutoValorList = new ArrayList<>();
    private List<InformacaoBancaria> informacaoBancariaList = new ArrayList<>();

    public Empresa() {
    }

    public Empresa(Boolean lojaSistema, ClassificacaoJuridica classifJuridica, String cnpj, Boolean ieIsento, String ie, String razao, String fantasia, Boolean cliente, Boolean fornecedor, Boolean transportadora, SituacaoNoSistema situacao, Usuario usuarioCadastro, LocalDateTime dataCadastro, Usuario usuarioAtualizacao, LocalDateTime dataAtualizacao, LocalDateTime dataAbetura, String naturezaJuridica, String observacoes, BigDecimal limite, Integer prazo, Boolean diaUtil) {
        this.lojaSistema = new SimpleBooleanProperty(lojaSistema);
        this.classifJuridica = new SimpleIntegerProperty(classifJuridica.getCod());
        this.cnpj = new SimpleStringProperty(cnpj);
        this.ieIsento = new SimpleBooleanProperty(ieIsento);
        this.ie = new SimpleStringProperty(ie);
        this.razao = new SimpleStringProperty(razao);
        this.fantasia = new SimpleStringProperty(fantasia);
        this.cliente = new SimpleBooleanProperty(cliente);
        this.fornecedor = new SimpleBooleanProperty(fornecedor);
        this.transportadora = new SimpleBooleanProperty(transportadora);
        this.situacao = new SimpleIntegerProperty(situacao.getCod());
        this.usuarioCadastro = usuarioCadastro;
        this.dataCadastro = new SimpleObjectProperty<>(dataCadastro);
        this.usuarioAtualizacao = usuarioAtualizacao;
        this.dataAtualizacao = new SimpleObjectProperty<>(dataAtualizacao);
        this.dataAbetura = new SimpleObjectProperty<>(dataAbetura);
        this.naturezaJuridica = new SimpleStringProperty(naturezaJuridica);
        this.observacoes = new SimpleStringProperty(observacoes);
        this.limite = new SimpleObjectProperty<>(limite);
        this.prazo = new SimpleIntegerProperty(0);
        this.diaUtil = new SimpleBooleanProperty(diaUtil);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(length = 1, nullable = false)
    public boolean isLojaSistema() {
        return lojaSistema.get();
    }

    public BooleanProperty lojaSistemaProperty() {
        return lojaSistema;
    }

    public void setLojaSistema(boolean lojaSistema) {
        this.lojaSistema.set(lojaSistema);
    }

    @Column(length = 1, nullable = false)
    public ClassificacaoJuridica getClassifJuridica() {
        return ClassificacaoJuridica.toEnum(classifJuridica.get());
    }

    public void setClassifJuridica(ClassificacaoJuridica classifJuridica) {
        this.classifJuridica.set(classifJuridica.getCod());
    }

    public IntegerProperty classifJuridicaProperty() {
        return classifJuridica;
    }

    public static boolean setWsEmpresa_ReceitaWs(Empresa empresa, JSONObject retWs) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            WsEmpresaReceitaWs wsEmpresaReceitaWs = null;
            if (retWs == null)
                return false;
            wsEmpresaReceitaWs = objectMapper.readValue(new StringReader(retWs.toString()), WsEmpresaReceitaWs.class);
            if (wsEmpresaReceitaWs.getStatus().toLowerCase().equals("error"))
                return false;

            empresa.setCnpj(wsEmpresaReceitaWs.getCnpj());
            empresa.setRazao(wsEmpresaReceitaWs.getNome());
            empresa.setFantasia(wsEmpresaReceitaWs.getFantasia().equals("") ? "***" : wsEmpresaReceitaWs.getFantasia());
            empresa.setDataAbetura(wsEmpresaReceitaWs.getAbertura_LocalDAteTime());
            empresa.setNaturezaJuridica(wsEmpresaReceitaWs.getNatureza_juridica());

            if (wsEmpresaReceitaWs.getSituacao().toLowerCase().equals("ativa")) {
                if (empresa.getEnderecoList().size() == 0)
                    empresa.getEnderecoList().add(new Endereco(EnderecoTipo.PRINCIPAL));
                Endereco endereco = empresa.getEnderecoList().get(0);
                endereco.setCep(wsEmpresaReceitaWs.getCep());
                endereco.setLogradouro(wsEmpresaReceitaWs.getLogradouro());
                endereco.setNumero(wsEmpresaReceitaWs.getNumero());
                endereco.setComplemento(wsEmpresaReceitaWs.getComplemento());
                endereco.setBairro(wsEmpresaReceitaWs.getBairro());
                endereco.setMunicipio(wsEmpresaReceitaWs.getMunicipio_BD());
                endereco.setProx("");
                empresa.getEnderecoList().set(0, endereco);
            } else {
                empresa.setEnderecoList(new ArrayList<>());
                switch (wsEmpresaReceitaWs.getSituacao().toLowerCase()) {
                    case "suspensa":
                        empresa.setSituacao(SituacaoNoSistema.SUSPENSA);
                        break;
                    case "inapta":
                        empresa.setSituacao(SituacaoNoSistema.INAPTA);
                        break;
                    case "baixada":
                        empresa.setSituacao(SituacaoNoSistema.BAIXADA);
                        break;
                }
            }

            wsEmpresaReceitaWs.getEmailList().stream()
                    .forEach(email -> {
                        if (empresa.getEmailHomePageList().stream().noneMatch(e -> e.getDescricao().equals(email)))
                            empresa.getEmailHomePageList().add(new EmailHomePage(email, true, false, false));
                    });

            wsEmpresaReceitaWs.getTelefoneList().stream()
                    .forEach(fone -> {
                        if (empresa.getTelefoneList().stream().noneMatch(t -> t.getDescricao().equals(fone)))
                            empresa.getTelefoneList()
                                    .add(new Telefone(fone, new ServiceConsultaWebServices()
                                            .getOperadoraTelefone(fone)));
                    });

            empresa.setInfoReceitaFederalList(wsEmpresaReceitaWs.getInfoReceitaFederalList());

            int index = 0;
            if (!empresa.getObservacoes().equals("")) {
                if (empresa.getObservacoes().contains("WebService:")) {
                    index = empresa.getObservacoes().indexOf("BD_Ws: [");
                    index += empresa.getObservacoes().substring(index).indexOf("]") + 2;
                }
            }
            empresa.setObservacoes(
                    String.format("WebService:\nreceitaWs:\tgerouCobrança: [%s]\tBD_Ws: [%s]\n%s",
                            !wsEmpresaReceitaWs.getBilling().isFree(),
                            wsEmpresaReceitaWs.getBilling().isDatabase(),
                            (empresa.getObservacoes().length() > index) ? empresa.getObservacoes().substring(index) : ""));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public StringProperty cnpjProperty() {
        return cnpj;
    }

    //@CNPJ
    @Column(length = 14, nullable = false)
    public String getCnpj() {
        return cnpj.get();
    }

    @Column(length = 1, nullable = false)
    public boolean isIeIsento() {
        return ieIsento.get();
    }

    public BooleanProperty ieIsentoProperty() {
        return ieIsento;
    }

    public void setIeIsento(boolean ieIsento) {
        this.ieIsento.set(ieIsento);
    }

    @Column(length = 14)
    public String getIe() {
        return ie.get();
    }

    public StringProperty ieProperty() {
        return ie;
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj.replaceAll("\\D", ""));
    }

    @Column(length = 80, nullable = false)
    public String getRazao() {
        return razao.get();
    }

    public StringProperty razaoProperty() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao.set(razao);
    }

    @Column(length = 80, nullable = false)
    public String getFantasia() {
        return fantasia.get();
    }

    public StringProperty fantasiaProperty() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia.set(fantasia);
    }

    @Column(length = 1, nullable = false)
    public boolean isCliente() {
        return cliente.get();
    }

    public BooleanProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(boolean cliente) {
        this.cliente.set(cliente);
    }

    @Column(length = 1, nullable = false)
    public boolean isFornecedor() {
        return fornecedor.get();
    }

    public BooleanProperty fornecedorProperty() {
        return fornecedor;
    }

    public void setFornecedor(boolean fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    @Column(length = 1, nullable = false)
    public boolean isTransportadora() {
        return transportadora.get();
    }

    public BooleanProperty transportadoraProperty() {
        return transportadora;
    }

    public void setTransportadora(boolean transportadora) {
        this.transportadora.set(transportadora);
    }

    @Column(length = 2, nullable = false)
    public SituacaoNoSistema getSituacao() {
        return SituacaoNoSistema.toEnum(situacao.get());
    }

    public void setSituacao(SituacaoNoSistema situacao) {
        this.situacao.set(situacao.getCod());
    }

    public IntegerProperty situacaoProperty() {
        return situacao;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioCadastro_id", foreignKey = @ForeignKey(name = "fk_empresa_usuario_cadastro"))
    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    @CreationTimestamp
    @Column(nullable = false)
    public LocalDateTime getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public ObjectProperty<LocalDateTime> dataCadastroProperty() {
        return dataCadastro;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioAtualizacao_id", foreignKey = @ForeignKey(name = "fk_empresa_usuario_atualizacao"))
    public Usuario getUsuarioAtualizacao() {
        return usuarioAtualizacao;
    }

    public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
        this.usuarioAtualizacao = usuarioAtualizacao;
    }

    @UpdateTimestamp
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao.get();
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao.set(dataAtualizacao);
    }

    public ObjectProperty<LocalDateTime> dataAtualizacaoProperty() {
        return dataAtualizacao;
    }

    @Column(nullable = false)
    public LocalDateTime getDataAbetura() {
        return dataAbetura.get();
    }

    public void setDataAbetura(LocalDateTime dataAbetura) {
        this.dataAbetura.set(dataAbetura);
    }

    public ObjectProperty<LocalDateTime> dataAbeturaProperty() {
        return dataAbetura;
    }

    @Column(length = 200, nullable = false)
    public String getNaturezaJuridica() {
        return naturezaJuridica.get();
    }

    public StringProperty naturezaJuridicaProperty() {
        return naturezaJuridica;
    }

    public void setNaturezaJuridica(String naturezaJuridica) {
        this.naturezaJuridica.set(naturezaJuridica);
    }

    @Column(length = 1500)
    public String getObservacoes() {
        return observacoes.get();
    }

    public StringProperty observacoesProperty() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes.set(observacoes);
    }

    @Column(length = 19, scale = 2, nullable = false)
    public BigDecimal getLimite() {
        return limite.get();
    }

    public void setLimite(BigDecimal limite) {
        this.limite.set(limite);
    }

    public ObjectProperty<BigDecimal> limiteProperty() {
        return limite;
    }

    @Column(length = 3, nullable = false)
    public int getPrazo() {
        return prazo.get();
    }

    public void setPrazo(int prazo) {
        this.prazo.set(prazo);
    }

    public IntegerProperty prazoProperty() {
        return prazo;
    }

    @Column(length = 1, nullable = false)
    public boolean isDiaUtil() {
        return diaUtil.get();
    }

    public void setDiaUtil(boolean diaUtil) {
        this.diaUtil.set(diaUtil);
    }

    public BooleanProperty diaUtilProperty() {
        return diaUtil;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<EmailHomePage> getEmailHomePageList() {
        return emailHomePageList;
    }

    public void setEmailHomePageList(List<EmailHomePage> emailHomePageList) {
        this.emailHomePageList = emailHomePageList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<InfoReceitaFederal> getInfoReceitaFederalList() {
        return infoReceitaFederalList;
    }

    public void setInfoReceitaFederalList(List<InfoReceitaFederal> infoReceitaFederalList) {
        this.infoReceitaFederalList = infoReceitaFederalList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<EmpresaProdutoValor> getEmpresaProdutoValorList() {
        return empresaProdutoValorList;
    }

    public void setEmpresaProdutoValorList(List<EmpresaProdutoValor> empresaProdutoValorList) {
        this.empresaProdutoValorList = empresaProdutoValorList;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<InformacaoBancaria> getInformacaoBancariaList() {
        return informacaoBancariaList;
    }

    public void setInformacaoBancariaList(List<InformacaoBancaria> informacaoBancariaList) {
        this.informacaoBancariaList = informacaoBancariaList;
    }

    public void setIe(String ie) {
        this.ie.set(ie.replaceAll("\\D", ""));
    }

    @Override
    public Empresa clone() throws CloneNotSupportedException {
        Empresa empresa = new Empresa();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.getById(Empresa.class, getId());
        return empresa;
    }

    @Transient
    @JsonIgnore
    public String getMunicipio() {
        Endereco end = getEnderecoList().stream()
                .filter(endereco -> endereco.tipoProperty().get() == EnderecoTipo.PRINCIPAL.getCod())
                .findFirst().orElse(null);
        if (end == null) return "MANAUS";
        return end.getMunicipio().getDescricao();
    }

    @Transient
    @JsonIgnore
    public String getUf() {
        Endereco end = getEnderecoList().stream()
                .filter(endereco -> endereco.tipoProperty().get() == EnderecoTipo.PRINCIPAL.getCod())
                .findFirst().orElse(null);
        if (end == null) return "AM";
        return end.getMunicipio().getUf().getSigla();
    }

    @Transient
    @JsonIgnore
    public Telefone getTelefone() {
        return getTelefoneList().stream()
                .findFirst().orElse(null);
    }

    @Transient
    public Endereco getEnderecoNFe() {
        return getEnderecoList().stream()
                .filter(endereco -> endereco.tipoProperty().get() == EnderecoTipo.PRINCIPAL.getCod())
                .findFirst().orElse(null);
    }

    @Transient
    public Endereco getEnderecoNFeEntrega() {
        return getEnderecoList().stream()
                .filter(endereco -> endereco.tipoProperty().get() == EnderecoTipo.ENTREGA.getCod())
                .findFirst().orElse(null);
    }

    @Transient
    @JsonIgnore
    public String getEnderecoPrincipal() {
        Endereco end = getEnderecoList().stream()
                .filter(endereco -> endereco.tipoProperty().get() == EnderecoTipo.PRINCIPAL.getCod())
                .findFirst().orElse(null);
//        if (end == null) return null;
        return end == null ? "" : String.format("%s, %s - %s",
                end.logradouroProperty().get(),
                end.numeroProperty().get(),
                end.bairroProperty().get());
    }

    @Transient
    @JsonIgnore
    public String getFonePrincipal() {
        Telefone tel = getTelefoneList().stream()
                .filter(telefone -> Integer.parseInt(telefone.getDescricao().substring(2, 3)) > 6)
                .findFirst().orElse(null);
        return tel == null ? "" : tel.getDescricao();
    }

    @Transient
    @JsonIgnore
    public String getEmailPrincipal() {
        EmailHomePage email = getEmailHomePageList().stream()
                .filter(emailHomePage -> (emailHomePage.isMail() && emailHomePage.isPrincipal()))
                .findFirst().orElse(getEmailHomePageList().stream()
                        .filter(emailHomePage -> emailHomePage.isMail())
                        .findFirst().orElse(null));
        return email == null ? "" : email.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", razaoProperty().get(), fantasiaProperty().get());
    }


//    @Override
//    public String toString() {
//        return "Empresa{" +
//                "id=" + id +
//                ", lojaSistema=" + lojaSistema +
//                ", classifJuridica=" + classifJuridica +
//                ", cnpj=" + cnpj +
//                ", ieIsento=" + ieIsento +
//                ", ie=" + ie +
//                ", razao=" + razao +
//                ", fantasia=" + fantasia +
//                ", cliente=" + cliente +
//                ", fornecedor=" + fornecedor +
//                ", transportadora=" + transportadora +
//                ", situacao=" + situacao +
//                ", usuarioCadastro=" + usuarioCadastro +
//                ", dataCadastro=" + dataCadastro +
//                ", usuarioAtualizacao=" + usuarioAtualizacao +
//                ", dataAtualizacao=" + dataAtualizacao +
//                ", dataAbetura=" + dataAbetura +
//                ", naturezaJuridica=" + naturezaJuridica +
//                ", observacoes=" + observacoes +
//                ", limite=" + limite +
//                ", prazo=" + prazo +
//                ", diaUtil=" + diaUtil +
//                ", enderecoList=" + enderecoList +
//                ", emailHomePageList=" + emailHomePageList +
//                ", telefoneList=" + telefoneList +
//                ", contatoList=" + contatoList +
//                ", infoReceitaFederalList=" + infoReceitaFederalList +
//                ", empresaProdutoValorList=" + empresaProdutoValorList +
//                '}';
//    }

}
