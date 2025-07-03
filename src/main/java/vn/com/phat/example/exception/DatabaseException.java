package vn.com.phat.example.exception;

import jp.sf.amateras.mirage.exception.SQLRuntimeException;

public class DatabaseException extends RuntimeException{

    public DatabaseException(SQLRuntimeException e) {
        super(e);
    }
}
