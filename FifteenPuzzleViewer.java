
/**
 * FifteenPuzzleViewer displays a 15 puzzle and interacts with the user.
 * 
 * @author Lyndon While
 * @version May 2020
 */

import java.awt.event.*;
import java.awt.*;

public class FifteenPuzzleViewer implements MouseListener 
{
    private FifteenPuzzle puzzle; // the internal puzzle 
    private int           size;   // the size of the puzzle
    private SimpleCanvas  sc;     // the canvas for display 
    private int count = 0;

    private final static int cellsize = 100;
    private final static Color Numbercolor = Color.black;
    private final static Color Tilecolor = Color.gray;
    private final static Color Gridcolor = Color.white;

    /**
     * Sets up the three fields and draws the initial puzzle.
     */
    public FifteenPuzzleViewer(FifteenPuzzle puzzle, int size) 
    {
        this.puzzle = puzzle;
        this.size = size;
        sc = new SimpleCanvas(" 15 puzzles ", size*cellsize, size*cellsize, Gridcolor);
        sc.addMouseListener(this);
        drawGrid();
    }

    /**
     * Draws all of the tiles on sc.
     */
    public void drawGrid()
    {  
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){ 
                if (puzzle.getGrid()[i][j] != 0) drawTile(i, j, Tilecolor);

                else 
                    drawTile (i,j, Gridcolor);
            }
        }
    }

    /**
     * Draws the tile at x,y in colour c at the appropriate spot on sc.
     */
    private void drawTile   (int x, int y, Color c)
    {
        sc.drawRectangle(x*cellsize + 10 ,y*cellsize +10 , (x+1)*cellsize -10,(y+1)*cellsize -10, c);

        if (puzzle.getGrid()[x][y] !=0){
            if (puzzle.getGrid()[x][y] <10){
                sc.drawString(puzzle.getGrid()[x][y], x*cellsize + 50, y*cellsize+50, Numbercolor);
            }
            else {
                sc.drawString(puzzle.getGrid()[x][y], x*cellsize + 45, y*cellsize+50, Numbercolor);

            }
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) 
    {
        for (int i = 0; i<size; i++){
            for (int j = 0; j<size; j++){ 
                if ((e.getX()>= i*cellsize +10 && e.getX()<=(i+1)*cellsize -10) && e.getY()>=j*cellsize + 10 && e.getY() <= (j+1) * cellsize - 10){
                    puzzle.moveTile(i,j);
                }
            }
        }
        drawGrid();
        count+=1;
        if (puzzle.finished()) System.out.println("Well done, you completed in " + count + " moves");
        
    }

    public void mouseExited(MouseEvent e)   {}

    public void mouseClicked(MouseEvent e)  {}

    public void mouseEntered(MouseEvent e)  {}
}
 