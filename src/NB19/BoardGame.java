package NB19;

import java.util.Arrays;
import java.util.Stack;

public class BoardGame {
    public enum Cells{WHITE,BLACK, EMPTY}
    private Cells[] board;
    private Stack<move> solved;

    public void initBoard(){
        board = new Cells[7];
        for (int i = 0; i < board.length; i++) {
            if(i<3)
                board[i] = Cells.BLACK;
            if(i == 3)
                board[i] = Cells.EMPTY;
            if(i>3)
                board[i] = Cells.WHITE;
        }
    }

    public void printBoard(){
        for (Cells cells : board) {
            if (cells == Cells.BLACK)
                System.out.print("⚪");
            if (cells == Cells.EMPTY)
                System.out.print("⬜");
            if (cells == Cells.WHITE)
                System.out.print("⚫");
        }
        System.out.println("");
    }

    public BoardGame(){
        solved = new Stack<>();
        initBoard();
    }

    public boolean solve(){
        if(solve(board,0,3)){
            System.out.println("Number of moves: " + solved.size());
        for (move Move:
             solved) {
            System.out.println(printMove(Move) + " ");
            board = makeMove(board, Move.from, Move.to);
            printBoard();
        }
            System.out.println("");
        return true;
        }
        return false;
    }

    private void changeBoard() {
        for (move move :
                solved) {
            board = makeMove(board,move.from,move.to);
        }
    }

    private String printMove(move move) {
        return move.from + "->" + move.to;
    }

    private boolean solve(Cells[] board, int nrMove, int freeIndex) {
        //printBoard(board);
        if(nrMove > 15)
            return false;
        if(done(board))
            return true;
        if(legalMove(freeIndex - 2) )
            if(solve(makeMove(board,freeIndex-2,freeIndex),nrMove +1, freeIndex -2)){
                solved.push(new move(freeIndex-2,freeIndex));
                return true;
            }
        if(legalMove(freeIndex - 1))
            if(solve(makeMove(board,freeIndex-1,freeIndex),nrMove +1, freeIndex -1)){
                solved.push(new move(freeIndex-1,freeIndex));
                return true;
            }
        if(legalMove(freeIndex + 1))
            if(solve(makeMove(board,freeIndex+1,freeIndex),nrMove +1, freeIndex +1)){
                solved.push(new move(freeIndex+1,freeIndex));
                return true;
            }
        if(legalMove(freeIndex + 2))
            if(solve(makeMove(board,freeIndex+2,freeIndex),nrMove +1, freeIndex +2)){
                solved.push(new move(freeIndex+2,freeIndex));
                return true;
            }
        return false;
    }



    private boolean done(Cells[] board) {
        for (int i = 0; i < board.length; i++) {
            if(i>3 && !(board[i] == Cells.BLACK))
                return false;
            if(i == 3 && !(board[i] == Cells.EMPTY))
                return false;
            if(i<3 && !(board[i] ==Cells.WHITE))
                return false;
        }
        return true;
    }

    private Cells[] makeMove(Cells[] board, int from, int to) {
        Cells[] temp = Arrays.copyOf(board,board.length);
        Cells fromVar = temp[from];
        Cells toVar = temp[to];
        temp[from] = toVar;
        temp[to] = fromVar;
        return temp;
    }

    private boolean legalMove(int i) {
        return 0<= i && i  < board.length;
    }

    private class move {
        int from,to;
        public move(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}
