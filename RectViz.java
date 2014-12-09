package game;

import java.awt.Color;
import nip.*;

import nip.Rectangle;
import game.pubsub.*;
import lab3.Point;

/**
 * Handle the display of rectangular Pieces.
 * 
 * @author Ron Cytron, with some changes by Jon Turner
 */
public class RectViz extends PieceViz implements Subscriber<PieceEvent> {

	private int width, height;
	
	public RectViz(Piece p, Color c, GraphicsPanel panel) {
		super(p,c,panel);
		setGraphic(newGraphic());
	}
	
	/**
	 * Used to determine when the size of a RectViz has changed
	 * 
	 * @return true if the size has just changed
	 */
	protected boolean newSize() {
		return piece.getWidth() != width || piece.getHeight() != height;
	}
	
	/**
	 * Compute new graphical representation of the RectViz.
	 * 
	 * @return the new graphic
	 */
	protected Graphic newGraphic() {	
		width = piece.getWidth();
		height = piece.getHeight();	
		Graphic g = new Rectangle(0, 0, width, height);
		return g;
	}
	
	public String toString() {
		return "rectViz at " + piece.getCenter();
	}
}
