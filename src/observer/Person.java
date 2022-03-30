package observer;

import java.util.ArrayList;
import java.util.List;

public class Person extends Observable<Person> {
	
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		
		if(this.age == age) return;
		this.age = age;
		propertyChanged(this, "age", age);
	}

}

class PropertyChangedEventArgs<T>{
	
	public T source;
	public String propertyName;
	public Object newValue;
	
	public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}
	
}

interface Observer<T>{
	void handle(PropertyChangedEventArgs<T> args);
}

class Observable<T>{
	
	private List<Observer<T>> observers = new ArrayList<>();
	
	public void subscribe(Observer<T> observer) {
		observers.add(observer);
	}
	
	protected void propertyChanged(T source, String propertyName, Object newValue) {
		
		for(Observer<T> o: observers) {
			o.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
		}
	}
}







