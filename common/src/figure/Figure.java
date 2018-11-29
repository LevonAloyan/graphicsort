package figure;

import javax.swing.*;
import java.awt.*;

public abstract class Figure implements Runnable {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;


	private JPanel canvas;

	/**
	 * Speed of this Figure by x coordinate
	 */
	private int dX;
	/**
	 * Speed of this Figure by y coordinate
	 */
	private int dY;

	private Thread t;

	private boolean isRunning;

	private boolean isPaused;

	public Figure(int x, int y, int width, int height, JPanel canvas) {
		this(x, y, width, height, canvas, Color.BLUE);
	}

	public Figure(int x, int y, int width, int height, JPanel canvas, Color color) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.canvas = canvas;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void move(int dX, int dY) {
		this.x += dX;
		this.y += dY;
	}

	public abstract void draw(Graphics g);

	public abstract boolean isBelong(int x, int y);

	@Override
	public void run() {
		while (isRunning) {
			if (isPaused) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			move();
			canvas.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void move() {
		move(dX, dY);
	}

	public void stop() {
		continueRun();
		isRunning = false;
	}

	public void pause() {
		isPaused = true;
	}

	synchronized  public void continueRun() {
		if(isPaused) {
			isPaused = false;
			notify();
		}
	}


	public void start() {
		if (t != null && isRunning) {
			try {
				isRunning = false;
				t.join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = new Thread(this);
		dX = 1;
		dY = 1;
		isRunning = true;
		isPaused = false;
		t.start();
	}

}

