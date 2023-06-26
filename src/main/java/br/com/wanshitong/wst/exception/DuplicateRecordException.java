package br.com.wanshitong.wst.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String isbn){
        super(isbn + " ja foi cadastrado");
    }
}
