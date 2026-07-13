package org.example.internship_system.exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message,Throwable cause){
        super(message,cause);
    }
}
