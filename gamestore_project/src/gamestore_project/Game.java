package gamestore_project;

import java.io.Serializable;

public abstract class Game implements Serializable { 
// Game is an abstract class that has the essential info for each game

	protected String name;
	protected String publisher;
	protected double price;

	public Game(String name, String publisher, double price) { // parameterized constructor
		this.name = name;
		this.publisher = publisher;
		this.price = price;
	}

	public abstract double priceAfterDiscount(); // an abstract method that will be implemented by each subclass / genre of
													// games differently

	public String getName() {
		return name;
	}

	public String getPublisher() {    //getters
		return publisher;
	}

	public double getPrice() {
		return price;
	}

	public String toString() { // a toString (overridden) method that will return the game's info
		String sPrice = priceAfterDiscount() + "";
		return "\n ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~ \n GAME NAME: " + name + "\n GAME PUBLISHER: " + publisher
				+ "\n GAME PRICE: " + price + "\n PRICE AFTER DISCOUNT: "
				+ sPrice.substring(0, (sPrice.indexOf('.') + 2)) + "\n ~*~*~*~*~*~*~*~*~*~*~*~*~*~*~ \n";
	}
}
