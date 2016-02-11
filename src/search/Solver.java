package search;
import java.util.Scanner;

public class Solver {
	
	ISearch searchStrategy;
	
	// Board configurations and initial start and goal
	static int[] easyBoard = {1,3,4,8,6,2,7,0,5};
	static int[] mediumBoard = {2,8,1,0,4,3,7,6,5};
	static int[] hardBoard = {5,6,7,4,0,8,3,2,1};
	static int[] goalBoard = {1,2,3,8,0,4,7,6,5};
	static Node goal = new Node(goalBoard);
	static Node start = new Node(goalBoard);
	
	
	public Solver(){
		setStrategy(new BreadthFirst());
	}
	
	
	public void setStrategy(ISearch strategy){
		searchStrategy = strategy;
	}
	
	public void searchPuzzle(){
		searchStrategy.search(start, goal);
	}
	
	// Difficulty Menu
	 public int difficultyMenu() {

	        int selection;
	        Scanner input = new Scanner(System.in);
	        System.out.println("Please select a difficulty");
	        System.out.println("1. Easy");
	        System.out.println("2. Medium");
	        System.out.println("3. Hard");
	        System.out.println("4. Quit");

	        selection = input.nextInt();
	        
	        return selection;
	    }
	 
	 
	 // Search Pattern Menu
	 public int searchMenu() {

	        int selection;
	        Scanner input = new Scanner(System.in);
	        System.out.println("\nPlease select a search Method");
	        System.out.println("1. Breadth First Search");
	        System.out.println("2. Depth First Search");
	        System.out.println("3. Iterative Deepening");
	        System.out.println("4. Uniform Cost");
	        System.out.println("5. Best First Search");
	        System.out.println("6. A*");
	        System.out.println("7. A* 2");
	        System.out.println("8. Quit");
	        selection = input.nextInt();
	     
	        return selection;    
	    }
	
	
	public static void main(String[] args){
		
		Solver solver =  new Solver();
		 
		// Pick a difficulty
		while(true){
		 int difficulty = solver.difficultyMenu();
		 if(difficulty == 1){
			 start = new Node(easyBoard);
			 break;
		 }
		 else if(difficulty == 2){
			 start = new Node(mediumBoard);
			 break;
		 }
		 else if(difficulty == 3){
			 start = new Node(hardBoard);
			 break;
		 }
		 if(difficulty == 4){
			 return;
		 }
		}
		
		// Pick a search pattern
		// Quick implementation to get some sort of UI going
		while(true){
			int search = solver.searchMenu();
				switch(search){
				
					// Breadth First
					case(1):
						long start_time = System.currentTimeMillis();
					 	solver.setStrategy(new BreadthFirst());
					    solver.searchPuzzle();
					 	long end_time = System.currentTimeMillis();
					 	long difference = end_time - start_time;
					 	System.out.println("THIS SEARCH TOOK " + difference + " MS");
					 	break;
					 	
					// Depth First
					case(2):
						 long start_time2 = System.currentTimeMillis();
						 solver.setStrategy(new DepthFirst());
						 solver.searchPuzzle();
						 long end_time2 = System.currentTimeMillis();
						 long difference2 = end_time2 - start_time2;
						 System.out.println("THIS SEARCH TOOK " + difference2 + " MS");
						 break;
						 
					// Iterative Deepening
					case(3):
						 long start_time3 = System.currentTimeMillis();
						 solver.setStrategy(new IterativeDeepening());
				 	     solver.searchPuzzle();
						 long end_time3 = System.currentTimeMillis();
						 long difference3 = end_time3 - start_time3;
						 System.out.println("THIS SEARCH TOOK " + difference3 + " MS");
						 break;
						 
					// Uniform Cost
					case(4):
						 long start_time4 = System.currentTimeMillis();
					     solver.setStrategy(new UniformCost());
					 	 solver.searchPuzzle();
						 long end_time4 = System.currentTimeMillis();
						 long difference4 = end_time4 - start_time4;
						 System.out.println("THIS SEARCH TOOK " + difference4 + " MS");
						 break;
					
					// Best First
					case(5):
						 long start_time5 = System.currentTimeMillis();
						 solver.setStrategy(new BestFirst());
						 solver.searchPuzzle();
						 long end_time5 = System.currentTimeMillis();
						 long difference5 = end_time5 - start_time5;
						 System.out.println("THIS SEARCH TOOK " + difference5 + " MS");
						 break;
					
					// Astar Misplaced
					case(6):
						 long start_time6 = System.currentTimeMillis();
						 solver.setStrategy(new AstarMisplaced());
						 solver.searchPuzzle();
						 long end_time6 = System.currentTimeMillis();
						 long difference6 = end_time6 - start_time6;
						 System.out.println("THIS SEARCH TOOK " + difference6 + " MS");
						 break;
						 
				    // Astar Manhattan
					case(7):
						 long start_time7 = System.currentTimeMillis();
					 	 solver.setStrategy(new AstarManhattan());
					     solver.searchPuzzle();
						 long end_time7 = System.currentTimeMillis();
						 long difference7 = end_time7 - start_time7;
						 System.out.println("THIS SEARCH TOOK " + difference7 + " MS");
					case(8):
						break;
						 
			 }
				
				break;
			
		}
		 
		 
		
		
	}
}
