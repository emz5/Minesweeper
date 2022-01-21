import java.util.Random;
//Emily
//I received help from Hodge Dauler, Akira Van de Groenendaal, Andrew Xu, Alicia Ye, and Jeffery Burt
public class MyMineModel implements MineModel {
    /** Starts a new game of Mine Sweeper.  This method creates a field of the indicated size,
     * initializes it, and places the indicated number of mines on the field.
     * @param numRows the number of rows for the field
     * @param numCols the number of columns for the field
     * @param numMines the number of mines to place in the field**/
    int numRows;
    int numCols;
    int numMines;
    int numFlags;
    boolean isgamestarted = false;
    boolean isgameover;
    boolean isplayerdead;
    boolean isgamewon;
    MyCell[][] gamegrid;
    long startTime;
    Random randmine = new Random();

    public void newGame(int numRows, int numCols, int numMines){
        this.numRows = numRows;
        this.numCols = numCols;
        this.numMines = numMines;
        this.numFlags = 0;
        gamegrid = new MyCell[numRows][numCols];
        isgamestarted = true;
        isgameover = false;
        isplayerdead = false;
        isgamewon = false;
        for(int r = 0; r < numRows; r += 1){
            for(int c = 0; c < numCols; c += 1){
                gamegrid[r][c] = new MyCell(r, c, this);
            }
        }
        startTime = System.currentTimeMillis();
        for(int m = 0; m < numMines; m +=1){
            int mcol = (int)(Math.random()*numCols); //randmine.nextInt(numCols);
            int mrow = (int)(Math.random()*numRows); //randmine.nextInt(numRows);
            if(gamegrid[mrow][mcol].isMine() == true){
                m -= 1;
            } else{
                gamegrid[mrow][mcol].makeMine();
            }

            //create 2 random number one for row one for col
            //if statement if cell location is null
            //if null then create cell where isMine = true
            //location is that row or col
        }
        //randomly place mines go through each row and col and random generate row and col then check if that location is null if it's null then you make it a mine
        //for loop there you go through the rows and cols and then if the row or col is null and not a mine then make it regualr cell
    }

    /** Returns the number of rows in the field. */
    public int getNumRows(){return numRows;}

    /** Returns the number of columns in the field. */
    public int getNumCols(){return numCols;}

    /** Returns the total number of mines in the field. */
    public int getNumMines(){return numMines;}

    /** Returns the number of flags that have been placed in the field.**/
    public int getNumFlags(){return numFlags;}

    /** Returns the number of seconds that have elapsed since this game started.**/
    public int getElapsedSeconds(){
        long currentTime = System.currentTimeMillis();
        long dtime = currentTime - startTime;
        int time = (int)(dtime/1000);
        return time;
    }

    /** Returns the Cell in the field at the given coordinates.
     * @param row the row number of the cell [precondition: <code> 0 <= row < getNumRows()</code>]
     * @param col the column number of the cell [precondition: <code> 0 <= col < getNumCols()</code>]
     * @return a valid, non-null Cell object**/
    public Cell getCell(int row, int col){return gamegrid[row][col];}

    /** Called when the player has stepped onto the cell at the given coordinates.
     * This cell is now made visible.  If it is a mine, the player is dead and the game is over.
     * If it is a safe (non-mine) cell with no neighboring mines (a zero cell), then every cell
     * that can be reached from this one by only stepping on zero cells should be made visible as well.
     * @param row the row number of the cell [precondition: <code> 0 <= row < getNumRows()</code>]
     * @param col the column number of the cell [precondition: <code> 0 <= col < getNumCols()</code>]**/
    public void stepOnCell(int row, int col){
        gamegrid[row][col].makeVisible();
        if(gamegrid[row][col].getNeighborMines() == 0){
            for(int neighborcol = col - 1; neighborcol <= col + 1; neighborcol += 1){
                for(int neighborrow = row - 1; neighborrow <= row + 1; neighborrow += 1){
                    if(neighborrow >= 0 && neighborrow < numRows){
                        if(neighborcol >= 0 && neighborcol < numCols){     //checks cells around and on the grid

                            if(gamegrid[neighborrow][neighborcol].isVisible() == false){
                                stepOnCell(neighborrow, neighborcol);
                            }
                        }
                    }
                }
            }
        }
        if (gamegrid[row][col].isMine() == true){
            isgameover = true;
            isplayerdead = true;
        }
    }

    /** Called when the player wants to change the flagged status of a cell.
     * If the indicated cell has no flag, then place a flag there.
     * If the indicated cell already has a flag, then remove it.
     * Note that it is safe to place a flag on any cell, even if it has a mine.
     * @param row the row number of the cell [precondition: <code> 0 <= row < getNumRows()</code>]
     * @param col the column number of the cell [precondition: <code> 0 <= col < getNumCols()</code>]**/
    public void placeOrRemoveFlagOnCell(int row, int col){
        if (gamegrid[row][col].isFlagged() == false){
            gamegrid[row][col].makeFlag();
        } else{
            gamegrid[row][col].deleteFlag();
        }
    }

    /** Returns true if a game was started, whether or not it has ended.
     * Returns false only if no game has ever been started.**/
    public boolean isGameStarted(){return isgamestarted;}

    /** Returns true if the current game has ended.
     * Returns false if the current game is running.
     * If the game hasn't started, then the value returned by this method is not defined.**/
    public boolean isGameOver() {return isgameover;}

    /** Returns true if the player is dead, because they stepped on a mine.
     * Returns false if the player has not yet stepped on a mine.**/
    public boolean isPlayerDead(){return isplayerdead;}

    /** Returns true if the player has won the current game, by exposing every non-mine cell.
     * Returns false if player hasn't won (either the game hasn't started, it is still going, or the player is dead).**/
    public boolean isGameWon(){
        int numCells = numRows * numCols - numMines;
        for(int row = 0; row < numRows; row += 1){
            for(int col = 0; col < numCols; col +=1){
                if(!gamegrid[row][col].isMine()){
                    if(gamegrid[row][col].isVisible()){
                       numCells -= 1;
                    }
                }
                if(numCells == 0){
                    isgamewon = true;
                }
            }
        }
        return isgamewon;
    }
}
