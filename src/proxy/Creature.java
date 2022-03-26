package proxy;

public class Creature {
	
	//Property<Integer> x = new Property(5);
	
	private Property<Integer> agility = new Property<>(10);
	
	public void setAgility(int value) {
		agility.setValue(value);
	}
	
	public int getAgility() {
		return agility.getValue();
	}
	
	public static void main(String[] args) {
		Creature creature = new Creature();
		creature.setAgility(12);
	}
}

class Property<T>{
	
	private T value;
	
	public Property(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		// logging
		this.value = value;
	}

	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((value == null) ? 0 : value.hashCode());
//		return result;
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property<?> other = (Property<?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
	
	
	
}
