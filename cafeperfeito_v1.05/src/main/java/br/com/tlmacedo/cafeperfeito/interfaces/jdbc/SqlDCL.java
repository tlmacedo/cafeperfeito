package br.com.tlmacedo.cafeperfeito.interfaces.jdbc;

public interface SqlDCL {

    void grant(String access);

    void remoke(String access);
}
