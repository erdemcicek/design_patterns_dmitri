package observer;

public class EventDemo {
	
	public static void main(String[] args) {
		
		PersonForEvent person = new PersonForEvent();
		
		Event<PropertyChangedEventArgsForEvent>.Subscription sub = 
				person.propertyChanged.addHandler(x -> {
			System.out.println("Person's " + x.properyName + " has changed");
		});
		
		person.setAge(17);
		person.setAge(18);
		//sub.close();
		person.setAge(19);
		
	}
	
	
	

}
