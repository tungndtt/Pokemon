package Game;

import java.awt.Point;

/**
 * The line for valid match
 * @author Tung Doan
 */
public class PikachuLine {
	public Point p1;
	public Point p2;

	public PikachuLine(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public String toString() {
		String string = "(" + p1.x + "," + p1.y + ") and (" + p2.x + "," + p2.y + ")";
		return string;
	}
}
