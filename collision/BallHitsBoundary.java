package game.collision;

import game.*;
import game.motion.*;

/**
 * Collision handler for a ball and a boundary.
 * 
 * @author Ron Cytron, with changes by Jon Turner
 */
public class BallHitsBoundary extends CollisionHandler  {
	
	private Ball ball;		// the particular ball being monitored
	private Boundary bound;	// the boundary hit by the ball
	private PieceMover ballMover;	// the object that moves the ball

	/**
	 * Constructor for BallHitsBoundary
	 * 
	 * @param ball the ball being monitored
	 * @param bound the boundary hit by the ball
	 * @param ballMover the object responsible for moving the ball
	 */
	public BallHitsBoundary(Ball ball, Boundary bound, PieceMover ballMover) {
		super(ball, bound);
		this.ball = ball; this.bound = bound;
		this.ballMover = ballMover;
	}

	/**
	 * This method is called when collisions occur and responds
	 * appropriately. Uses ballMover's bounce method to change the
	 * ball's direction of motion appropriately.
	 */
	@Override
	public void observeEvent(Collision e) {
		if (bound.getWidth() > bound.getHeight()) {
			// this must be the ceiling or floor (assuming reasonable geometry)
			ballMover.bounce(1, -1);
		} else {
			// must be a wall
			ballMover.bounce(-1, 1);
		}
	}
}
