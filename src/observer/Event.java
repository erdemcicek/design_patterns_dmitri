package observer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Event<TArgs> {

	private int count = 0;
	private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();
	
	public Subscription addHandler(Consumer<TArgs> handler) {
		int i = count;
		handlers.put(count++, handler);
		return new Subscription(this, i);
	}
	
	public void fire(TArgs args) {
		for(Consumer<TArgs> handler: handlers.values()) {
			handler.accept(args);
		}
	}
	
	public class Subscription implements AutoCloseable{
		
		private Event<TArgs> event;
		private int id;
		
		public Subscription(Event<TArgs> event, int id) {
			this.event = event;
			this.id = id;
		}

		@Override
		public void close() /*throws Exception*/ {
			event.handlers.remove(id);
		}
	}
}

class PropertyChangedEventArgsForEvent{ // PropertyChangedEventArgs class
	
	public Object source;
	public String properyName;
	
	public PropertyChangedEventArgsForEvent(Object source, String properyName) {
		this.source = source;
		this.properyName = properyName;
	}
	
}

class PersonForEvent{ // Person class
	public Event<PropertyChangedEventArgsForEvent> propertyChanged = new Event<PropertyChangedEventArgsForEvent>();
	
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		
		if(this.age == age) return;
		
		boolean oldCanVote = getCanVote();
		
		this.age = age;
		propertyChanged.fire(new PropertyChangedEventArgsForEvent(this, "age"));
		
		if(oldCanVote != getCanVote()) {
			propertyChanged.fire(new PropertyChangedEventArgsForEvent(this, "canVote"));
		}
	}
	
	public boolean getCanVote() {
		return age >= 18;
	}
	
}
