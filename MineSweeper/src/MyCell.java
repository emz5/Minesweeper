//Emily
//I received help from Hodge Dauler, Akira Van de Groenendaal, Andrew Xu, Scooch Herbst, ALicia Ye, and Jeffery Burt
public class MyCell implements Cell {
    //this is just a single cell
    private int row;
    private int col;
    private boolean isvisible;
    boolean ismine;
    boolean isflagged;
    MyMineModel allcells;
    int numRows;
    int numCols;
    int neighborrow;
    int neighborcol;
    //make constructors to intialize the variables
    public MyCell(int row, int col, MyMineModel allcells){
        this.row = row;
        this.col = col;
        this.isvisible = false;
        this.ismine = false;
        this.isflagged = false;
        this.allcells = allcells;
        this.numRows = allcells.getNumRows();
        this.numCols = allcells.getNumCols();
        this.neighborrow = neighborrow;
        this.neighborcol = neighborcol;
    }
    /**Returns the row number of this cell.**/
    public int getRow(){return row;}

    /**Returns the column number of this cell.**/
    public int getCol(){return col;}

    /**Makes the cell visible**/
    public void makeVisible(){isvisible = true;}

    /**Returns true if this cell is visible, meaning that its number of neighboring mines should be displayed.**/
    public boolean isVisible(){return isvisible;}

    /**Returns true if this cell contains a mine (regardless of whether it is visible or has been flagged).**/
    public boolean isMine(){return ismine;}

    /**Makes the cell a mine**/
    public void makeMine(){ismine = true;}

    /**Make the cell flagged**/
    public void makeFlag(){isflagged = true;}

    /**Unflag the cell**/
    public void deleteFlag(){isflagged = false;}

    /**Returns true if this cell has been flagged (regardless of whether it is visible or contains a mine).**/
    public boolean isFlagged(){return isflagged;}

    /**
     * Returns the number of mines that are in cells that are adjacent to this one.  Most cells have
     * eight neighbors (N, S, E, W, NE, NW, SE, SW).  Cells along the edges of the field or cells in the
     * corners have fewer neighbors.  Mines are counted regardless of whether or not they are visible
     * or flagged.  If there is a mine in the current cell, it is not counted.
     */
    public int getNeighborMines(){
        int neighbormines = 0;
        for(int ncol = col - 1; ncol <= col + 1; ncol += 1){
            for(int nrow = row - 1; nrow <= row + 1; nrow += 1){
                if(nrow >= 0 && nrow < numRows){
                    if(ncol >= 0 && ncol < numCols){
                        if(allcells.getCell(nrow, ncol).isMine()){
                            neighbormines += 1;
                        }
                    }
                } //check if the cell is even on the grid (0<__<largest row/col)
            }
        }
        return neighbormines;
    }
    //(row - 1, col) etc. for the placement of the cell
}
