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
		/*
		TODO: return true if board is in a solved state.
		*/
		return false;
	}
	public void solve() {
		/*
		TODO: attempts to solve the board. Exits when board is solved or no updates were made to the board.
		*/
	}
	public static void main(String[] args) {
		//test stuff here
	}
}