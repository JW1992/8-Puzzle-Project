import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
    	pq.insert(initial);
    	//while(pq.size()>0){
    		Board tempBoard = pq.delMin();
    		for(Board bTemp : tempBoard.neighbors()){
    			StdOut.println(bTemp.hamming());
    			StdOut.println(bTemp.toString());
    			pq.insert(bTemp);
    		}
    	//}
    	while(pq.size()>0){
    		Board bTemp = pq.delMin();
    		StdOut.println(bTemp.hamming());
    		StdOut.println(bTemp.toString());
    	}
    }
    public boolean isSolvable()            // is the initial board solvable?
    {//debug
    	return true;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {//debug
    	return -1;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {//debug
    	return null;
    }
    private class BoardHammingComparator implements Comparator<Board>{
    	@Override
    	public int compare(Board b1, Board b2){
    		return b1.hamming() - b2.hamming();
    	}
    }
    private Comparator<Board> comparator = new BoardHammingComparator();
    private MinPQ<Board> pq = new MinPQ<Board>(comparator);
    
    
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
    	int[][] nTemp = new int[3][3];
    	for(int i = 0; i<3; i++){
    		for(int j=0; j<3; j++){
    			nTemp[i][j] = i*3+j;
    		}
    	}
    	Board tempB = new Board(nTemp);
    	StdOut.println(tempB.toString());
    	Solver s = new Solver(tempB);
    }
}
