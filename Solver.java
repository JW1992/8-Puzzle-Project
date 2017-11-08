import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
    	pq.insert(new Move(initial, 0, null, false));
    	pq.insert(new Move(initial.twin(), 0, null, true));
    	Move tempMove;
    	while(pq.size()>0){    		
    		tempMove = pq.delMin();
    		if(tempMove.board.hamming()==0){
    			if(tempMove.isTwin)
    			{
    				isSolv=false;
    				nMove = -1;
    			}
    			else{
    				isSolv=true;
    				nMove=tempMove.nStep;
    				moveSteps.push(tempMove.board);
    				while(tempMove.lastMove!=null){
    					tempMove = tempMove.lastMove;
    					moveSteps.push(tempMove.board);
    				}
    			}
    			break;
    		}
    		for(Board bTemp : tempMove.board.neighbors()){
    			//StdOut.println(bTemp.manhattan());
    			//StdOut.println(bTemp.toString());
    			if(tempMove.lastMove!=null&&tempMove.lastMove.board.equals(bTemp)) 
    			{
    			//	StdOut.println("Skipped, same as pred\n");
    			}
    			else pq.insert(new Move(bTemp, tempMove.nStep+1, tempMove, tempMove.isTwin));
    		}
    	}
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
    	return isSolv;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	return nMove;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	return moveSteps;
    }
    private class Move{
    	private final Board board;
    	private final int priority;
    	private final int nStep;
    	private final Move lastMove;
    	private boolean isTwin;
    	
    	public Move(Board inBoard, int step, Move lMove, boolean bTwin){
    		this.board = inBoard;
    		this.nStep = step;
    		this.lastMove = lMove;
    		this.priority = board.manhattan()+step;
    		this.isTwin = bTwin;
    	}
    	public String boardCheck(){
    		return board.toString();
    	}
    	
    }
    private class BoardHammingComparator implements Comparator<Move>{
    	@Override
    	public int compare(Move m1, Move m2){
    		return m1.priority - m2.priority;
    	}
    }
    private Comparator<Move> comparator = new BoardHammingComparator();
    private MinPQ<Move> pq = new MinPQ<Move>(comparator);
    private MinPQ<Move> pq_twin = new MinPQ<Move>(comparator);
    private int nMove;
    private boolean isSolv;
    private Deque<Board> moveSteps = new ArrayDeque<Board>();//Stack in Java has problematic iterator
    
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	int[][] nTemp = new int[3][3];
    	for(int i = 0; i<3; i++){
    		for(int j=0; j<3; j++){
    			nTemp[i][j] = i*3+j;
    		}
    	}
    	/*
    	nTemp[0][0] = 0;
    	nTemp[0][1] = 1;
    	nTemp[0][2] = 3;
    	nTemp[1][0] = 4;
    	nTemp[1][1] = 2;
    	nTemp[1][2] = 5;
    	nTemp[2][0] = 7;
    	nTemp[2][1] = 8;
    	nTemp[2][2] = 6;*/
    	Board tempB = new Board(nTemp);
    	StdOut.println(tempB.toString());
    	StdOut.println("Twin:");
    	StdOut.println(tempB.twin().toString());
    	Solver s = new Solver(tempB);
    	if(s.isSolvable()){
    		StdOut.println("Solution:");
    		for(Board tB : s.solution()){
    			StdOut.println(tB);
    		}
    	}
    	else StdOut.println("Not solvable");
    }
}
