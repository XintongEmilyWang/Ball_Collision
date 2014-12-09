package game;


import java.awt.event.MouseEvent;

import lab3.Point;

import nip.*;

/**
 * This class adapts mouse actions and tells the Controller what to do.
 * 
 * @author Ron Cytron
 */
public class Main extends Tool {

	private Controller controller;
	private int numTicks;
	
	public Main() {
		numTicks = 1;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		controller.tick(numTicks);
	}

	/**
	 * When the mouse moves, inform the Controller
	 * to set its Paddle at the specified location.
	 */
	@Override
	public void mouseMoved(MouseEvent me) {
		controller.setPaddle(new Point(me.getX(), me.getY()));

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public String toString() {
		return "game";
	}

	private void tick(int num) {
		this.numTicks = num;
		controller.tick(num);
	}

	@Override
	public void actionNameCalled(String name) {
		if (name.equals("start")) {
			controller.start();
		}
		if (name.equals("stop")) {
			controller.stop();
		}
		if (name.equals("single tick"))
			tick(1);
		if (name.equals("ten ticks"))
			tick(10);
		if (name.equals("hundred ticks"))
			tick(100);
		if (name.equals("add ball")) {
			controller.genBall();
		}

	}

	@Override
	public String[] getEventNames() {
		return new String[] { "start", "stop" , "single tick",
				"ten ticks", "hundred ticks", "add ball" };
	}

	public static void main(String args[]) {
		NIP nip = new NIP(512, 512);
		Main m = new Main();
		m.controller = new Controller(nip.getTargetPanel());
		nip.setTool(m);
	}
}
