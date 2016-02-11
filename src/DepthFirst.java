import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirst implements ISearch{

	@Override
	public Node search(Node start, Node goal){
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

	
	
	@Override
	public void displayPath(Node n, Node start){
		
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

}
