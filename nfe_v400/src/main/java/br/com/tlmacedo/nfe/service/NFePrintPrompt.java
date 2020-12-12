package br.com.tlmacedo.nfe.service;

public class NFePrintPrompt {

    public static void print(String xmlType, String print) {
        if (NFev400.isPrintPrompt())
            System.out.printf("\n%s%s:\n%s\n",
                    (NFev400.isAmbProducao()) ? "prod_" : "hom_",
                    xmlType,
                    print);
    }

}
