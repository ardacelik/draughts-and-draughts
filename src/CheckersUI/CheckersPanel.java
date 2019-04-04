package CheckersUI;

import checkers.Board;
import checkers.Coordinate;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CheckersPanel extends JPanel {

    final static String BLACK_PAWN = "/assets/black_pawn.png";
    final static String WHITE_PAWN = "/assets/white_pawn.png";
    final static String BOARD = "/assets/board.png";
    final static String WHITE_KING = "/assets/white_pawn_king.png";
    final static String BLACK_KING = "/assets/black_pawn_king.png";


    ImageIcon  black_pawn, white_pawn, board, board1, board2, black_king, white_king;

    boolean newBoard = true;

    //pawns: Arraylist of Pawns to be drawn to board
    ArrayList<Pawn> pawns = new ArrayList<Pawn>();

    //allBoardPoints: Arraylist of board points, holds coordinates of all center points of squares on board
    ArrayList<Point> allBoardPoints = new ArrayList<>();

    Board boardO = new Board();
    String turn = "your turn";
    String user_move = "";
    String computer_move = "";
    int theme = 1;


    public CheckersPanel() {


        initAllpositions();

        black_pawn = new ImageIcon(getClass().getResource(BLACK_PAWN));
        white_pawn = new ImageIcon(getClass().getResource(WHITE_PAWN));
        board = new ImageIcon(getClass().getResource(BOARD));
        white_king = new ImageIcon(getClass().getResource(WHITE_KING));
        black_king = new ImageIcon(getClass().getResource(BLACK_KING));
        boardO.initialize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            board.paintIcon(this, g, 0, 0);

        if (newBoard) {
            createBoard();
        }

        for (int i=0;i<allBoardPoints.size();i++){
          System.out.println(allBoardPoints.get(i) + " ");
        }
        System.out.println();
        drawPawns(g);
    }


    //initializes allboardpoints for a new game
    private void initAllpositions() {
        int lignes = 0;
        for (int i = 0; i < 32; i++) {
            Point blackpos1 = new Point(5, 5);
            if (i != 0 && i % 4 == 0) {
                lignes++;
            }
            if (lignes % 2 == 0) {
                blackpos1.x = (i % 4) * 75 * 2 + 5;
                blackpos1.y = lignes * 75 + 5;
            }
            else {
                blackpos1.x = (i % 4) * 75 * 2 + 5 + 75;
                blackpos1.y = lignes * 75 + 5;
            }

            allBoardPoints.add(blackpos1);
        }
    }

    //paints pawn icons to screen
    public void drawPawns(Graphics g) {
        for (Pawn p : pawns) {
            p.image.paintIcon(this, g, (int) p.point.getX(), (int) p.point.getY());
        }
    }

    //reconstructs pawn arraylist with updated coordinates and graphics prior to painting
    private void createBoard() {
        pawns.clear();

        for (int i = 1; i < 33; i++) {
            Coordinate c = new Coordinate(i);
            int color = 0;

            if (boardO.getChecker(c) != null) {
                color = boardO.getChecker(c).getColor();
            }
            if (color == 2) {

                Pawn p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Pawn(allBoardPoints.get(i - 1), white_king);
                } else {
                    p = new Pawn(allBoardPoints.get(i - 1), white_pawn);
                }

                p.posindex = i;
                pawns.add(p);
            }
            if (color == 1) {
                Pawn p = null;
                if (boardO.getChecker(c).isKing()) {
                    p = new Pawn(allBoardPoints.get(i - 1), black_king);
                } else {
                    p = new Pawn(allBoardPoints.get(i - 1), black_pawn);
                }
                p.posindex = i;
                pawns.add(p);
            }

        }
    }
}
