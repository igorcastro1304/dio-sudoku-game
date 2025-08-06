package com.br.dio.sudoku.domain;

import java.util.Scanner;

import com.br.dio.sudoku.controller.MenuController;
import com.br.dio.sudoku.exception.ImutableFieldException;
import com.br.dio.sudoku.exception.InvalidValueException;

public class Menu {
	private Board board;
	
	public Menu(Board board) {
		this.board = board;
	}

	public void startProgram(String[] values) {
		if (values.length != 81) {
			throw new IllegalArgumentException(
					"Entrada inválida: por favor, insira 81 valores de 0 a 9 separados por vírgula.");
		}

		for (int i = 0; i < 81; i++) {
			int num = Integer.parseInt(values[i].trim());

			if (num != 0 && num <= 9) {
				int row = i / 9;
				int col = i % 9;

				try {
					board.placeValue(row, col, num, false);
				} catch (InvalidValueException valueException) {
					System.out.println("Não foi possível inserir o valor pois o mesmo valor já existe em uma linha, coluna ou caixa.");
				} catch (ImutableFieldException fieldException) {
					System.out.println("Não foi possível inserir o valor pois o campo é imutável.");
				}
			}
		}
		
		board.printBoard();
	}

	private void printOptions() {
		System.out.println("1 - Mostrar tabuleiro;\n" + "2 - Adicionar um novo número;\n" + "3 - Remover um número;\n"
				+ "4 - Verificar jogo;\n" + "5 - Remover todas as casas;\n" + "0 - Sair");
	}

	public void userMenu(Scanner in, Board board) {
		int option = -1;

		System.out.println("Olá usuário! Seja bem-vindo ao Sudoku. Por favor selecione uma opção");

		while (option != 0) {
			printOptions();
			System.out.print("Escolha sua opção: ");
			option = in.nextInt();

			switch (option) {
			case 1:
				board.printBoard();
				break;
			
			case 2:
				System.out.print("Digite a linha que deseja inserir o número: ");
				int rowPosition = in.nextInt();
				System.out.println();
				
				System.out.print("Digite a coluna que deseja inserir o número: ");
				int colPosition = in.nextInt();
				System.out.println();
				
				System.out.print("Digite o número desejado: ");
				int number = in.nextInt();
				System.out.println();
				
				MenuController.addNumber(board, rowPosition, colPosition, number);
				break;
				
			case 3:
				System.out.print("Digite a linha que deseja remover o número: ");
				rowPosition = in.nextInt();
				System.out.println();
				
				System.out.print("Digite a coluna que deseja remover o número: ");
				colPosition = in.nextInt();
				System.out.println();
				
				MenuController.removeNumber(board, rowPosition, colPosition);
				break;
				
			case 4:
				MenuController.viewGameSituation(board);
				break;
				
			case 5:
				MenuController.restartGame(in, board);
				break;
				
			case 0:
				break;
				
			default:
				System.out.println("Opção inválida, por favor tente novamente!");
			}
		}
	}

}
