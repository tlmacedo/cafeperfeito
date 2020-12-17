package br.com.tlmacedo.cafeperfeito.model.enums;

public enum EnumClassificacaoJuridica {

    FISICA(0, "Física"),
    JURIDICA(1, "Juridica");

    private Integer cod;
    private String descricao;

    EnumClassificacaoJuridica(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EnumClassificacaoJuridica toEnum(Integer codigo) {
        for (EnumClassificacaoJuridica classificacaoJuridica : EnumClassificacaoJuridica.values())
            if (classificacaoJuridica.getCod().equals(codigo))
                return classificacaoJuridica;
        throw new IllegalArgumentException("Id inválido!");
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
