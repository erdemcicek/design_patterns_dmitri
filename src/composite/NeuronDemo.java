package composite;

import java.util.ArrayList;
import java.util.List;

public class NeuronDemo {
	
	public static void main(String[] args) {
		
		Neuron neuron1 = new Neuron();
		neuron1.in = new ArrayList<>(List.of(new Neuron(), new Neuron()));
		neuron1.out = new ArrayList<>(List.of(new Neuron(), new Neuron()));
		Neuron neuron2 = new Neuron();
		neuron2.in = new ArrayList<>();
		neuron2.out = new ArrayList<>();
		
		NeuronLayer layer1 = new NeuronLayer();
		NeuronLayer layer2 = new NeuronLayer();
		
		//layer1.in = null;
		layer2.addAll(List.of(new Neuron(), new Neuron()));
		
		neuron1.connectTo(neuron2);
		neuron1.connectTo(layer1);
		layer1.connectTo(neuron1);
		layer1.connectTo(layer2);
		
		System.out.println(neuron1.in);
		System.out.println(neuron1.out);
		System.out.println(layer1);
		System.out.println(neuron2.in);
		System.out.println(neuron2.out);
		System.out.println(layer2);
	}

}
