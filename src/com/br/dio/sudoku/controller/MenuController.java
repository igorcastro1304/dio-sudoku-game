package com.br.dio.sudoku.controller;

import java.io.IOException;
import java.util.Scanner;

import com.br.dio.sudoku.domain.Board;
import com.br.dio.sudoku.exception.ImutableFieldException;
import com.br.dio.sudoku.exception.InvalidValueException;

public class MenuController {
	public static void addNumber(Board board, int row, int col, int value) {
		try {
			board.placeValue(row, col, value, true);
		} catch (InvalidValueException valueException) {
			System.out.println("Não foi possível inserir o valor pois o mesmo valor já existe em uma linha, coluna ou caixa.");
		} catch (ImutableFieldException fieldException) {
			System.out.println("Não foi possível inserir o valor pois o campo é imutável.");
		}
		
		board.printBoard();
	}
	
	public static void removeNumber(Board board, int row, int col) {
		try {
			board.removeValue(row, col);
		} catch (IOException ioException) {
			System.out.println("Não foi possível remover o valor pois a coluna ou linha não existem.");
		}
		
		board.printBoard();
	}
	
	public static void viewGameSituation(Board board) {
		boolean isFinished = board.isBoardFinished();
		
		board.printBoard();
		
		if(isFinished) {
			System.out.println("O jogo terminou e você ganhou!");
		} else {
			System.out.println("O jogo ainda não está completo.");
		}
	}
	
	public static void restartGame(Scanner in, Board board) {
		board.printBoard();
		
		System.out.print("Tem certeza que deseja remover todos os elementos? (S/N):");
		in.nextLine();
		String confirmation = in.nextLine();
		
		if(confirmation.toLowerCase().strip().equals("s")) {
			board.removeAll();
			System.out.println("Jogo reiniciado com sucesso!");
		}
	}
}
