public class Sudoku {
	private int[][] board = new int[9][9];
	public Sudoku() {
		for (int i = 0; i < int[i].length; i++) {
			for (int j = 0; j < int[i].length; j++)
				board[i][j] = 0;
		}
	}
	public Sudoku(int[][] input) {
		for (int i = 0; i < int[i].length; i++) {
			for (int j = 0; j < int[i].length; j++)
				board[i][j] = input[i][j];
		}
	}
	public int[][] board() {
		return board;
	}
	public boolean[] candidates(int row, int column) {
		/*
		TODO: returns the list of candidates for the specified cell. 
		The array contains true at index i if i is a candidate for the cell at row and column.
		*/
	}
	public boolean isSolved() {
		/*
		TODO: return true if board is in a solved state.
		*/
	}
	public void solve() {
		/*
		TODO: attempts to solve the board. Exits when board is solved or no updates were made to the board.
		*/
	}
}