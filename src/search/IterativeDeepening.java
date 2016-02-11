package search;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class IterativeDeepening implements ISearch{

	
	
	public Node search(Node start, Node goal){
		
		int maxDepth = 0;
		Node n = start;
		
		// While we don't have a solution run depth limited search
		while(!Arrays.equals(n.board, goal.board)){
			n = dls(start, goal, maxDepth);
			maxDepth++;
		}
		return null;
	}
	
	public Node dls(Node start, Node goal, int maxDepth){
		
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

	@Override
	public void displayPath(Node n, Node start) {
		// TODO Auto-generated method stub
		
	}

}
