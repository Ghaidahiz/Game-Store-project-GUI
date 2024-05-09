package gamestore_project;

public class StoryRich extends Game {
	// Story-rich is a genre of games, and it is a subclass of class Game, it has a
	// parametrized constructor and a copy constructor, and it implements the
	// abstract method "PriceAfterDiscount" that calculates the discount specified
	// for the story-rich genre

	public StoryRich(String name, String publisher, double price) {
		super(name, publisher, price);
	}

	public StoryRich(Game g) {
		super(g.getName(), g.getPublisher(), g.getPrice());
	}

	public double priceAfterDiscount() {
		return price - (price * 0.40);
	}
}
