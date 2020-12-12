package br.com.tlmacedo.nfe.model.vo;

import java.io.Serializable;
import java.util.List;

public class InfNfeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String versao;
    private String id;
    private String pk_nItem;
    private IdeVO ide;
    private EmitVO emit;
    private DestVO dest;
    private EntregaVO entrega;
    private List<DetVO> detList;
    private TotalVO total;
    private TranspVO transp;
    private CobrVO cobr;
    private PagVO pag;
    private InfAdicVO infAdic;
    private InfRespTecVO infRespTec;


    public InfNfeVO() {
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPk_nItem() {
        return pk_nItem;
    }

    public void setPk_nItem(String pk_nItem) {
        this.pk_nItem = pk_nItem;
    }

    public IdeVO getIde() {
        return ide;
    }

    public void setIde(IdeVO ide) {
        this.ide = ide;
    }

    public EmitVO getEmit() {
        return emit;
    }

    public void setEmit(EmitVO emit) {
        this.emit = emit;
    }

    public DestVO getDest() {
        return dest;
    }

    public void setDest(DestVO dest) {
        this.dest = dest;
    }

    public EntregaVO getEntrega() {
        return entrega;
    }

    public void setEntrega(EntregaVO entrega) {
        this.entrega = entrega;
    }

    public List<DetVO> getDetList() {
        return detList;
    }

    public void setDetList(List<DetVO> detList) {
        this.detList = detList;
    }

    public TotalVO getTotal() {
        return total;
    }

    public void setTotal(TotalVO total) {
        this.total = total;
    }

    public TranspVO getTransp() {
        return transp;
    }

    public void setTransp(TranspVO transp) {
        this.transp = transp;
    }

    public CobrVO getCobr() {
        return cobr;
    }

    public void setCobr(CobrVO cobr) {
        this.cobr = cobr;
    }

    public PagVO getPag() {
        return pag;
    }

    public void setPag(PagVO pag) {
        this.pag = pag;
    }

    public InfAdicVO getInfAdic() {
        return infAdic;
    }

    public void setInfAdic(InfAdicVO infAdic) {
        this.infAdic = infAdic;
    }

    public InfRespTecVO getInfRespTec() {
        return infRespTec;
    }

    public void setInfRespTec(InfRespTecVO infRespTec) {
        this.infRespTec = infRespTec;
    }

    @Override
    public String toString() {
        return "InfNfeVO{" +
                "versao='" + versao + '\'' +
                ", id='" + id + '\'' +
                ", pk_nItem='" + pk_nItem + '\'' +
                ", ide=" + ide +
                ", emit=" + emit +
                ", dest=" + dest +
                ", entrega=" + entrega +
                ", detList=" + detList +
                ", total=" + total +
                ", transp=" + transp +
                ", cobr=" + cobr +
                ", pag=" + pag +
                ", infAdic=" + infAdic +
                ", infRespTec=" + infRespTec +
                '}';
    }
}
