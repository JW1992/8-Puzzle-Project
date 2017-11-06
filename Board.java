import java.util.*;
import java.lang.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    public Board(int[][] blocks)// construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
    {
    	nDimension = blocks.length;
    	nHamming = 0;
    	nManhattan = 0;
    	nBlock = new int[nDimension][nDimension];
    	nZeroPos = new int[2];
    	
    	for(int i=0; i<nDimension; i++){
    		for(int j=0; j<nDimension; j++){
    			nBlock[i][j] = blocks[i][j];
    			if(blocks[i][j]==0){
    				nZeroPos[0] = i;
    				nZeroPos[1] = j;
    			}
    			if(i==nDimension-1&&j==nDimension-1){
    				if(blocks[i][j]!=0)
    				{
    					nHamming += 1;
    					nManhattan += Math.abs(blocks[i][j]);
    				}
    			}
    			else{
    				if(i*nDimension+j+1!=blocks[i][j]){
    					nHamming += 1;
    					nManhattan += Math.abs(i*nDimension+j+1-blocks[i][j]);
    				}
    			}
    		}
    	}
    	
    }
               
                                           
    public int dimension()                 // board dimension n
    {
    	return nDimension;
    }
    public int hamming()                   // number of blocks out of place
    {
    	return nHamming;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	return nManhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	return (nHamming != 0);
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
    	if(nDimension==1) return this;
	    if(nZeroPos[0]!=0){
	    	int nTemp = nBlock[0][0];
	    	nBlock[0][1] = nBlock[0][0];
	    	nBlock[0][0] = nTemp;
	    	Board tempBoard = new Board(nBlock);
	    	nBlock[0][1] = nBlock[0][0];
	    	nBlock[0][0] = nTemp;
	    	return tempBoard;
	    }
	    else{
	    	int nTemp = nBlock[1][0];
	    	nBlock[1][1] = nBlock[1][0];
	    	nBlock[1][0] = nTemp;
	    	Board tempBoard = new Board(nBlock);
	    	nBlock[1][1] = nBlock[1][0];
	    	nBlock[1][0] = nTemp;
	    	return tempBoard;
	    }
	    
    }
    public boolean equals(Object y)        // does this board equal y?
    {
    	if(y==null) return false;
    	if(! (y instanceof Board)) return false;
    	Board tempBoard = (Board)y;
    	if(this.dimension()!=tempBoard.dimension()) return false;
    	return (this.toString() == tempBoard.toString());
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	List<Board> neighborBoards = new ArrayList<Board>();
    	if(nZeroPos[0]>0){
    		int nTemp = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nBlock[nZeroPos[0]-1][nZeroPos[1]];
    		nBlock[nZeroPos[0]-1][nZeroPos[1]] = nTemp;
    		neighborBoards.add(new Board(nBlock));
    		nBlock[nZeroPos[0]-1][nZeroPos[1]] = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nTemp;
    	}
    	if(nZeroPos[1]>0){
    		int nTemp = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nBlock[nZeroPos[0]][nZeroPos[1]-1];
    		nBlock[nZeroPos[0]][nZeroPos[1]-1] = nTemp;
    		neighborBoards.add(new Board(nBlock));
    		nBlock[nZeroPos[0]][nZeroPos[1]-1] = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nTemp;
    	}
    	if(nZeroPos[0]<nDimension-1){
    		int nTemp = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nBlock[nZeroPos[0]+1][nZeroPos[1]];
    		nBlock[nZeroPos[0]+1][nZeroPos[1]] = nTemp;
    		neighborBoards.add(new Board(nBlock));
    		nBlock[nZeroPos[0]+1][nZeroPos[1]] = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nTemp;
    	}
    	if(nZeroPos[1]<nDimension-1){
    		int nTemp = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nBlock[nZeroPos[0]][nZeroPos[1]+1];
    		nBlock[nZeroPos[0]][nZeroPos[1]+1] = nTemp;
    		neighborBoards.add(new Board(nBlock));
    		nBlock[nZeroPos[0]][nZeroPos[1]+1] = nBlock[nZeroPos[0]][nZeroPos[1]];
    		nBlock[nZeroPos[0]][nZeroPos[1]] = nTemp;
    	}
    	return neighborBoards;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	StringBuilder result = new StringBuilder();
    	for(int i=0; i<nDimension; i++){
    		for(int j=0; j<nDimension; j++){
    			result.append(nBlock[i][j]);
    			result.append(' ');
    			if(j==nDimension-1) result.append('\n');
    		}
    	}
    	return result.substring(0);
    }

	private int nDimension;
    private int nHamming;
    private int nManhattan;
    private int[][] nBlock;
    private int[] nZeroPos;
    
    public static void main(String[] args) // unit tests (not graded)
    {
    	int[][] nTemp = new int[3][3];
    	for(int i = 0; i<3; i++){
    		for(int j=0; j<3; j++){
    			nTemp[i][j] = i*3+j;
    		}
    	}
    	Board tempB = new Board(nTemp);
    	StdOut.println(tempB.dimension());
    	StdOut.println(tempB.hamming());
    	StdOut.println(tempB.manhattan());
    	StdOut.println(tempB.toString());
    	for(Board bTemp : tempB.neighbors()){
    		StdOut.println(bTemp.manhattan());
    		StdOut.println(bTemp.toString());
    	}
    }
    
    
}
