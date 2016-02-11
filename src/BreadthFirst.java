import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BreadthFirst implements ISearch{

	@Override
	public Node search(Node start, Node goal){
		
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
