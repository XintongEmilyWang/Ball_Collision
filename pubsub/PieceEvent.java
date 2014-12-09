package game.pubsub;

import game.Piece;

public class PieceEvent implements PubSubEvent {
	final public Piece p;
	
	public PieceEvent(Piece p) {
		this.p = p;
	}
}
