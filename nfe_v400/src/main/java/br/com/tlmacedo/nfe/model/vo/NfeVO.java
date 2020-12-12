package br.com.tlmacedo.nfe.model.vo;

public class NfeVO {
    private static final long serialVersionUID = 1L;

    private InfNfeVO infNfe;

    public NfeVO() {
    }


    public InfNfeVO getInfNfe() {
        return infNfe;
    }

    public void setInfNfe(InfNfeVO infNfe) {
        this.infNfe = infNfe;
    }

    @Override
    public String toString() {
        return "NfeVO{" +
                "infNfe=" + infNfe +
                '}';
    }
}
