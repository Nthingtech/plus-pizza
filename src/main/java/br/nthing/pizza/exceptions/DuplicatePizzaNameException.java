package br.nthing.pizza.exceptions;

public class DuplicatePizzaNameException extends RuntimeException {
    public  DuplicatePizzaNameException(String message) {
        super(message);
    }
}
