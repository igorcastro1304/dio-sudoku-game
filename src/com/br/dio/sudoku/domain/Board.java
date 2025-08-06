package com.br.dio.sudoku.domain;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.br.dio.sudoku.exception.ImutableFieldException;
import com.br.dio.sudoku.exception.InvalidValueException;

public class Board {
	private Set<Integer>[] rows = new HashSet[9];
	private Set<Integer>[] cols = new HashSet[9];
	private Set<Integer>[] boxes = new HashSet[9];
	private Value[][] board = new Value[9][9];

	public Board() {
		for (int i = 0; i < 9; i++) {
			rows[i] = new HashSet<>();
			cols[i] = new HashSet<>();
			boxes[i] = new HashSet<>();
		}
	}
	
	private int getBoxIndex(int row, int col) {
	    return (row / 3) * 3 + (col / 3);
	}

	public boolean isValidPlacement(int row, int col, int value) {
		return !rows[row].contains(value) && !cols[col].contains(value) && !boxes[getBoxIndex(row, col)].contains(value);
	}

	public void placeValue(int row, int col, int value, boolean isMutable) throws InvalidValueException, ImutableFieldException {
	    Value current = board[row][col];

	    if (current != null && !current.isMutable()) {
	    	throw new ImutableFieldException("Cannot insert value here. The field is imutable.");
	    }

	    if (!isValidPlacement(row, col, value)) {
	    	throw new InvalidValueException("The value you are trying to assign already exists on the row, column or box.");
	    }

	    if (current != null) {
	        int oldValue = current.getValue();
	        rows[row].remove(oldValue);
	        cols[col].remove(oldValue);
	        boxes[getBoxIndex(row, col)].remove(oldValue);
	    }

	    board[row][col] = new Value(value, isMutable);
	    rows[row].add(value);
	    cols[col].add(value);
	    boxes[getBoxIndex(row, col)].add(value);
	}

	public void removeValue(int row, int col) throws IOException {
	    Value v = board[row][col];
	    if (v != null && v.isMutable()) {
	        int value = v.getValue();
	        rows[row].remove(value);
	        cols[col].remove(value);
	        boxes[getBoxIndex(row, col)].remove(value);
	        board[row][col] = null;
	    } else {
	    	throw new IOException("The given row or column doesn't exists");
	    }
	}

	public Value getCell(int row, int col) {
		return board[row][col];
	}
	
	public boolean isBoardFinished() {
		for (int c = 0; c < 9; c++) {
			for(int r = 0; r < 9; r++) {
				Value value = getCell(r, c);
				
				if(value == null || value.getValue() == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void removeAll() {
		for (int i = 0; i < 9; i++) {
            rows[i].clear();
            cols[i].clear();
            boxes[i].clear();
        }

		for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                board[r][c] = null;
            }
        }
	}

	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Value val = board[i][j];
				System.out.print((val != null ? val.getValue() : ".") + " ");
			}
			System.out.println();
		}
	}

}
