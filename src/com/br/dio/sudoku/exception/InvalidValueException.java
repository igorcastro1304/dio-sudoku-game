package com.br.dio.sudoku.exception;

public class InvalidValueException extends Exception {
	public InvalidValueException(String errorMessage) {
		super(errorMessage);
	}
}
