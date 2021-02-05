package Forelasning;



import java.util.LinkedList;
import java.util.Queue;

public class elevator {

    static int antalResorHiss(int n, int upp, int ned, int position, int destination, int antalResor)
    {
        if(position == destination)
            return antalResor;
        else if(antalResor > 30)
            return Integer.MAX_VALUE;
        else {
            int antalUpp = Integer.MAX_VALUE , antalNed = Integer.MAX_VALUE;
            if(position+upp<=n){
                antalUpp = antalResorHiss(n,upp,ned,position+upp,destination,antalResor+1);
            }if(position-ned>=1){
                antalNed = antalResorHiss(n,upp,ned,position - ned,destination,antalResor+1);
            }
            return Math.min(antalNed,antalUpp);
        }
    }
    static int antalResorHiss(int n, int upp, int ned, int destination)
    {
        return antalResorHiss(n,upp,ned,1,destination,0);
    }

    static int antalResorBredd(int n, int upp, int ned, int destination){
        Queue<State> q = new LinkedList<State>();
        State t = new State(1,0);
        while (t.position != destination){
            if(t.position+upp <= n){
                q.offer(new State(t.position+upp,t.antalResor+1));
            }
            if(t.position-ned >=1){
                q.offer(new State(t.position-ned,t.antalResor+1));
            }
            t = q.poll();
        }
        return t.antalResor;
    }

    private static class State{
        public int position, antalResor;
        public State(int p, int a){
            position = p;
            antalResor = a;
        }
    }
}
