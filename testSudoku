import junit.framework.TestCase;

public class TestSudoku extends TestCase {
  public Sudoku s;
  public Sudoku s2 = new Sudoku();
  public Sudoku s3;
  
  public void setUp() {
    String boardInput = "000041000060000200000000000320600000000050040700000000000200300480000000501000000";
    int[][] input = Sudoku.stringToBoard(boardInput);
    s = new Sudoku(input);
    
    String boardInput2 = "000004028406000005100030600000301000087000140000709000002010003900000507670400000";
    int[][] input2 = Sudoku.stringToBoard(boardInput2);
    s3 = new Sudoku(input2);
  }
  
  public void testCandidates() {
    boolean[] temp;
    temp = s.candidates(0, 0);

    assertEquals(temp[0], false);
    assertEquals(temp[1], false);
    assertEquals(temp[2], true);
    assertEquals(temp[3], false);
    assertEquals(temp[4], false);
    assertEquals(temp[5], false);
    assertEquals(temp[6], false);
    assertEquals(temp[7], false);
    assertEquals(temp[8], true);
    assertEquals(temp[9], true);
  }
  
  public void testHiddenSingles() {
    assertTrue(s.hiddenSingles());
    
    int[][] temp = s.board();
    
    assertEquals(temp[7][2], 2);
  }
  
  public void testNakedSingles() {
    assertFalse(s.nakedSingles());
  }
  
  public void testIsSolved() {
    assertFalse(s.isSolved());
  }
  
  public void testSolve() {
    int[][] actualSolution = {
      {7, 3, 5, 1, 6, 4, 9, 2, 8}, 
      {4, 2, 6, 9, 7, 8, 3, 1, 5},
      {1, 9, 8, 5, 3, 2, 6, 7, 4},
      {2, 4, 9, 3, 8, 1, 7, 5, 6},
      {3, 8, 7, 2, 5, 6, 1, 4, 9},
      {5, 6, 1, 7, 4, 9, 8, 3, 2},
      {8, 5, 2, 6, 1, 7, 4, 9, 3},
      {9, 1, 4, 8, 2, 3, 5, 6, 7},
      {6, 7, 3, 4, 9, 5, 2, 8, 1}
    };
    
    s3.solve();
    int[][] solution = s3.board();
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        assertEquals(actualSolution[i][j], solution[i][j]);
      }
    }
    
    assertTrue(s3.isSolved());
  }
}
