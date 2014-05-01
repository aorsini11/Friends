//Alessandro Orsini and Niral Shah
//test
package Friends;

import java.io.*;
import java.util.*;

public class Friends {
	
	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static Vertex[] group;
	static HashMap<String, Integer> table = new HashMap<String, Integer>();
	
	public static void main(String[] args) throws IOException {
		System.out.print("Enter friendship file name => ");
		String infile = keyboard.readLine();
		BufferedReader br = new BufferedReader(new FileReader(infile));
		int count = Integer.parseInt(br.readLine());

		Vertex[] group = new Vertex[count];
		group = build(infile);
		
		
		//////////////////////////////////
			System.out.println();
			System.out.println(1 + ". Students at school");
			System.out.println(2 + ". Shortest intro chain");
			System.out.println(3 + ". Cliques at school");
			System.out.println(4 + ". Connectors");
			System.out.println(5 + ". QUIT");
			System.out.print("\tEnter choice # => ");
			int choice = Integer.parseInt(keyboard.readLine());
			
			while (choice != 5) {
				if (choice < 1 || choice > 5) {
					System.out.println("\tIncorrect choice " + choice);
				} else {
					switch (choice) {
					case 1: 
						System.out.print("Enter the name of the school => ");
						String school = keyboard.readLine();
						subgraph(school); 
						break;
					case 2: 
						System.out.print("Enter the name of the starting person => ");
						String start = keyboard.readLine();
						System.out.print("Enter the name of the target person => ");
						String target = keyboard.readLine();
						//shortest(start, target, group, table); 
						break;
					case 3: 
						System.out.print("Enter the name of the school => ");
						String sch = keyboard.readLine();
					//	cliques(sch, group); 
						break;	
					case 4: 
					//	connectors(group, count);
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
				choice = Integer.parseInt(keyboard.readLine());
				
				/*for(int i=0; i<group.length;i++){			//reset EVERYTHING
					group[i].visited=false;
					group[i].back = -1;
					group[i].dfs = -1;
					group[i].schoolIndex = -1;*/
				//}
		
		}
	}
	/* Input:Filename that contains all information to build a graph
	 * 
	 * Result: Creates array of vertices from the file provided. Each of these
	 * vertices have a neighbor field that acts as a linked list pointing to friends
	 * of the person at that vertex. A hashtable is created to get the index of 
	 * a person given his name.
	 */
	public static Vertex[] build(String infile) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(infile));
		int count = Integer.parseInt(br.readLine());
		
		//Make an array of all people in the graph
		String[] people = new String[count];	
		for(int i=0; i<count; i++){			
			people[i]=br.readLine();
		}
		//Make an array of all friend relationships
		ArrayList<String> friendsList = new ArrayList<String>();
		friendsList.add(br.readLine());
			
		while(br.readLine()!=null)
			friendsList.add(br.readLine());
			
		//Make people and add them
		Vertex[] returnArray = new Vertex[people.length];
		
		for(int i=0;i<people.length;i++)
		{
			String original = people[i];
			String name = original.substring(0, original.indexOf('|'));
			String school = null;
			if (original.charAt(original.indexOf('|')+1)=='y')
				school = original.substring(original.lastIndexOf('|')+1,original.length());
			Vertex person = new Vertex(name,school,null);
			returnArray[i] = person;
			table.put(name.toLowerCase().trim(), i);
		}
		
		for(int i=0;i<friendsList.size()-1;i++)
		{
			String original = friendsList.get(i);
			String friend1name = original.substring(0,original.indexOf('|'));
			String friend2name = original.substring(original.indexOf('|')+1,original.length());
			
			int index1 = table.get(friend1name.toLowerCase().trim());
			int index2 = table.get(friend2name.toLowerCase().trim());
			
			Vertex friend1 = returnArray[index1];
			Vertex friend2 = returnArray[index2];
			
			if (friend1.neighbor == null)
				friend1.neighbor = new Neighbor(index2,null);
			else
			{
				// Neighbor prev = friend1.neighbor;
				friend1.neighbor = new Neighbor(index2,friend1.neighbor);
			}
			
			if(friend2.neighbor==null)
				friend2.neighbor = new Neighbor(index1,null);
			else
				friend2.neighbor = new Neighbor(index1,friend2.neighbor);		
		}
		
		
		return returnArray;
	}
	
	public static void subgraph(String schoolName)
	{
		ArrayList<Vertex> subClass = new ArrayList<Vertex>();
		int count = 0;
		for (int i=0; i<group.length;i++)
		{
			
		}
		
	}
}
		
