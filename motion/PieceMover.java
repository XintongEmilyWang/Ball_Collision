package game.motion;

import lab3.Vector;
import game.*;
import game.time.*;
import game.pubsub.*;

/**
 * At each tick, set the location of the Piece according
 *   to the specified Trajectory.
 *
 */
public class PieceMover implements Subscriber<ClockTick> {
	
	private Piece piece;
	private Trajectory trajectory;
	private boolean dead;
	
	public PieceMover(Piece p, Trajectory t) {
		this.piece = p;
		this.trajectory = t;
		this.dead = false;
		Clock.instance().addSubscriber(this);
	}
	
	/**
	 * In response to a clock tick, set the location of the
	 * Relocatable to the current location of the Trajectory.
	 */
	@Override
	public void observeEvent(ClockTick e) {
		// FIXME:  Check for death and if so, this is dead too
		if (piece.isDead()) die();
		piece.setCenter(trajectory.getCurrentLocation());				
	}

	/**
	 * Change the Trajectory associated with this piece to a new 
	 * one whose velocity's x and y components are multiplied
	 * by xfactor and yfactor, respectively. As one example,
	 * if xfactor=0.5 and yfactor=-1, the piece moves half as fast
	 * to the left or right, and reverses direction its up-down
	 * motion.
	 * @param xfactor
	 * @param yfactor
	 */
	public void bounce(double xfactor, double yfactor) {
		Vector v = trajectory.getCurrentVelocity();
		Vector a = trajectory.getCurrentAcceleration();
		v = new Vector(v.getDeltaX()*xfactor, v.getDeltaY()*yfactor);
		this.trajectory = new ConstantAcceleration(this.trajectory, v, a);
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	public void die() {
		dead = true;
	}
	
}
