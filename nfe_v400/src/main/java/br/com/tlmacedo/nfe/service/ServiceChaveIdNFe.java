package br.com.tlmacedo.nfe.service;

import br.com.tlmacedo.nfe.model.vo.IdeVO;

import java.time.LocalDate;

public class ServiceChaveIdNFe {

    private static final int[] pesoChaveNfeCte = {4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9,
            8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String getChaveNfe(IdeVO ideVO) {
        String base = gera_BaseChaveNfe(
                String.format("%02d", ideVO.getcUF()),
                ideVO.getDhEmi().toLocalDate(),
                System.getProperty("nfeCnpjLoja", "08009246000136"),
                String.format("%02d", Integer.parseInt(ideVO.getMod())),
                String.format("%03d", Integer.parseInt(ideVO.getSerie())),
                String.format("%09d", Integer.parseInt(ideVO.getnNF())),
                String.format("%d", Integer.parseInt(ideVO.getTpEmis()))
        );
        return String.format("%s%d", base, nfeDv(base));
    }

    public static String gera_BaseChaveNfe(String cUF, LocalDate dtEmissao, String cnpj, String mod, String serie, String nNF, String tpEmis) {
        String aAMM = String.format("%02d%02d",
                dtEmissao.getYear() % 100,
                dtEmissao.getMonthValue());
        String cNF = String.format("%04d%s",
                dtEmissao.getYear(), aAMM);

        return String.format("%s%s%s%s%s%s%s%s",
                cUF,
                aAMM,
                cnpj,
                mod,
                serie,
                nNF,
                tpEmis,
                cNF);
    }

    public static int nfeDv(final String base) {
        final String chave = base;
        int[] numeros = chave.chars().map(Character::getNumericValue).toArray();
        int resultado = 0;
        for (int i = numeros.length - 1; i >= 0; i--) {
            resultado += (numeros[i] * pesoChaveNfeCte[i]);
        }
        resultado = 11 - resultado % 11;
        return resultado > 9 ? 0 : resultado;
    }

}
