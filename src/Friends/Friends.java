//Alessandro Orsini and Niral Shah

package Friends;

import java.io.*;
import java.util.*;

import sun.security.provider.certpath.Vertex;

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

		group = new Vertex[count];
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
			name = name.toLowerCase().trim();
			if(school!=null)
				school = school.toLowerCase().trim();
			Vertex person = new Vertex(name,school,null);
			returnArray[i] = person;
			table.put(name.toLowerCase().trim(), i);
		}
		
		for(int i=0;i<friendsList.size()-1;i++)
		{
			String original = friendsList.get(i);
			String friend1name = original.substring(0,original.indexOf('|'));
			String friend2name = original.substring(original.indexOf('|')+1,original.length());
			friend1name = friend1name.trim();
			friend2name = friend2name.trim();
			
			int index1 = table.get(friend1name.toLowerCase());
			int index2 = table.get(friend2name.toLowerCase());
			
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
	
	public static ArrayList<Vertex> subgraph(String schoolName)
	{
		schoolName = schoolName.toLowerCase();
		HashMap<String, Integer> schoolTable = new HashMap<String, Integer>();
		ArrayList<Vertex> subGroup = new ArrayList<Vertex>();
		int count = 0;
		//Find all the people that go to a certain school
		for (int i=0; i<group.length;i++)
		{
			if(group[i].school!=null && group[i].school.equals(schoolName))
			{
				subGroup.add(group[i]);
				count++;
				schoolTable.put(group[i].name, count);
				
			}
		}
		//Find all edges
		for(int i=0;i<subGroup.size();i++)
		{
			Vertex current = subGroup.get(i);
			Neighbor first = current.neighbor;
			Neighbor second = null;
			while(first!=null)
			{
				if(group[first.vertexNum].school!=null && group[first.vertexNum].school.equals(schoolName))
				{
					first.vertexNum = schoolTable.get(group[first.vertexNum].name);
					second = first;
				}
				else
				{
					if(second==null)
						current.neighbor = first.next;
					else
						second.next = first.next;
				}
				first = first.next;
			}
		}
		printGroup(subGroup);
		return subGroup;
	}
	
	public static void printGroup(ArrayList<Vertex> Group)
	{
		// Print #
		System.out.println(Group.size());
		// Print each person
		for(int i = 0; i < Group.size();i++)
		{
			if(Group.get(i).school!=null)
				System.out.println(Group.get(i).name + "|y|"+ Group.get(i).school);
			else
				System.out.println(Group.get(i).name + "|n");
		}
		// Print each relationship 
		for(int i = 0; i < Group.size();i++)
		{
			Vertex current = Group.get(i);
			Neighbor ptr = current.neighbor;
			while(ptr!=null)
			{
				if(current.visited==false || Group.get(ptr.vertexNum).visited==false)
				{
					System.out.println(current.name + "|" + Group.get(ptr.vertexNum).name);
					current.visited = true;
					Group.get(ptr.vertexNum).visited = true;
				}
				ptr = ptr.next;
			}
		}
		
	} // this is going to return a subgraph ArrayList<Vertex>
	
	public static void cliques(String school){ 
		ArrayList<Vertex> subList= subgraph(school);
		
		boolean[] visited= new boolean[subList.size()];
		for (int v=0; v < visited.length; v++) {
			visited[v] = false;
		}
		 for(int v=0; v<visited.length; v++){
			 if(!visited[v]){ // indicates a new clique
				 System.out.println("Clique "+v+":")
				 dfs(v,visited, subList, school);  
				  // once a clique has been printed move to next line
			 }
		 }
	}
	
	 private void dfs(int v, boolean[] visited, ArrayList<Vertex> subList, String school) {
	        visited[v] = true;
	        for (Neighbor nbr=subLists.get(v).adjList; nbr != null; nbr=nbr.next) {
	            if (!visited[nbr.vertexNum]) {
	            	System.out.println(subList.get(v).name+"|"+"y"+"|"+school);
	                dfs(nbr.vertexNum, visited);
	            }
	        }
	    }
	
}
		
