package Friends;

public class People {
		public String name;
		public String school;
		public Friendex front;
		public boolean visited;
		public int groupIndex;
		public int schoolIndex;
		public int back;
		public int dfs;

		public People(String name, String school, Friendex front, boolean visited, 
				int zooIndex, int schoolIndex, int back, int dfs){	//ArrayList<Person> friendList
			this.name = name;
			this.school = school;
			this.front= front;
			this.visited = visited;
			this.group = zooIndex;
			this.schoolIndex = schoolIndex;
			this.back = back;
			this.dfs = dfs;
		
	}

}
