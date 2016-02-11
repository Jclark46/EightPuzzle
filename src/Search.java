import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Search {
	
	
	
	
	// Prints the board, moves, cost, and depth
	public static void displayPath(Node n, Node start){
		
		List<Node> nodes = new LinkedList<Node>();
		while(n.parent != null){
			nodes.add(n);
			n = n.parent;
		}
		
		while(!nodes.isEmpty()){
			Node m = nodes.remove(nodes.size() - 1);
			System.out.println("\nMOVE: " + m.path());
			System.out.println("COST: " + m.cost); 
			System.out.println("DEPTH: " + m.depth);
			System.out.println("\n" + m);
		}
	}
	
	
	
	
	
	/*
	 * Breadth First Search
	 */
	public static Node bfs(Node start, Node goal){
		
		int maxQueue = 0;
		Queue<Node> queue = new LinkedList<Node>();
		Set<Node> visitedNodes = new HashSet<Node>();
		queue.add(start);
		visitedNodes.add(start);
		while(!queue.isEmpty()){		
			Node currentState = queue.poll();
			
			currentState.children = currentState.getSuccessors();
			
			if(Arrays.equals(currentState.board, goal.board)){
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH: "  + currentState.depth);
				System.out.println("SPACE: " + maxQueue);
				return currentState;
			}	
			
			for(Node n : currentState.children){
				// Check visited nodes
				if(!visitedNodes.contains(n)){
					visitedNodes.add(n);
					queue.add(n);
				}
			}
			if(queue.size() > maxQueue){
				maxQueue = queue.size();
			}
				
		}
		return null;
	}

	
	
	
	
	/*
	 * Depth First Search 
	 */
	public static Node dfs(Node start, Node goal){
		int maxStack = 0;
		
		//Here we use a stack instead of a queue for depth first search
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visitedNodes = new HashSet<Node>();
		
		stack.add(start);
		visitedNodes.add(start);
		
		while(!stack.isEmpty()){
			Node currentState = stack.pop();
			
			if(Arrays.equals(currentState.board, goal.board)){	
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE: " + maxStack);
				return currentState;
			}
			
			currentState.children = currentState.getSuccessors();
			
			for(Node n : currentState.children){
				// Check visited nodes
				if(!visitedNodes.contains(n)){
					visitedNodes.add(n);
					stack.push(n);
				}
			}
			if(stack.size() > maxStack){
				maxStack = stack.size();
			}
		}
		return null;
	}
	
	
	
	
	
	/*
	 * Depth Limited Search 
	 * Used for Iterative Deepening
	 */
	public static Node dls(Node start, Node goal, int maxDepth){
		
		int maxStack = 0;
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visitedNodes = new HashSet<Node>();
		stack.add(start);
		visitedNodes.add(start);
		
		while(!stack.isEmpty()){
			Node currentState = stack.pop();
			
			if(Arrays.equals(currentState.board, goal.board)){
				displayPath(currentState, start);	
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE: " + maxStack);
				return currentState;
			}
			
			// check the depth of the node
			if(currentState.depth == maxDepth){
				continue;
			}
			
			currentState.children = currentState.getSuccessors();
			for(Node n : currentState.children){
				if(!visitedNodes.contains(n)){
					visitedNodes.add(n);
					stack.push(n);
				}
			}
			if(stack.size() > maxStack){
				maxStack = stack.size();
			}
		}
		return start;
	}
	
	
	
	
	/*
	 * Iterative Deepening 
	 */	
	public static Node iterativeDeepening(Node start, Node goal){
		
		int maxDepth = 0;
		Node n = start;
		
		// While we don't have a solution run depth limited search
		while(!Arrays.equals(n.board, goal.board)){
			n = dls(start, goal, maxDepth);
			maxDepth++;
		}
		return null;

	}
	
	
	
	
	
	/*
	 * Uniform Cost
	 */
	public static Node uniformCost(Node start, Node goal){
		
		int maxQueue = 0;
		LinkedList<Node> queue = new LinkedList<Node>();
		Set<Node> visitedNodes = new HashSet<Node>();
		queue.add(start);
		visitedNodes.add(start);
		
		while(!queue.isEmpty()){
			
			Node currentState = queue.pop();			
			if(Arrays.equals(currentState.board, goal.board)){	
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE: " + maxQueue);
				return currentState;
			}
			
			currentState.children = currentState.getSuccessors();
			for(Node n : currentState.children){
				if(!visitedNodes.contains(n)){
					queue.push(n);
					visitedNodes.add(n);	
				}
			}
			Collections.sort(queue, new costComparator());
			if(queue.size() > maxQueue){
				maxQueue = queue.size();
			}
		}
		return null;
	}
	
	
	
	
	
	/*
	 * Greedy Best First
	 */
	public static Node bestFirst(Node start, Node goal){
		
		int maxQueue = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new tilesComparator());
		Set<Node> visitedNodes = new HashSet<Node>();
		queue.add(start);
		visitedNodes.add(start);
		
		while(!queue.isEmpty()){
			Node currentState = queue.poll();
			
			if(Arrays.equals(currentState.board, goal.board)){	
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE:"  + maxQueue);
				return currentState;
			}
			currentState.children = currentState.getSuccessors();
			for(Node n : currentState.children){
				if(!visitedNodes.contains(n)){
					queue.add(n);
					visitedNodes.add(n);	
				}
			}
			if(queue.size() > maxQueue){
				maxQueue = queue.size();
			}
		
		}
		return null;
	
	}
	
	
	
	
	
	/*
	 * a* - Misplaced Tiles
	 */
	public static Node aStar(Node start, Node goal){
		
		int maxQueue = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new f1Comparator());
		Set<Node> visitedNodes = new HashSet<Node>();
		queue.add(start);
		visitedNodes.add(start);
		
		while(!queue.isEmpty()){
			Node currentState = queue.poll();
			if(Arrays.equals(currentState.board, goal.board)){	
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE:"  + maxQueue);
				return currentState;
			}
			currentState.children = currentState.getSuccessors();
			for(Node n : currentState.children){
				if(!visitedNodes.contains(n)){
					queue.add(n);
					visitedNodes.add(n);	
				}
			}
			if(queue.size() > maxQueue){
				maxQueue = queue.size();
			}
			
		}
		return null;
	}
	
	
	
	
	/*
	 * a* - Manhattan Distance
	 */
	public static Node aStar2(Node start, Node goal){
		
		int maxQueue = 0;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new f2Comparator());
		Set<Node> visitedNodes = new HashSet<Node>();
		queue.add(start);
		visitedNodes.add(start);
		
		while(!queue.isEmpty()){
			Node currentState = queue.poll();
			
			if(Arrays.equals(currentState.board, goal.board)){	
				displayPath(currentState, start);
				System.out.println("\n**********************************");
				System.out.println("TOTAL COST: " + currentState.g_N);
				System.out.println("DEPTH:"  + currentState.depth);
				System.out.println("SPACE:"  + maxQueue);
				return currentState;
			}
			
			currentState.children = currentState.getSuccessors();
			for(Node n : currentState.children){
				if(!visitedNodes.contains(n)){
					queue.add(n);
					visitedNodes.add(n);	
				}
			}
			
			if(queue.size() > maxQueue){
				maxQueue = queue.size();
			}
		}
		return null;
	}
	
	
	
}
