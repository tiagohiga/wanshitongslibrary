package br.com.wanshitong.wst.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException(){
        super("A quantidade de estoque nao pode ser menor que 0");
    }
}
