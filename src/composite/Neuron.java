package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Neuron implements SomeNeurons{

	public ArrayList<Neuron> in, out = new ArrayList<>();

	@Override
	public Iterator<Neuron> iterator() {
		return Collections.singleton(this).iterator();
	}
	
	@Override
	public void forEach(Consumer<? super Neuron> action) {
		action.accept(this);
	}
	
	@Override
	public Spliterator<Neuron> spliterator(){
		return Collections.singleton(this).spliterator();
	}

	@Override
	public String toString() {
		return "Neuron";
	}
	
	
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons{
	
	
	@Override
	public String toString() {
		var sb = new StringBuilder();
		for(int i = 0 ; i < this.size() ; i++) {
			sb.append(this.get(i).toString());
			if( i != this.size() - 1) sb.append(" - ");
		}
		return "NeuronLayer = " + sb.toString();
	}
	
}

interface SomeNeurons extends Iterable<Neuron>{
	
	default void connectTo(SomeNeurons other) { // this=neuron1 in out notnull ;  param=layer1
		
		if(this == other) return;
		
		for(Neuron from : this) {
			for(Neuron to : other) {
				from.out.add(to);
				to.in.add(from); // to.in = null
			}
		}
	}
}


















