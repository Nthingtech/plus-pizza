package br.nthing.pizza;

public class DuplicatePizzaNameException extends RuntimeException {
    public  DuplicatePizzaNameException(String message) {
        super(message);
    }
}
