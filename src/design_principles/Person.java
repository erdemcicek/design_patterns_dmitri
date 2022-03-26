package design_principles;

import java.util.ArrayList;
import java.util.List;

public class Person {

	public String name;
	
	public Person(String name) {
		this.name = name;
	}
}

enum Relationship { PARENT, CHILD, SIBLING}

interface RelationshipBrowser{
	List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser{ // low-level
	private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();
	
	public List<Triplet<Person, Relationship, Person>> getRelations(){
		return relations;
	}
	
	public void addParentAndChild(Person parent, Person child) {
		relations.add(new Triplet<>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<>(child, Relationship.CHILD, parent));
	}

	@Override
	public List<Person> findAllChildrenOf(String name) {
//		return relations.stream()
//				.filter(t -> Objects.equals(t.getValue0().name, name) && t.getValue1() == Relationship.PARENT)
//				.map(t -> getValue2(t).collect(Collectors.toList()));
		return null;
	}
}

class Research{ // high-level
	
	public Research(Relationships relationships) {
		List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
		relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
		.forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
	}
	
	public Research(RelationshipBrowser browser) {
		List<Person> children = browser.findAllChildrenOf("John");
		for(Person child : children) {
			System.out.println("John has a child called " + child.name);
		}
	}
}

class Triplet<A,B,C>{
	
	A a; B b; C c;
	
	public Triplet(A a, B b, C c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public A getValue0() {
		return a;
	}
	public B getValue1() {
		return b;
	}
	public C getValue2() {
		return c;
	}
}