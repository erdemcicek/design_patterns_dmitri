package proxy;

public class Car implements Drivable {
	
	protected Driver driver;
	
	public Car(Driver driver) {
		this.driver = driver;
	}

	@Override
	public void drive() {
		System.out.println("Car being driven");
	}
	
	public static void main(String[] args) {
		Car car1 = new CarProxy(new Driver(12));
		car1.drive();
	}

}

interface Drivable{
	void drive();
}

class Driver{
	
	public int age;
	
	public Driver(int age) {
		this.age = age;
	}
	
}

class CarProxy extends Car{

	public CarProxy(Driver driver) {
		super(driver);
	}
	
	@Override
	public void drive() {
		if(driver.age >= 16) {
			super.drive();
		} else {
			System.err.println("Driver too young");
		}
	}
	
}
