package Friends;

public class Vertex {
		public String name;
		public String school;
		public Neighbor neighbor;
		boolean visited;
		/*public boolean visited;
		public int groupIndex;
		public int schoolIndex;
		public int back;
		public int dfs;*/

		public Vertex(String name, String school,Neighbor neighbor){	//ArrayList<Person> friendList
			this.name = name;
			this.school = school;
			this.neighbor = neighbor;
			this.visited = false;
			/*this.front= front;
			this.visited = visited;
			this.group = zooIndex;
			this.schoolIndex = schoolIndex;
			this.back = back;
			this.dfs = dfs;*/
		
	}

}
