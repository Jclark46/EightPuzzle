import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Node{
	
	
    int[] board = new int[9];
	static int[] goalBoard = {1,2,3,8,0,4,7,6,5};
	int depth;
	int cost;
	int g_N;
	Node parent;
	List<Node> children = new LinkedList<Node>();
	
	
		//Initial constructor
		public Node(int [] board){
			this.parent = null;
			this.board = board;
			this.depth = 0;
			this.cost = 0;
			this.g_N = 0;
		}
		
		//Constructor for children
		public Node(Node prev){
			this.parent = prev;
			this.depth = prev.depth + 1;
		}
	
	
	//Slide the 0 tile up
	public Node UP(){
		Node newState = new Node(this);
		for(int i = 0; i < 9; i++){
			newState.board[i] = board[i];
		}
		for(int i = 0; i < 9; i++){
			if(board[i] == 0){
				// Swap the positions of the tiles.
				// UP
				newState.cost = newState.board[i-3];
				newState.g_N = g_N + newState.board[i-3];
				newState.board[i] = newState.board[i - 3];
				newState.board[i - 3] = 0;
				return newState;
				
				}
			}
		return newState;
	}
	
	
	//Slide the 0 tile down
	public Node DOWN(){
		Node newState = new Node(this);
		for(int i = 0; i < 9; i++){
			newState.board[i] = board[i];
		}
		for(int i = 0; i < 9; i++){
			if(newState.board[i] == 0){
				// Swap the positions of the tiles.
				// DOWN
				newState.cost = newState.board[i+3];
				newState.g_N = g_N + newState.board[i+3];
				newState.board[i] = newState.board[i + 3];
				newState.board[i + 3] = 0;
				return newState;
				
				}
			}
		return newState;

	}
	
	//Slide the 0 tile right
	public Node RIGHT(){
		Node newState = new Node(this);
		for(int i = 0; i < 9; i++){
			newState.board[i] = board[i];
		}
		for(int i = 1; i < 9; i++){
			// Swap the positions of the tiles.
			// RIGHT
			if(newState.board[i] == 0){
				newState.cost = newState.board[i+1];
				newState.g_N = g_N + newState.board[i+1];
				newState.board[i] = newState.board[i+1];	
				newState.board[i+1] = 0;
				return newState;
			}
		}
		return newState;
	}
	
	//Slide the 0 tile left
	public Node LEFT(){
		Node newState = new Node(this);
		for(int i = 0; i < 9; i++){
			newState.board[i] = board[i];
		}
		for(int i = 1; i < newState.board.length; i++){
			// Swap the positions of the tiles.
			// LEFT
			if(newState.board[i] == 0){
				newState.cost = newState.board[i-1];
				newState.g_N = g_N + newState.board[i-1];
				newState.board[i] =newState.board[i-1];	
				newState.board[i-1] = 0;
				return newState;
			}
		}
		return newState;
	}
	
	
	// Check if up is a valid move in this board state
	public boolean canGoUp(){
		int loc = 0;
		while(this.board[loc] != 0){
			loc++;
		}
		if(loc > 2){
			return true;
		}
		return false;
	}
	
	// Check if down is a valid move in this board state
	public boolean canGoDown(){
		int loc = 0;
		while(this.board[loc] != 0){
			loc++;
		}
		if(loc < 6){
			return true;
		}
		return false;
	}
	
	// Check if right is a valid move in this board state
	public boolean canGoRight(){
		int loc = 0;
		while(this.board[loc] != 0){
			loc++;
		}
		if(loc % 3 != 2){
			return true;
		}
		return false;
	}
	
	// Check if left is a valid move in this board state
	public boolean canGoLeft(){
		int loc = 0;
		while(this.board[loc] != 0){
			loc++;
		}
		if(loc % 3 != 0){
			return true;
		}
		return false;
	}
	

	// create list of successors
	public List<Node> getSuccessors(){
		List<Node> moves = new ArrayList<Node>();
		if(this.canGoUp()){
			moves.add(this.UP());
		}

		if(this.canGoDown()){
			moves.add(this.DOWN());
		}

		if(this.canGoRight()){
			moves.add(this.RIGHT());
		}
		
		if(this.canGoLeft()){
			moves.add(this.LEFT());
		}
		
		return moves;
		
	}
	
	//Heuristic function - Misplaced Tiles
	public static int getMisplaced(int[] board){
		int tiles = 0;
		for(int i = 0; i < board.length; i++){
			if(board[i] != goalBoard[i]){
				tiles++;
			}
		}
		return tiles;
	}
	
	//Heuristic function - Manhattan Distance
	public static int getManhattan(int[] board){
	int manhattan = 0;
	int ind = 0;
	for(int i = 0; i < board.length; i++){
		int value = board[i];
		ind = 0;
		for(int j = 0; j < goalBoard.length; j++){
			int target = goalBoard[j];
			
			if(value == target){
				ind = i - j;
				if(Math.abs(ind) == 3){
					manhattan += 1;
				}
				else if(Math.abs(ind) == 4){
					manhattan += 2;
				}
				else{
					manhattan += Math.abs(i - j);
				}
				
			}
		}
	}
	return manhattan;
}
	
	
	
	// This getter is f(n) for the first implentation of
	// a*. Takes the misplaced tiles and adds it to the total cost
	// f(n) = h(n) + g(n)
	// h(n) = misplaced Tiles
	// g(n) = total cost
	public static int getf_NforMisplaced(Node n){
		return getMisplaced(n.board) + n.g_N;
	}
	
	// This getter is f(n) for the first implentation of
	// a*. Takes the misplaced tiles and adds it to the total cost
	// f(n) = h(n) + g(n)
	// h(n) = manhattan distance
	// g(n) = total cost
	public static int getf_NforManhattan(Node n){
		return getManhattan(n.board) + n.g_N;
	}
	
	
	
	// Helper function that displays what moves are made
	public String path(){
		int childIndex = 0;
		int parentIndex = 0;
		for(int i = 0; i < 9; i++){
			if(board[i] == 0){
				childIndex = i;
			}
		}
		for(int j = 0; j < 9; j++){
			if(parent.board[j] == 0){
				parentIndex = j;
			}
		}	
		if(childIndex == parentIndex - 1){
			return "LEFT";
		}
		if(childIndex == parentIndex + 1){
			return "RIGHT";
		}
		if(childIndex == parentIndex - 3){
			return "UP";
		}
		if(childIndex == parentIndex + 3){
			return "DOWN";
		}
		
		return null;
	
	}
	
	
	
	
	
	// Override the equals function
	
	public boolean equals(Node n){
		if( board.equals(n.board)){
			return true;
		}
		else{
			return false;
		}
	}	
	
	//Override to check if current node is in set of visitedNodes
	
	@Override
	public int hashCode(){
		return Arrays.hashCode(this.board);
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Node){
			return(Arrays.equals(((Node) o).board, this.board));
		}
		return false;
	}
	
	
	// Display the boards
	public String toString(){
		String boardDisplay = Integer.toString(board[0]) + " " + 
							  Integer.toString(board[1]) + " " +
							  Integer.toString(board[2]) + " " + "\n" +
							  Integer.toString(board[3]) + " " +
							  Integer.toString(board[4]) + " " +
							  Integer.toString(board[5]) + " " + "\n" +
							  Integer.toString(board[6]) + " " +
							  Integer.toString(board[7]) + " " +
							  Integer.toString(board[8]) + " ";
							  
		
		return boardDisplay;
			
	}

	
	
}


//Comparator that is used to sort the queue by order of total cost
class costComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if( arg0.g_N < arg1.g_N){
			return -1;
		}
		else if(arg0.g_N >  arg1.g_N){
			return 1;
		}
		return 0;
	}
}


// Comparator that is used to sort the queue by order of manhattan distance
class manhattanComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if( Node.getManhattan(arg0.board) < Node.getManhattan(arg1.board)){
			return -1;
		}
		else if(Node.getManhattan(arg0.board) > Node.getManhattan(arg1.board)){
			return 1;
		}
		return 0;
	}
}

// Comparator that is used to sort the queue by order of misplaced tiles
class tilesComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if( Node.getMisplaced(arg0.board) < Node.getMisplaced(arg1.board)){
			return -1;
		}
		else if(Node.getMisplaced(arg0.board) > Node.getMisplaced(arg1.board)){
			return 1;
		}
		return 0;
	}
}

//Comparator that is used to sort the queue by order of f(n) for first a star
class f1Comparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if( Node.getf_NforMisplaced(arg0) < Node.getf_NforMisplaced(arg1)){
			return -1;
		}
		else if(Node.getf_NforMisplaced(arg0) > Node.getf_NforMisplaced(arg1)){
			return 1;
		}
		return 0;
	}
}
	
//Comparator that is used to sort the queue by order of f(n) for second a star
class f2Comparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		if( Node.getf_NforManhattan(arg0) < Node.getf_NforManhattan(arg1)){
			return -1;
		}
		else if(Node.getf_NforManhattan(arg0) > Node.getf_NforManhattan(arg1)){
			return 1;
		}
		return 0;
	}


}
