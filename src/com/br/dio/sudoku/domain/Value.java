package com.br.dio.sudoku.domain;

public class Value {
	private int value;
	private boolean isMutable;
	
	public Value(int value, boolean isMutable) {
		this.value = value;
		this.isMutable = isMutable;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isMutable() {
		return isMutable;
	}
	
	public void setMutable(boolean isMutable) {
		this.isMutable = isMutable;
	}
}
