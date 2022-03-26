package design_principles;

import java.util.List;
import java.util.stream.Stream;


public class Product {
	
	public String name;
	public Color color;
	public Size size;
	
	public Product(String name, Color color, Size size) {
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static void main(String[] args) {
		Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
		Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
		Product house = new Product("House", Color.BLUE, Size.LARGE);
		
		GoodFilter<Product> gf = new GoodFilter();
		gf.filter(List.of(apple, tree, house), new ColorSpecification(Color.GREEN).and(new SizeSpecification(Size.LARGE)))
		.forEach(t -> System.out.println(t));
	}

}

interface Specification<T>{
	boolean isSatisfied(T item);
	default Specification<T> and(Specification<? super T> spec){
		return t -> isSatisfied(t) && spec.isSatisfied(t);
	}
}

class GoodFilter<T>{
	
	public Stream<T> filter(List<T> items, Specification<T> spec){
		return items.stream().filter( t-> spec.isSatisfied(t) );
	}
}

class ColorSpecification implements Specification<Product>{

	Color color;
	public ColorSpecification(Color color) {
		this.color = color;
	}
	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}
}

class SizeSpecification implements Specification<Product>{

	Size size;
	public SizeSpecification(Size size) {
		this.size = size;
	}
	@Override
	public boolean isSatisfied(Product item) {
		return item.size == size;
	}
}


enum Color {RED, GREEN, BLUE}

enum Size {SMALL, MEDIUM, LARGE, HUGE}

//BAD EXAMPLE
class ProductFilter{
	
	public Stream<Product> filterByColor(List<Product> products, Color color){
		return products.stream().filter(p -> p.color == color);
	}
	
	public Stream<Product> filterBySize(List<Product> products, Size size){
		return products.stream().filter(p -> p.size == size);
	}
	
}