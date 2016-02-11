package comparators;
import java.util.Comparator;
import search.Node;

//Comparator that is used to sort the queue by order of total cost
public class costComparator implements Comparator<Node> {

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
