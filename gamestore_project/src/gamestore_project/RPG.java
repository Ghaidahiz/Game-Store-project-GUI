package gamestore_project;

public class RPG extends Game {
	// RPG is a genre of games, and it is a subclass of class Game, it has a
	// parametrized constructor and a copy constructor, and it implements the
	// abstract method "PriceAfterDiscount" that calculates the discount specified
	// for the RPG genre
	public RPG(String name, String publisher, double price) {
		super(name, publisher, price);
	}

	public RPG(Game g) {
		super(g.getName(), g.getPublisher(), g.getPrice());
	}

	public double priceAfterDiscount() {
		return price - (price * 0.50);
	}
}
