import java.util.Scanner;

import com.br.dio.sudoku.domain.Board;
import com.br.dio.sudoku.domain.Menu;

public class Main {
	public static void main(String[] args) {
		String[] values = args[0].split(",");

		Scanner in = new Scanner(System.in);
		Board board = new Board();
		Menu menu = new Menu(board);
		
		
		menu.startProgram(values);
		menu.userMenu(in, board);
	}
}
