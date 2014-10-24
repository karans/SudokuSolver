import java.util.*;
public class Sudoku {
	private int[][] board = new int[9][9];
	public Sudoku() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = 0;
		
	}
	public Sudoku(int[][] input) {
		for (int i = 0; i < board.length; i++) 
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = input[i][j];
		
	}
	public int[][] board() {
		return board;
	}
	public boolean[] candidates(int row, int column) {
		boolean[] candidates = new boolean[9];
		/*
		TODO: returns the list of candidates for the specified cell. 
		The array contains true at index i if i is a candidate for the cell at row and column.
		*/
		return candidates;
	}
	public boolean isSolved() {
		//check for 3x3, outer 2 loops for iterating between 3x3 blocks
		ArrayList<Integer> current = new ArrayList<Integer>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 9; i+=3) {
			for (int j = 0; j < 9; j+=3){
				//checking within blocks
				for (int k = 0; k < 3; k++){
					for (int l = 0; l < 3; l++){
						current.add(board[i+k][j+l]);
						//System.out.printf("adding [%d][%d] = %d\n", i+k , j+l, board[i+k][j+l]);
					}
				}
				if (!(current.containsAll(numbers))) {
				        return false;
				}
				current.clear();
			}
		}
		//check rows
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				current.add(board[i][j]);
			}
			if (!(current.containsAll(numbers))) {
				        return false;
			}
			current.clear();

		}
		//check columns
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				current.add(board[j][i]);
			}
			if (!(current.containsAll(numbers))) {
				        return false;
			}
			current.clear();

		}
		return true;
	}
	public void solve() {
		/*
		TODO: attempts to solve the board. Exits when board is solved or no updates were made to the board.
		*/
	}
	public boolean nakedSingles() {
		return true;
	}
	public boolean hiddenSingles() {
		return true;
	}
	public void printBoard() {
		System.out.println("    1 2 3   4 5 6   7 8 9");
		System.out.println("  +-------+-------+-------+");
		char letter = 'A';
		for (int i = 0; i < 9; i++) {
			System.out.print(letter + " |");
			for (int j = 0; j < 3; j++){
				if (board[i][j] == 0)
					System.out.printf("  ", board[i][j]);
				else
					System.out.printf(" %d", board[i][j]);
			}
			System.out.print(" |");
			for (int j = 3; j < 6; j++){
				if (board[i][j] == 0)
					System.out.printf("  ", board[i][j]);
				else
					System.out.printf(" %d", board[i][j]);
			}
			System.out.print(" |");
			for (int j = 6; j < 9; j++){
				if (board[i][j] == 0)
					System.out.printf("  ", board[i][j]);
				else
					System.out.printf(" %d", board[i][j]);
			}
			System.out.println(" |");
			if(letter == 'C' || letter == 'F' || letter == 'I')
				System.out.println("  +-------+-------+-------+");
			letter++;

		}
	}
	public static int[][] stringToBoard(String s) {
		int[][] output = new int[9][9];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				output[i][j] = s.charAt((i*9) + j) - 48;
		return output;
	}
	public static void main(String[] args) {
		//sample argument: 000041000060000200000000000320600000000050040700000000000200300480000000501000000
		//sample argument: 123123123654456456987789789123123123456456456789789789123123123456456456789789789
		//sample argument: 248395716571628349936741582682539174359174628714862953863417295195286437427953861
		String boardInput = args[0];
		int[][] input = stringToBoard(boardInput);
		Sudoku s = new Sudoku(input);
		s.printBoard();
		System.out.println(s.isSolved());
	}
}