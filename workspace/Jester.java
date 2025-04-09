// Rachel Jenny
// Period 7
// 2-28-2025

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
// Rules for the Jester: can move up to two squares horizontally and vertically, but not diagonally. 
public class Jester extends Piece{
    
    public Jester(boolean isWhite, String img_file) {
     super(isWhite, img_file);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
ArrayList<Square> moves = new ArrayList<Square>();
    	
    	
    	// to move one space in any direction
    	// left
    	if(start.getCol()-1>=0)
    	{
    		moves.add(board[start.getRow()][start.getCol()-1]);
    	}
    	// right
    	if(start.getCol()+1<8)
    	{
    		moves.add(board[start.getRow()][start.getCol()+1]);
    	}
    	// up
    	if(start.getRow()-1>=0)
    	{
    		moves.add(board[start.getRow()-1][start.getCol()]);
    	}
    	// down
    	if(start.getRow()+1<8)
    	{
    		moves.add(board[start.getRow()+1][start.getCol()]);
    	}
    	
    	
    	// to move two spaces in any direction
    	// left
    	if(start.getCol()-2>=0)
    	{
    		moves.add(board[start.getRow()][start.getCol()-2]);
    	}
    	// right
    	if(start.getCol()+2<8)
    	{
    		moves.add(board[start.getRow()][start.getCol()+2]);
    	}
    	// up
    	if(start.getRow()-2>=0)
    	{
    		moves.add(board[start.getRow()-2][start.getCol()]);
    	}
    	// down
    	if(start.getRow()+2<8)
    	{
    		moves.add(board[start.getRow()+2][start.getCol()]);
    	}
    	
    	return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    
    // How this piece moves: can move up to two squares vertically or horizontally in any direction, but not diagonally.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	
    	ArrayList<Square> moves = new ArrayList<Square>();
    	
    	
    	// to move one space in any direction
    	// left
    	if(start.getCol()-1>=0 && (!b.getSquareArray()[start.getRow()][start.getCol()-1].isOccupied() || b.getSquareArray()[start.getRow()][start.getCol()-1].getOccupyingPiece().getColor()!= this.getColor()))
    	{
    		moves.add(b.getSquareArray()[start.getRow()][start.getCol()-1]);
			if(start.getCol()-2>=0 && (!b.getSquareArray()[start.getRow()][start.getCol()-2].isOccupied() || b.getSquareArray()[start.getRow()][start.getCol()-2].getOccupyingPiece().getColor()!= this.getColor()))
    		{
				if(!moves.get(moves.size()-1).isOccupied()){
    				moves.add(b.getSquareArray()[start.getRow()][start.getCol()-2]);
				}
    		}
    	}
    	// right
    	if(start.getCol()+1<8 && (!b.getSquareArray()[start.getRow()][start.getCol()+1].isOccupied() || b.getSquareArray()[start.getRow()][start.getCol()+1].getOccupyingPiece().getColor()!= this.getColor()))
    	{
    		moves.add(b.getSquareArray()[start.getRow()][start.getCol()+1]);
			if(start.getCol()+2<8 && (!b.getSquareArray()[start.getRow()][start.getCol()+2].isOccupied() || b.getSquareArray()[start.getRow()][start.getCol()+2].getOccupyingPiece().getColor()!= this.getColor()))
    		{
				if(!moves.get(moves.size()-1).isOccupied()){
    				moves.add(b.getSquareArray()[start.getRow()][start.getCol()+2]);
				}
    		}
    	}
    	// up
    	if(start.getRow()-1>=0 && (!b.getSquareArray()[start.getRow()-1][start.getCol()].isOccupied() || b.getSquareArray()[start.getRow()-1][start.getCol()].getOccupyingPiece().getColor()!= this.getColor()))
    	{
    		moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()]);
			if(start.getRow()-2>=0 && (!b.getSquareArray()[start.getRow()-2][start.getCol()].isOccupied() || b.getSquareArray()[start.getRow()-2][start.getCol()].getOccupyingPiece().getColor()!= this.getColor()))
    		{
				if(!moves.get(moves.size()-1).isOccupied()){
    				moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()]);
				}
    		}
    	}
    	// down
    	if(start.getRow()+1<8 && (!b.getSquareArray()[start.getRow()+1][start.getCol()].isOccupied() || b.getSquareArray()[start.getRow()+1][start.getCol()].getOccupyingPiece().getColor()!= this.getColor()))
    	{
    		moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()]);
			if(start.getRow()+2<8 && (!b.getSquareArray()[start.getRow()+2][start.getCol()].isOccupied() || b.getSquareArray()[start.getRow()+2][start.getCol()].getOccupyingPiece().getColor()!= this.getColor()))
    		{
				if(!moves.get(moves.size()-1).isOccupied()){
    				moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()]);
				}
    		}
    	}
    	
    	return moves;
    }
}