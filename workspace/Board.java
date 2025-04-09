

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
    private static final String RESOURCES_WASSASSIN_PNG = "WAssassin.png";
    private static final String RESOURCES_BASSASSIN_PNG = "BAssassin.png";
    private static final String RESOURCES_BANIPAWN_PNG = "bAntiPawn.png";
    private static final String RESOURCES_WANIPAWN_PNG = "wAntiPawn.png";
    private static final String RESOURCES_WJESTER_PNG = "wjester.png";
    private static final String RESOURCES_BJESTER_PNG = "bjester.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    //precondition: the class Square works as intended
    //postcondition: creates a board of squares alternating colors
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
        int rowColor = 0;

        for (int i=0; i<board[0].length; i++){
            for(int j=0; j<board.length; j++){
                if(rowColor % 2 ==0 ){
                    board[i][j]= new Square(this, true, i,j);
                    this.add(board[i][j]);
                }
                else{
                    board[i][j]= new Square(this, false, i,j);
                    this.add(board[i][j]);
                }
                rowColor++;

            }
            rowColor++;
        }  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.

    //precondition: board is initialized
    //postcondition: creates My Piece, called the Assassin, on the board where the regular pieces would go. white on ones side, black on the other
    private void initializePieces() {
        
        //kings
        board[7][4].put(new King(false, RESOURCES_BKING_PNG));
        board[0][4].put(new King(true, RESOURCES_WKING_PNG));
        
        //Queen
        board[7][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[0][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));


        //bishops
        board[0][5].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[7][5].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
        board[0][2].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[7][2].put(new Bishop(false, RESOURCES_BBISHOP_PNG));

        //rooks
        board[0][0].put(new Rook(true, RESOURCES_WROOK_PNG));
        board[7][7].put(new Rook(false, RESOURCES_BROOK_PNG));
        board[0][7].put(new Rook(true, RESOURCES_WROOK_PNG));
        board[7][0].put(new Rook(false, RESOURCES_BROOK_PNG));

        //anti pawns
        board[6][0].put(new AntiPawn(false, RESOURCES_BANIPAWN_PNG));
        board[1][0].put(new AntiPawn(true, RESOURCES_WANIPAWN_PNG));
        board[6][6].put(new AntiPawn(false, RESOURCES_BANIPAWN_PNG));
        board[1][6].put(new AntiPawn(true, RESOURCES_WANIPAWN_PNG));
        board[6][2].put(new AntiPawn(false, RESOURCES_BANIPAWN_PNG));
        board[1][2].put(new AntiPawn(true, RESOURCES_WANIPAWN_PNG));
        board[6][4].put(new AntiPawn(false, RESOURCES_BANIPAWN_PNG));
        board[1][4].put(new AntiPawn(true, RESOURCES_WANIPAWN_PNG));

        //jesters
        board[1][1].put(new Jester(true, RESOURCES_WJESTER_PNG));
        board[6][3].put(new Jester(false, RESOURCES_BJESTER_PNG));
        board[1][5].put(new Jester(true, RESOURCES_WJESTER_PNG));
        board[6][1].put(new Jester(false, RESOURCES_BJESTER_PNG));
        board[1][3].put(new Jester(true, RESOURCES_WJESTER_PNG));
        board[6][5].put(new Jester(false, RESOURCES_BJESTER_PNG));
        board[1][7].put(new Jester(true, RESOURCES_WJESTER_PNG));
        board[6][7].put(new Jester(false, RESOURCES_BJESTER_PNG));

        //assassins
        board[0][1].put(new Assassin(true, RESOURCES_WASSASSIN_PNG));
        board[7][1].put(new Assassin(false, RESOURCES_BASSASSIN_PNG));
        board[0][6].put(new Assassin(true, RESOURCES_WASSASSIN_PNG));
        board[7][6].put(new Assassin(false, RESOURCES_BASSASSIN_PNG));

            
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img.getScaledInstance(50,50, Image.SCALE_DEFAULT), currX, currY, null);
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 

    //precondition: pieces have been made and board has been made
    //postcondition: allows the pieces to be dragged around. Depending on who's turn it is, the piece can be moed to a square, if it is legal
    @Override
    public void mouseReleased(MouseEvent e) {
    
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        

        
        for(Square [] row: board){
            for(Square s:row){
                s.setBorder(null);
            }
        }

        if(currPiece.getColor()== whiteTurn){
            for( Square i : currPiece.getLegalMoves(this, fromMoveSquare)){
                if( endSquare == i ){
                    endSquare.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    Piece oldPiece = endSquare.getOccupyingPiece();
                    endSquare.put(currPiece);
                    fromMoveSquare.put(null);

                    
                    if(isInCheck(whiteTurn)){
                        //undo the move that just happened
                        endSquare.put(oldPiece);
                        fromMoveSquare.put(currPiece);
                         
                    }
                    else{

                        whiteTurn = !whiteTurn;
                    }
                    System.out.println(currPiece.toString());
                }
            }
        }
        
        
        
        //using currPiece
        
       
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;
      
        if(currPiece !=null){
            for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)){
                s.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
	public boolean isInCheck(boolean kingColor){
        for(Square [] row: board){
            for(Square s:row){
                if(s.isOccupied() && s.getOccupyingPiece().getColor() != kingColor){
                    ArrayList<Square> controlledSquares = s.getOccupyingPiece().getControlledSquares(this.getSquareArray(), s);
                    for(Square checkThis : controlledSquares){
                        if(checkThis.isOccupied() && checkThis.getOccupyingPiece() instanceof King && checkThis.getOccupyingPiece().getColor() == kingColor){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    

}