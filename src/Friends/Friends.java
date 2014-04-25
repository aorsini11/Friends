//Alessandro Orsini and Niral Shah

package Friends;

import java.io.*;
import java.util.*;

public class Friends {
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		System.out.print("Enter friendship file name => ");
		String infile = keyboard.readLine();
		BufferedReader br = new BufferedReader(new FileReader(infile));
		int count = Integer.parseInt(br.readLine())+1;
		
		String[] people = new String[count-1];	

		for(int i=0; i<count-1; i++){			//put the people lines in an array
			people[i]=br.readLine();
		}
		
		ArrayList<String> friendsList = new ArrayList<String>();
		friendsList.add(br.readLine());
			
		while(br.readLine()!=null)
			friendsList.add(br.readLine());
			
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		People[] group = new People[count];
		group = build(people, friendsList, table);
		
		
		//////////////////////////////////
			System.out.println();
			System.out.println(1 + ". Students at school");
			System.out.println(2 + ". Shortest intro chain");
			System.out.println(3 + ". Cliques at school");
			System.out.println(4 + ". Connectors");
			System.out.println(5 + ". QUIT");
			System.out.print("\tEnter choice # => ");
			int choice = Integer.parseInt(br.readLine());
			
			while (choice != 5) {
				if (choice < 1 || choice > 5) {
					System.out.println("\tIncorrect choice " + choice);
				} else {
					switch (choice) {
					case 1: 
						System.out.print("Enter the name of the school => ");
						String school = sc.nextLine();
						subgraph(school, group, true); 
						break;
					case 2: 
						System.out.print("Enter the name of the starting person => ");
						String start = sc.nextLine();
						System.out.print("Enter the name of the target person => ");
						String target = sc.nextLine();
						shortest(start, target, group, table); 
						break;
					case 3: 
						System.out.print("Enter the name of the school => ");
						String sch = sc.nextLine();
						cliques(sch, group); 
						break;	
					case 4: 
						connectors(group, count);
						break;    
					default: break;
					}
				}
				System.out.println();
				System.out.println(1 + ". Students at school");
				System.out.println(2 + ". Shortest intro chain");
				System.out.println(3 + ". Cliques at school");
				System.out.println(4 + ". Connectors");
				System.out.println(5 + ". QUIT");
				System.out.print("\tEnter choice # => ");
				choice = Integer.parseInt(br.readLine());
				
				for(int i=0; i<group.length;i++){			//reset EVERYTHING
					group[i].visited=false;
					group[i].back = -1;
					group[i].dfs = -1;
					group[i].schoolIndex = -1;
				}
		
		}
	}
}
		
