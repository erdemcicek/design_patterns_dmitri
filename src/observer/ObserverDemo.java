package observer;

public class ObserverDemo implements Observer<Person>{

	@Override
	public void handle(PropertyChangedEventArgs<Person> args) {
		System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
	}
	
	public ObserverDemo() {
		Person person = new Person();
		person.subscribe(this);
		for(int i = 20 ; i < 24; i++) {
			person.setAge(i);
		}
	}
	
	public static void main(String[] args) {
		
		new ObserverDemo();
	}
	
}
