package observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

class EventGame<T>{
	
	private List<BiConsumer<Object, T>> consumers = new ArrayList<>();
	
	public void subscribe(BiConsumer<Object, T> consumer) {
		consumers.add(consumer);
	}
	
	public void invoke(Object sender, T arg) {
		for(BiConsumer<Object, T> consumer: consumers) {
			consumer.accept(sender, arg);
		}
	}
}

public class Game {

	public EventGame<Void> ratEnters = new EventGame<>();
	public EventGame<Void> ratDies = new EventGame<>();
	public EventGame<Rat> notifyRat = new EventGame<>();
}


class Rat implements Closeable{
	
	private Game game;
	public int attack = 1;
	
	public Rat(Game game) {
		this.game = game;
		game.ratEnters.subscribe((sender, arg) -> {
			if(sender != this) {
				++attack;
				game.notifyRat.invoke(this, (Rat) sender);
			}
		});
		
		game.notifyRat.subscribe((sender, rat) -> {
			if(rat == this) ++attack;
		});
		game.ratDies.subscribe((sender, arg) -> --attack);
		game.ratEnters.invoke(this, null);
	}

	@Override
	public void close() throws IOException {
		
	}
	
}