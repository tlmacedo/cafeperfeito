package br.com.tlmacedo.cafeperfeito.service;

public class ServiceCalculaTempo {

    Long ini, fim;
    int cont = 0;
    String strStart;

    public ServiceCalculaTempo() {
        start("inicio");
    }

    public void start(String strStart) {
        ini = System.currentTimeMillis();
        this.strStart = strStart;
    }

    public void fim(String strFim) {

        fim = System.currentTimeMillis();
        try {
            cont++;
            System.out.printf("ini(%2$02d): [%1$13s]\t{%3$s}\n", ini, cont, strStart);
            System.out.printf("dif(%2$02d): [%1$13s]\n", fim - ini, cont);
            System.out.printf("fim(%2$02d): [%1$13s]\t{%3$s}\n", fim, cont, strFim);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
