import java.util.Scanner;

public class Solver {
	
	
	// Difficulty Menu
	 public static int difficultyMenu() {

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
	 public static int searchMenu() {

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
		
		
		// Board configurations and initial start and goal
		int[] easyBoard = {1,3,4,8,6,2,7,0,5};
		int[] mediumBoard = {2,8,1,0,4,3,7,6,5};
		int[] hardBoard = {5,6,7,4,0,8,3,2,1};
		int[] goalBoard = {1,2,3,8,0,4,7,6,5};
		Node goal = new Node(goalBoard);
		Node start = new Node(goalBoard);
		 
		// Pick a difficulty
		while(true){
		 int difficulty = Solver.difficultyMenu();
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
			int search = Solver.searchMenu();
				switch(search){
					case(1):
						long start_time = System.currentTimeMillis();
					 	Search.bfs(start,goal);
					 	long end_time = System.currentTimeMillis();
					 	long difference = end_time - start_time;
					 	System.out.println("THIS SEARCH TOOK " + difference + " MS");
					 	break;
					case(2):
						 long start_time2 = System.currentTimeMillis();
						 Search.dfs(start,goal);
						 long end_time2 = System.currentTimeMillis();
						 long difference2 = end_time2 - start_time2;
						 System.out.println("THIS SEARCH TOOK " + difference2 + " MS");
						 break;
					case(3):
						 long start_time3 = System.currentTimeMillis();
						 Search.iterativeDeepening(start,goal);
						 long end_time3 = System.currentTimeMillis();
						 long difference3 = end_time3 - start_time3;
						 System.out.println("THIS SEARCH TOOK " + difference3 + " MS");
						 break;
					case(4):
						 long start_time4 = System.currentTimeMillis();
						 Search.uniformCost(start,goal);
						 long end_time4 = System.currentTimeMillis();
						 long difference4 = end_time4 - start_time4;
						 System.out.println("THIS SEARCH TOOK " + difference4 + " MS");
						 break;
					case(5):
						 long start_time5 = System.currentTimeMillis();
						 Search.bestFirst(start,goal);
						 long end_time5 = System.currentTimeMillis();
						 long difference5 = end_time5 - start_time5;
						 System.out.println("THIS SEARCH TOOK " + difference5 + " MS");
						 break;
					case(6):
						 long start_time6 = System.currentTimeMillis();
						 Search.aStar(start,goal);
						 long end_time6 = System.currentTimeMillis();
						 long difference6 = end_time6 - start_time6;
						 System.out.println("THIS SEARCH TOOK " + difference6 + " MS");
						 break;
					case(7):
						 long start_time7 = System.currentTimeMillis();
						 Search.aStar2(start, goal);
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
