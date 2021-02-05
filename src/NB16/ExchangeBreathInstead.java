package NB16;

import java.util.LinkedList;
import java.util.Queue;

public class ExchangeBreathInstead {

    public static void main(String[] args) {
        System.out.println("Should be 1: " + exchange(5, 1, 3));
        System.out.println("Should be 8: " +exchange(2, 1, 3));
        System.out.println("Should be 6: " +exchange(22, 22, 13));
        System.out.println("Should be ?: " +exchange(1, 0, 0));
    }
    public static int exchange(int blue, int white, int red){
        return exchangeBreadth(blue, white, red);
    }

    private static int exchangeBreadth(int blue, int white, int red) {
        Queue<State> q = new LinkedList<State>();
        State t = new State(0,blue,white,red);
        while(!t.finished()){
            if((t.blue -1) >= 0)
                q.offer(new State(t.exchanges+1,t.blue-1,t.white+3,t.red+1));
            if((t.white -1) >= 0)
                q.offer(new State(t.exchanges+1,t.blue+3,t.white-1,t.red+4));
            if((t.red -1) >=0)
                q.offer(new State(t.exchanges+1,t.blue+2,t.white+1,t.red-1));
            t = q.poll();
        }
        return t.exchanges;
    }

    private static class State{
        public int exchanges;
        public int blue,white,red;

        public State(int exchanges, int blue, int white, int red) {
            this.exchanges = exchanges;
            this.blue = blue;
            this.white = white;
            this.red = red;
        }
        public boolean finished(){
            return (exchanges == 15 || (blue == white && white == red));
        }
    }


    private static int exchangeDepth(int depth, int blue, int white, int red) {

        if ( depth == 0 || (blue == white && white == red))
            return 15 - depth;

        int blueInt, whiteInt, redInt;

        blueInt = exchangeDepth(depth - 1, blue - 1, white + 3, red + 1);
        whiteInt = exchangeDepth(depth - 1, blue + 3, white - 1, red + 4);
        redInt = exchangeDepth(depth - 1, blue + 2, white + 1, red - 1);

        return Math.min(Math.min(blueInt,whiteInt),redInt);

    }
}
