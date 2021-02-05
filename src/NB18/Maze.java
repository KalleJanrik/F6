package NB18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    int rows, columns;
    Cell[][] mazeMatrix;

    ;
    private Position currentP, goal, start;

    public Maze() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Labyrint.txt"));
            rows = Integer.parseInt(in.readLine()) + 2;
            columns = Integer.parseInt(in.readLine()) + 2;
            mazeMatrix = new Cell[rows][columns];
            for (int j = 0; j < columns; j++) {
                mazeMatrix[0][j] = Cell.WALL;
                mazeMatrix[rows - 1][j] = Cell.WALL;
            }
            for (int i = 1; i < rows - 1; i++) {
                mazeMatrix[i][0] = Cell.WALL;
                mazeMatrix[i][columns - 1] = Cell.WALL;
            }
            for (int i = 1; i < rows - 1; i++) {
                String s = in.readLine();
                for (int j = 1; j < columns - 1; j++) {
                    mazeMatrix[i][j] = Cell.OPEN;
                    if (s.charAt(j - 1) == '*')
                        mazeMatrix[i][j] = Cell.WALL;
                    else if (s.charAt(j - 1) == 'g') {
                        goal = new Position(i, j);
                    } else if (s.charAt(j - 1) == 's') {
                        currentP = new Position(i, j);
                        start = currentP;
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public boolean solve() {
        return solve(currentP);
    }

    private boolean solve(Position p) {
        //Markera ruta som korrekt väg
        mazeMatrix[p.row][p.column] = Cell.CORRECT;
        //Om ruta är målet
        if (p.equals(goal)) {
            //returnera true
            return true;
        } else {//annars
            //Om ruta ovanför är öppen och obesökt
            if (mazeMatrix[p.row - 1][p.column] == Cell.OPEN && mazeMatrix[p.row - 1][p.column] != Cell.VISITED) {
                //Om hittaUt(ruta ovanför) returnera true
                if (solve(new Position(p.row - 1, p.column)))
                    return true;
            }
            //Om ruta till höger är öppen och obesökt
            if (mazeMatrix[p.row][p.column + 1] == Cell.OPEN && mazeMatrix[p.row][p.column + 1] != Cell.VISITED) {
                //Om hittaUt(ruta till höger) returnera true
                if (solve(new Position(p.row, p.column + 1)))
                    return true;
            }
            //Om ruta nedanför är öppen och obesökt
            if (mazeMatrix[p.row + 1][p.column] == Cell.OPEN && mazeMatrix[p.row + 1][p.column] != Cell.VISITED) {
                //Om hittaUt(ruta nedanför) returnera true}
                if (solve(new Position(p.row + 1, p.column)))
                    return true;
            }
            //Om ruta till vänster är öppen och obesökt
            if (mazeMatrix[p.row][p.column - 1] == Cell.OPEN && mazeMatrix[p.row][p.column - 1] != Cell.VISITED) {
                //Om hittaUt(ruta till vänster) returnera true}
                if (solve(new Position(p.row, p.column - 1)))
                    return true;
            }
            //Markera ruta som besökt
            mazeMatrix[p.row][p.column] = Cell.VISITED;
            return false;
        }
    }

    /*public void print(){
        for(int i=1;i<rows-1;i++){
            for(int j=1;j<columns-1;j++)
                System.out.print(mazeMatrix[i][j].ordinal());
            System.out.println();
        }
    }
     */
    public void print() {
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                if (new Position(i, j).equals(start)) {
                    System.out.print("S");
                } else if (new Position(i, j).equals(goal)) {
                    System.out.print("G");
                } else if (mazeMatrix[i][j] == Cell.CORRECT) {
                    System.out.print("O");
                } else if (mazeMatrix[i][j] == Cell.WALL) {
                    System.out.print("*");
                } else if (mazeMatrix[i][j] == Cell.VISITED) {
                    System.out.print("-");
                } else if (mazeMatrix[i][j] == Cell.OPEN) {
                    System.out.print(" ");
                } else {
                    System.out.print(mazeMatrix[i][j].ordinal());
                }
            }
            System.out.println();
        }
    }

    public enum Cell {WALL, OPEN, CORRECT, VISITED}

    private class Position {
        int row, column;

        public Position(int r, int c) {
            row = r;
            column = c;
        }

        public boolean equals(Position p) {
            return (row == p.row && column == p.column);
        }
    }

}
