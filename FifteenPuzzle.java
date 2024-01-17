
/**
 * FifteenPuzzle maintains the internal representation of a 15 puzzle. 
 * This is a 4x4 2D array of integers, numbered 0..15. 0 denotes the space. 
 * A legal move is to shift the space to an adjacent square on the grid. 
 * The puzzle is finished when all tiles are in the correct position as defined by goal. 
 * 
 * @author Lyndon While
 * @version May 2020
 */
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

public class FifteenPuzzle 
{ 
    private       int[][] grid, nextgrid; // the current positions of the tiles and the space, denoted by 0..15
    private       int xspace;   // xspace,yspace are the current coordinates of the space
    private       int yspace;
    private final int size = 4; // the number of tiles across and down 
    private static final int[][] goal = {{1,5,9,13}, {2,6,10,14}, {3,7,11,15}, {4,8,12,0}}; // the tile positions in the goal state

    // these two are public and static so that they can be used in BlueJ 
    // when creating an object, enter e.g. FifteenPuzzle.close4 in the argument box
    public static final int[][] example4 = {{ 5,11,14, 0}, { 9, 3,13, 7}, { 2, 8,10,12}, { 4, 1,15, 6}};
    public static final int[][] close4   = {{ 1, 5, 9,13}, { 2, 6,10,14}, { 3, 7,11, 0}, { 4, 8,12,15}};
    
    string 

    /** This constructor sets up the grid using initialGrid, and it initialises xspace and yspace.
     *  initialgrid is assumed to be a legal position.
     */
    public FifteenPuzzle (int[][] initialGrid)
    {
        grid = initialGrid;

        for (int i =0; i<grid.length; i++){
            for (int j = 0; j<grid[0].length; j++){
                if (grid[i][j] == 0){
                    xspace = i;
                    yspace = j;
                    i = grid.length;
                    j = grid[0].length; //stops the loop, we have found what we need
                }
            }

        }
    }

    /** This constructor sets up the grid by copying goal and then making random moves. 
     *  It also initialises xspace and yspace.
     */
    public FifteenPuzzle ()
    {
       this(goal);
       Random rg = new Random();
        
        for (int i = 0; i<=1000; i++)
        {
            int x = xspace + rg.nextInt(3) - 1;
            int y = yspace + rg.nextInt(3) - 1;
            
            if (x>=0 && x<=3 && y>=0 && y<=3) moveTile(x,y);
           
        }
    }

    /**
     * Returns the grid.
     */
    public int[][] getGrid()
    {
        return grid;
    }

    /**
     * Returns the size.
     */ 
    public int getSize()
    {
        return size;
    }

    /**
     * Returns true iff x,y is on the board and adjacent to the space. 
     * Either x must be xspace and y must be yspace plus or minus 1, 
     * or     y must be yspace and x must be xspace plus or minus 1.
     */
    public boolean legalClick(int x, int y)
    {
        boolean legal = false;
        if (x == xspace) {
            if (y == yspace -1 || y == yspace + 1) legal = true;
        }
        if (y == yspace) {
            if (x == xspace -1 || x == xspace + 1) legal = true;
        }
        return legal;
    }

    /**
     * Returns true iff the puzzle is in the goal state.
     */
    public boolean finished()
    {
        // TODO 7
        for (int i = 0; i<size; i++){
            if (!(Arrays.equals(grid[i],goal [i])))
            return false;
        }
        return true;
    }

    /**
     * If x,y is a legal click on the board, swaps the tile at x,y with the space;
     * otherwise does nothing.
     */ 
    public void moveTile (int x, int y) 
    {
        if (legalClick(x,y)) {
            grid[xspace][yspace] = grid[x][y];
            grid[x][y] = 0;
            xspace = x;
            yspace = y;

        }
    }
}
