



package game.collision;

import game.*;
import game.motion.*;

/**
 * Collision handler for a brick and the floor.
 * 
 * @author Ron Cytron, with changes by Jon Turner
 */
public class BrickHitsFloor extends CollisionHandler {
	
	private Brick brick;
	
	/**
	 * Constructor for BrickHitsFloor
	 * 
	 * @param brick the brick being monitored
	 * @param floor the boundary object hit by the brick
	 * @param brickMover the object responsible for moving the brick
	 */
	public BrickHitsFloor(Brick brick, Boundary floor, PieceMover brickMover) {
		super(brick, floor);
		this.brick = brick;
	}

	/**
	 * This method is called when collisions occur and responds
	 * appropriately. Basic action is to kill the brick.
	 */
	@Override
	public void observeEvent(Collision e) {
		this.die(); brick.die();
	}
}