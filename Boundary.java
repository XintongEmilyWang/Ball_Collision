package game;

import lab3.Point;

/**
 * This is one of several subclasses of the Piece class,
 * and is used to represent one of the boundaries of the game
 * area.
 * 
 * @author Ron Cytron, with some changes by Jon Turner
 *
 */
public class Boundary extends Piece {
	
	
	public Boundary(Point p, int width, int height) {
		super(new BoundingBox(p,width,height));
	}
	
	public void shrink(double factor) {
		// do nothing - Boundary may not shrink
	}
	
	@Override
	public void setCenter(Point center) {
		// do nothing - Boundary may not move
	}
	
	public String toString() {
		return "boundary at " + getCenter();
	}
}
