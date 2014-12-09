package game;

import lab3.Point;

/**
 * This is one of several subclasses of the Piece class,
 * and is used to represent a paddle in the game. Provides
 * a special setCenter method to keep the paddle "pinned"
 * at the bottom of the game board.
 * 
 * @author Ron Cytron, with some changes by Jon Turner
 */
public class Paddle extends Piece {

	private double yCoord;  // y-coordinate of paddle
	
	public Paddle(Point center, int width, int height) {
		super(new BoundingBox(center, width, height));
		yCoord = center.getY();
	}
	/**
	 * Move the paddle to a new location. To be more explicit,
	 * moves the paddle's x-coordinate to match the specified point,
	 * while leaving the y-coordinate unchanged.
	 * 
	 * @param center is the point that determines the new paddle location
	 */
	public void setCenter(Point center) {
		super.setCenter(new Point(center.getX(), yCoord));
	}

	public String toString() {
		return "paddle at " + getCenter();
	}
}
