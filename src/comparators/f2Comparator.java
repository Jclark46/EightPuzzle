package comparators;
import java.util.Comparator;

import search.Node;

//Comparator that is used to sort the queue by order of f(n) for second a star
public class f2Comparator implements Comparator<Node> {

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