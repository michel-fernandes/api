package br.com.j38.api.service.exceptions;

public class DataIntegretyViolationException extends RuntimeException{
    public DataIntegretyViolationException(String message) {
        super(message);
    }
}
