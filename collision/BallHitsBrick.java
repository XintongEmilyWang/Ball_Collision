package game.collision;

import game.*;
import game.motion.*;
import java.util.*;

/**
 * Collision handler for a ball and a brick.
 * 
 * @author Ron Cytron, with changes by Jon Turner
 */
public class BallHitsBrick extends CollisionHandler {
	
	Ball ball;  		// the particular ball being monitored
	Brick brick;  	// the brick being monitored
	PieceMover ballMover;  // the object that moves the ball
	
	/**
	 * Constructor for BallHitsBrick
	 * 
	 * @param ball the ball being monitored
	 * @param brick the brick hit by the ball
	 * @param ballMover the object responsible for moving the ball
	 */
	public BallHitsBrick(Ball ball, Brick brick, PieceMover ballMover) {
		super(ball, brick);
		this.ball = ball; this.brick = brick;	
		this.ballMover = ballMover;
	}
	
	/**
	 * This method is called when collisions occur and responds
	 * appropriately.
	 */
	@Override
	public void observeEvent(Collision c) {
		// FIXME - do something on a collision
	}
}
