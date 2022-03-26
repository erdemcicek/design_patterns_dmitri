package flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class User {
	
	private String fullName;
	
	public User(String fullName) {
		this.fullName = fullName;
	}
	
	public static void main(String[] args) {
//		User u1 = new User("John Smith");
//		User u2 = new User("Jane Smith");
		
		BetterUser u1 = new BetterUser("John Smith");
		BetterUser u2 = new BetterUser("Jane Smith");
		
		System.out.println(BetterUser.strings);
	}

}

class BetterUser{
	
	static List<String> strings = new ArrayList<>();
	private int[] names;
	
	public BetterUser(String fullName) {
		
		Function<String, Integer> getOrAdd = s -> {
			
			int idx = strings.indexOf(s);
			
			if(idx != -1) return idx;
			
			strings.add(s);
			return strings.size()-1;
		};
		
		names = Arrays.stream(fullName.split(" ")).mapToInt(s -> getOrAdd.apply(s)).toArray();
	}
}










