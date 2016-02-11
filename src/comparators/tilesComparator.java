package comparators;
import java.util.Comparator;
import search.Node;

// Comparator that is used to sort the queue by order of misplaced tiles
public class tilesComparator implements Comparator<Node> {

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