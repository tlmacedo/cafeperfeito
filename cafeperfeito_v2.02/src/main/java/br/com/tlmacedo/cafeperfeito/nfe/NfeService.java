package br.com.tlmacedo.cafeperfeito.nfe;

import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoNfeDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoNfe;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;
import static br.com.tlmacedo.nfe.service.ServiceChaveIdNFe.gera_BaseChaveNfe;
import static br.com.tlmacedo.nfe.service.ServiceChaveIdNFe.nfeDv;

public class NfeService {

    public static String factoryIdLote(int nBase) {
        return String.format("%015d", nBase);
    }

    public static String factoryIdLote(Long nBase) {
        return factoryIdLote(nBase.intValue());
    }

    public static Integer getLastNumeroNFe() {
        Integer nLast = 0;
        try {
            nLast = new SaidaProdutoNfeDAO().getLast(SaidaProdutoNfe.class, "numero").numeroProperty().getValue();
        } catch (Exception ex) {
            if (!(ex instanceof NullPointerException))
                ex.printStackTrace();
            nLast = 0;
        }
        return nLast;
    }

    public static String getChaveNfe(SaidaProdutoNfe saidaProdutoNfe) {
        String base = gera_BaseChaveNfe(
                String.format("%02d", TCONFIG.getInfLoja().getCUF()),
                saidaProdutoNfe.dtHoraEmissaoProperty().getValue().toLocalDate(),
                TCONFIG.getInfLoja().getCnpj(),
                String.format("%02d", saidaProdutoNfe.getModelo()),
                String.format("%03d", saidaProdutoNfe.serieProperty().getValue()),
                String.format("%09d", saidaProdutoNfe.numeroProperty().getValue()),
                String.format("%d", saidaProdutoNfe.impressaoTpEmisProperty().getValue())
        );
        return String.format("%s%d", base, nfeDv(base));
    }

}
