package comparators;
import java.util.Comparator;

import search.Node;

//Comparator that is used to sort the queue by order of f(n) for first a star
public class f1Comparator implements Comparator<Node> {

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