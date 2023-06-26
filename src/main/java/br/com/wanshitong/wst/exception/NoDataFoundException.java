package br.com.wanshitong.wst.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String record){
        super("Sem dados cadastrados para o parametro: " + record);
    }
}
