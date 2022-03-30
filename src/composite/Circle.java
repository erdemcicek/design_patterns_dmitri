package composite;

public class Circle extends GraphicObject {
	
	public Circle(String color) {
		name = "Circle";
		this.color = color;
	}

}

class Square extends GraphicObject {
	
	public Square(String color) {
		name = "Square";
		this.color = color;
	}
}
