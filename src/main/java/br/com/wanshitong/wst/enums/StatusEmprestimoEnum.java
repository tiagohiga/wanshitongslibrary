package br.com.wanshitong.wst.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEmprestimoEnum {
    ABERTO(1, "Aberto"),

    DEVOLUCAO_PRAZO(2, "Devolvido"),

    DEVOLUCAO_ATRASO(3, "Devolvido com Atraso"),

    FORA_PRAZO(99, "Fora do prazo");

    private final long id;

    private final String status;

    public boolean isPendente(long id){
        return ABERTO.id == id;
    }

    public boolean isDevolvidoNoPrazo(long id){
        return DEVOLUCAO_PRAZO.id == id;
    }

    public boolean isDevolvidoComAtraso(long id){
        return DEVOLUCAO_ATRASO.id == id;
    }

    public boolean isForaPrazo(long id){
        return FORA_PRAZO.id == id;
    }
}
