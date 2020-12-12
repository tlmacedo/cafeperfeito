package br.com.tlmacedo.cafeperfeito.interfaces.jdbc;

public interface BancoDados extends SqlDCL, SqlDML, SqlDDL {

    void abrirConexao();

    void fecharConexao();
}
