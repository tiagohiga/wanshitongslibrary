package br.com.wanshitong.wst.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String record, String id){
        super(record + " nao encontrado para o parametro: " + id);
    }
}
