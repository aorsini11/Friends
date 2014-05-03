package Friends;

public class Vertex {
		public String name;
		public String school;
		public Neighbor neighbor;
		public boolean visited;
		public boolean isConnector;
		public int index;
		public int search;
		public int prev;
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
			this.isConnector = false;
			this.index = -1;
			this.search = -1;
			this.prev = -1;
			/*this.front= front;
			this.visited = visited;
			this.group = zooIndex;
			this.schoolIndex = schoolIndex;
			this.back = back;
			this.dfs = dfs;*/
		
	}

}
