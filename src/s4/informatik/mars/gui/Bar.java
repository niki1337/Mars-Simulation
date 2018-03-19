package s4.informatik.mars.gui;

import java.awt.Graphics;

import s4.informatik.mars.util.Utils;

public abstract class Bar {
	
	protected int x, y;
	protected int width, height;
	protected float max;
	protected float fill;
	
	private boolean changingMax = false;
	private int temp_newMax = 0;
	private int temp_maxDiff = 0;
	private int temp_changeTicks = 0;
	private int temp_maxTicks = 0;
	private double temp_barMaxChangeValue;
	
	public Bar(int x, int y, int width, int height, float max) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.max = max;
		fill = max;
	}
	
	public float getFill() {
		return fill;
	}
	
	public void setFill(float fill) {
		if (fill > max) {
			this.fill = max;
			return;
		} else if (fill < 0) {
			this.fill = 0;
			return;
		}
		this.fill = fill;
	}
	
	public float getMax() {
		return max;
	}
	
	public void changeMaxSmoothly(int newMax, int ticks) {
		if (newMax < max || changingMax)
			return;
		
		changingMax = true;
		temp_newMax = newMax;
		temp_maxDiff = newMax - (int) max;
		temp_maxTicks = ticks;
		temp_barMaxChangeValue = Utils.getBarMaxChangeValue(ticks, temp_maxDiff);
	}
	
	public void update() {
		if (changingMax) {
			temp_changeTicks++;
			if (temp_changeTicks >= temp_maxTicks) {
				changingMax = false;
				temp_changeTicks = 0;
				temp_maxTicks = 0;
				max = temp_newMax; // Update max, so it has an integer value
				temp_newMax = 0;
				temp_maxDiff = 0;
			} else {
				max += Math.sin((Math.PI / temp_maxTicks) * temp_changeTicks) * temp_barMaxChangeValue;
			}
		}
		
		if (fill >= max && !changingMax) changeMaxSmoothly((int) max * 2, 100);
	}
	
	public abstract void render(Graphics g);
}