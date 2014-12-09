package game;

import game.collision.*;
import game.motion.*;
import game.time.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;

import lab3.Point;
import lab3.Vector;
import nip.GraphicsPanel;

/**
 * As the primary controller of the game, this class provides
 * hooks to control the game's components.  It is separated
 * from actual mouse events and such which are handled by Main.
 * 
 * @author Ron Cytron, with some changes by Jon Turner
 */
public class Controller {
	private GraphicsPanel panel;
	private Paddle paddle;
	private Boundary floor, top, left, right;
	private Random random;
	private LinkedList<Brick> bricks;

	public final static int numBricksHoriz = 4, numBricksVert = 5;

	public Controller(GraphicsPanel panel) {
		this.panel = panel;
		random = new Random();
		bricks = new LinkedList<Brick>();
		
		makePaddle(); genBoundaries(100);
		genBricks(); genBall();
	}
	
	private void makePaddle() {
		paddle = new Paddle(new Point(
				panel.getWidth()/2,
				panel.getHeight()-20),
				125, 40);
		new RectViz(paddle, Color.GREEN, panel);
	}
	
	private Boundary makeBoundary(Point center, int width, int height, Color color) {
		Boundary b = new Boundary(center, width, height);
		new RectViz(b, color, panel);
		return b;
	}

	/**
	 * Generates the boundaries for the game
	 * @param padding Boundary extends this number of pixels past the
	 * visible part of the screen
	 * @param recessFloor Floor Boundary is recessed this many pixels. 
	 * A negative value raises the floor.
	 */
	private void genBoundaries(int pad) {
		int w = panel.getWidth(); int h = panel.getHeight();
		top   = makeBoundary(new Point(w/2,5-pad/2), w+2*pad, pad+2, Color.darkGray);
		left  = makeBoundary(new Point(5-pad/2,h/2), pad+2, h+2*pad, Color.darkGray);
		right = makeBoundary(new Point((w-5)+pad/2,h/2), pad+2, h+2*pad, Color.darkGray);
		floor = makeBoundary(new Point(w/2,(h-10)+pad/2), w+2*pad, pad, Color.YELLOW);
	}
	
	private void makeBrick(Point p, int width, int height, Vector accel) {
		Brick b = new Brick(p,width,height);
		new RectViz(b, Color.RED, panel);
		bricks.add(b);
		PieceMover mover = new PieceMover(b, 
				new ConstantAcceleration(
						b.getCenter(), new Vector(0,0), accel
				)
		);
		new BrickHitsFloor(b, floor, mover);
	}

	private void genBricks() {
		int bwidth = panel.getWidth() / (numBricksHoriz*2-1);
		int bheight = (3*panel.getHeight()/4) / (numBricksVert*2-1);
		int x = bwidth/2;
		for (int i=0; i < numBricksHoriz; ++i) {
			int y = bheight/2;
			for (int j=i; j < numBricksVert; ++j) {
				Point p = new Point(x,y);
				Vector accel = new Vector(0,10*j);
				makeBrick(p,bwidth,bheight,accel);
				y += 2*bheight;
			}
			x += 2*bwidth;
		}
	}

	/**
	 * Generate a new Ball that starts at some random spot
	 */
	public void genBall() {
		Ball ball = new Ball(new Point(paddle.getCenter().getX(),
									   panel.getHeight()/4),
						10.0);
		
		new CircViz(ball,Color.BLUE, panel);
		//
		// TRY: Different initial Trajectories for the Ball
		//
		PieceMover ballMover = new PieceMover(
				ball, 
				new ConstantAcceleration(
						ball.getCenter(), 
						new Vector(300,-300),
						new Vector(0,0)
				)
		);

		new BallHitsBoundary(ball, top,  ballMover);
		new BallHitsBoundary(ball, left, ballMover);
		new BallHitsBoundary(ball, right,ballMover);
		new BallHitsBoundary(ball, floor, ballMover);
		
		for (Brick brick : bricks) {
			new BallHitsBrick(ball, brick, ballMover);
		}
	}


	/**
	 * Starts the Clock ticking continuously.
	 */
	public void start() {
		Clock.instance().start();
	}

	/**
	 * Halts the continuous ticking of the Clock.
	 */
	public void stop() {
		Clock.instance().stop();
	}

	/**
	 * Set the Paddle to the specified destination.
	 * @param p
	 */
	public void setPaddle(Point p) {
		paddle.setCenter(p);
	}

	/**
	 * Called by a mouseClick from Main, this steps the game some
	 * number of ticks.
	 * 
	 * @param numTicks the number of ticks to step the game
	 */
	public void tick(int numTicks) {
		for (int i=0; i < numTicks; ++i) {
			Clock.instance().tick();
		}
	}
}
