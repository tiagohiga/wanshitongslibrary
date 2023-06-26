package br.com.wanshitong.wst.exception;

public class BookBorrowFoundException extends RuntimeException{
    public BookBorrowFoundException(String id){
        super("Foi encontrado um emprestimo em aberto: " + id);
    }
}
