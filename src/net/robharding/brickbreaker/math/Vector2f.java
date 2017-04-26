package net.robharding.brickbreaker.math;

public class Vector2f {
	
	float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public float magnitude() {
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
