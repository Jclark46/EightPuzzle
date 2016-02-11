package comparators;
import java.util.Comparator;

import search.Node;

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