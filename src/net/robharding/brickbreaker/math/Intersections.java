package net.robharding.brickbreaker.math;

public class Intersections {
	
	private Intersections() {}
	
	public static boolean AABBIntersect(float ax, float ay, float aw, float ah,
							float bx, float by, float bw, float bh) {
		return (ax < bx + bw &&
				ax + aw > bx &&
				ay < by + bh &&
				ay + ah > by);
	}
}
