// Disclaimer: This sample Java class is downloaded from http://cogsci.ucsd.edu/~batali/cogsci109/searches.html
//             and used as a case study for Introduction to AI module taught in the University of Nottingham,  
//             Malaysia Campus for the 2007/2008 session   

import java.util.*;

public class searchers { 

    public static int [] tiles1 =  { 1, 0, 3, 4, 2, 5, 6, 7, 8 };
    public static int [] tiles2 =  { 1, 2, 3, 4, 7, 5, 0, 6, 8 };
    public static int [] tiles4 =  { 4, 1, 3, 2, 0, 5, 6, 7, 8 };
    public static int [] tiles6 =  { 2, 4, 3, 1, 5, 8, 6, 7, 0 };
    public static int [] tiles8 =  { 0, 1, 3, 4, 2, 5, 7, 8, 6 }; //edited as per stated in question
    public static int [] tiles10 = { 1, 3, 5, 6, 2, 8, 0, 7, 4 };
    public static int [] tiles11 = { 4, 0, 3, 6, 1, 7, 8, 2, 5 };

    public static Ivector bfs (State s0) {
	Bag b = new Bag();

		int depthlimit = 0;
		State ns = s0;
		while(State.isGoal(ns)==false){
			b.pushFront(s0);
			ns=s0;
			depthlimit++;
			while (b.size() >0){
				ns = b.popFront();
	    		if (State.isGoal(ns)){ break;
				}else if (ns.moves.size()<depthlimit){
					for (int m = 8; m >0; --m) {

						if (ns.moves.size() ==0 || (m!=ns.moves.last())){
							if (State.canMove(ns.tiles, m)) {
								State ms = new State(ns.tiles, ns.moves, 0);
								State.doMove(ms.tiles, m);
								ms.moves.push(m);
								b.pushFront(ms);
							}
						}

					}
				}
			}
		}
		return ns.moves;
    }




    public static void main (String[] args) { 
	State s0 = new State (tiles8);
	int istates = State.getCount();
	Ivector sv = bfs(s0);
	int nstates = State.getCount()-istates;
	System.out.println ("states: "+nstates+"; steps "+sv.size());
	System.out.println(sv);
	State.doMoves(s0.tiles,sv);
	State.printState(s0);



    }
}


