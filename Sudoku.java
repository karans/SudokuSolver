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
   boolean[] canidates = new boolean[10];
  
  //if the specified cell is already filled sets canidates to false and returns it
   if (board[row][column] > 0) {
     for (int i = 0; i < 9; i++) {
       canidates[i] = false;
     }
     return canidates;
   }
   
   /*checks every cell in the same row and same column as the specified cell
   * sets the canidates value at a specific value to false if it finds that value, otherwise
   * true
   */
   boolean numFound = true;
   for (int i = 1; i < canidates.length; i++) {
     for (int j = 0; j < board.length; j++) {
       if (board[row][j] == i || board[j][column] == i) {
         numFound = false;
       }
     }
     canidates[i] = numFound;
     numFound = true;
   }

   int[][] box = getBox(row, column);
   
  //checks the cells in the same box
   for (int i = 0; i < 3; i++) {
     for (int j = 0; j < 3; j++) {
       for (int k = 1; k < 10; k++) {
         if (box[i][j] == k) {
           canidates[k] = false;
         }
       }
     }
   }
   
   canidates[0] = false;
   return canidates;
 }
 public ArrayList<Integer> getNumsFromCandidates(boolean[] candidates, ArrayList<Integer> toAppend) {
  ArrayList<Integer> nums = new ArrayList<Integer>(toAppend);
  for (int i = 1; i < 10; i++ ){
    if (candidates[i]){
      toAppend.add(i);
    }
  }
  return nums;
 }
 //returns the box the given cell is in
 public int[][] getBox(int row, int column) {
   int[][] box = new int[3][3];
   
   if (row <= 2 && column <= 2) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i][j];
       }
     }
   }
   
   if (row <= 5 && row >= 3 && column <= 2) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 3][j];
       }
     }
   }
   
   if (row <= 8 && row >= 6 && column <= 2) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 6][j];
       }
     }
   }
   
   if (row <= 2 && column <= 5 && column >= 3) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i][j + 3];
       }
     }
   }   
   
   if (row <= 5 && row >= 3 && column <= 5 && column >= 3) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 3][j + 3];
       }
     }
   }   
   
   if (row >= 6 && column <= 5 && column >= 3) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 6][j + 3];
       }
     }
   }   
   
   if (row <= 2 && column >= 6) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i][j + 6];
       }
     }
   }   
   
   if (row <= 5 && row >= 3 && column >= 6) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 3][j + 6];
       }
     }
   }
   
   if (row >= 6 && column >= 6) {
     for (int i = 0; i < 3; i++) {
       for (int j = 0; j < 3; j++) {
         box[i][j] =  board[i + 6][j + 6];
       }
     }
   }
   return box;
 }
 public boolean isSolved() {
  //check for 3x3, outer 2 loops for iterating between 3x3 blocks
  ArrayList<Integer> current = new ArrayList<Integer>();
  ArrayList<Integer> numbers = new ArrayList<Integer>();
  for (int i = 1; i < 10; i++)
    numbers.add(i);
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
    while (!isSolved() && (hiddenSingles() || nakedSingles()));
 }
 public boolean nakedSingles() {
  boolean[] temp;
  int count = 0;
  int index = 0;
  boolean change = false;
   for (int i = 0; i < 9; i++) {
     for (int j = 0; j < 9; j++) {
       temp = candidates(i, j);
       for (int k = 1; k < 10; k++) {
         if (temp[k]) {
           count++;
           index = k;
         }
       }
       if (count == 1) {
         this.board[i][j] = index;
         change = true;
       }
       count = 0;
     }
   }
  return change;
 }
 public boolean hiddenSingles() {
  boolean change = false;
  ArrayList<Integer> current = new ArrayList<Integer>();
  ArrayList<Integer> thisCellsCandidates = new ArrayList<Integer>();
  for (int row = 0; row < 9; row++) {
    for (int column = 0; column < 9; column++) {
      getNumsFromCandidates(candidates(row,column),thisCellsCandidates);
      //check the box...
      if (row <= 2 && column <= 2) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
             if(i == row && j == column)
              continue;
             getNumsFromCandidates(candidates(i,j),current);
           }
         }
       }
       
       if (row <= 5 && row >= 3 && column <= 2) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 3 == row && j == column)
              continue;
             getNumsFromCandidates(candidates(i+3,j),current);
           }
         }
       }
       
       if (row <= 8 && row >= 6 && column <= 2) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 6== row && j == column)
              continue;
             getNumsFromCandidates(candidates(i+6,j),current);
           }
         }
       }
       
       if (row <= 2 && column <= 5 && column >= 3) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i == row && j + 3 == column)
              continue;
             getNumsFromCandidates(candidates(i,j+3),current);
           }
         }
       }   
       
       if (row <= 5 && row >= 3 && column <= 5 && column >= 3) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 3== row && j + 3== column)
              continue;
             getNumsFromCandidates(candidates(i+3,j+3),current);
           }
         }
       }   
       
       if (row >= 6 && column <= 5 && column >= 3) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 6 == row && j + 3 == column)
              continue;
             getNumsFromCandidates(candidates(i+6,j+3),current);
           }
         }
       }   
       
       if (row <= 2 && column >= 6) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i == row && j + 6 == column)
              continue;
             getNumsFromCandidates(candidates(i,j+6),current);
           }
         }
       }   
       
       if (row <= 5 && row >= 3 && column >= 6) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 3 == row && j + 6 == column)
              continue;
             getNumsFromCandidates(candidates(i+3,j+6),current);
           }
         }
       }
       
       if (row >= 6 && column >= 6) {
         for (int i = 0; i < 3; i++) {
           for (int j = 0; j < 3; j++) {
            if(i + 6 == row && j + 6 == column)
              continue;
             getNumsFromCandidates(candidates(i+6,j+6),current);
           }
         }
       }
      //System.out.printf("Finished Box:[%d][%d]\n",row,column);
      //check the row
      for (int i = 0; i < 9; i++) {
        if (row == 0 || row == 3 || row == 6) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 + 1,i),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 + 2,i),current);
          }
        }
        if (row == 1 || row == 4 || row == 7) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 - 1,i),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 + 1,i),current);
          }
        }
        if (row == 2 || row == 5 || row == 8) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 - 1,i),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(row % 3 - 2,i),current);
          }
        }
      }
      //System.out.printf("Finished Row:[%d][%d]\n",row,column);
      //check the column
      for (int i = 0; i < 9; i++) {
        if (column == 0 || column == 3 || column == 6) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 + 1),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 + 2),current);
          }
        }
        if (column == 1 || column == 4 || column == 7) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 - 1),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 + 1),current);
          }
        }
        if (column == 2 || column == 5 || column == 8) {
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 - 1),current);
          }
          for (int j = 0; j < 9; j++){
            getNumsFromCandidates(candidates(i,column % 3 - 2),current);
          }
        }
      }
      //System.out.printf("Finished Column:[%d][%d]\n",row,column);
      current = new ArrayList<Integer>(new LinkedHashSet<Integer>(current));

      
      System.out.printf("Candidates acquired to test [%d][%d]: ",row, column);
      for (int i =0; i < current.size(); i++)
        System.out.print(current.get(i) + ", ");
      System.out.printf("     Cell candidates [%d][%d]: ",row, column);    
      for (int i = 0 ; i < thisCellsCandidates.size(); i++)
        System.out.print(thisCellsCandidates.get(i) + ", "); 
      for (int i =0 ; i < current.size(); i++)
        thisCellsCandidates.remove(current.get(i));
      System.out.printf("     Cell[%d][%d] unique candidates: ",row, column);
      for (int i =0; i < thisCellsCandidates.size(); i++)
        System.out.print(thisCellsCandidates.get(i) + ", ");
      if (thisCellsCandidates.size() == 1){
        System.out.printf("\n!!!!!!!!!!!!!MADE A CHANGE!!!!!!!!!!!!!!!!!  @[%d][%d] = %d\n",row,column, thisCellsCandidates.get(0));
        board[row][column] = thisCellsCandidates.get(0);
        change = true;
      }
      thisCellsCandidates.clear();
      current.clear();
      System.out.println();
    }
  }
  return change;
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
  //sample argument: 000004028406000005100030600000301000087000140000709000002010003900000507670400000
  //solved:          735164928426978315198532674249381756387256149561749832852617493914823567673495281
  //test candidates: [6][0] = 2, 6, 9

  
  String boardInput = args[0];
  int[][] input = stringToBoard(boardInput);
  Sudoku s = new Sudoku(input);
  

  /*
  int[][] input = {{0,0,0,0,0,4,0,2,8},
   {4,0,6,0,0,0,0,0,5},
   {1,0,0,0,3,0,6,0,0},
   {0,0,0,3,0,1,0,0,0},
   {0,8,7,0,0,0,1,4,0},
   {0,0,0,7,0,9,0,0,0},
   {0,0,2,0,1,0,0,0,3},
   {9,0,0,0,0,0,5,0,7},
   {6,7,0,4,0,0,0,0,0}};
  Sudoku s = new Sudoku(input);
  */
  s.printBoard();
  //System.out.println(s.isSolved());
  s.solve();
  
  ArrayList<Integer> test = new ArrayList<Integer>();
  
  boolean[] can = s.candidates(6,0);
  for (int i = 1; i < 10; i++) {
    System.out.println(can[i]);
  }
  
  s.printBoard();
 }
}
