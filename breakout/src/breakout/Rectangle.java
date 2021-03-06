package breakout;

/**
 * Represents a rectangle
 * @invar | getTL() != null
 * @invar | getBR() != null
 * @invar | getTL().getX() < getBR().getX() && getTL().getY() < getBR().getY()
 * @immutable
 */
public class Rectangle {
	
	/**
	 * @invar | RectangleTL != null
	 * @invar | RectangleBR != null
	 * @invar | RectangleTL.getX() < RectangleBR.getX() && RectangleTL.getY() < RectangleBR.getY()
	 */
	private Point RectangleTL;
	private Point RectangleBR;

	/**
	 * Return a rectangle with given most topleft and bottomright point
	 * @pre | TL != null
	 * @pre | BR != null 
	 * @pre | TL.getX() < BR.getX() && TL.getY() < BR.getY()
	 * @post | TL.equals(getTL())
	 * @post | BR.equals(getBR())
	 */
	public Rectangle(Point TL, Point BR) {
		this.RectangleTL = TL;
		this.RectangleBR = BR;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rectangle other = (Rectangle) obj;
		if (!RectangleTL.equals(other.RectangleTL))
			return false;
		if (!RectangleBR.equals(other.RectangleBR))
			return false;
		return true;
	}
	
	/** Returns the most bottomright point of this rectangle */
	public Point getBR() {
		return this.RectangleBR;
	}

	/** Returns the most topleft point of this rectangle */
	public Point getTL() {
		return this.RectangleTL;
	}
	
	/** Checks if this rectangle has a collision with a ball with given center and diameter and returns the reflection vector
	 *  If there is no collision the function returns the zero vector.
	 * @pre | Ballcenter != null
	 * @pre | Balldiameter > 0
	 * 
	 * @post | result.equals(Vector.UP) || result.equals(Vector.DOWN) || result.equals(Vector.RIGHT) || result.equals(Vector.LEFT) || result.equals(new Vector(0,0))
	 */
	public Vector collision(Point Ballcenter, int Balldiameter) {
		if (this.RectangleTL.getX() <= Ballcenter.getX() && Ballcenter.getX() <= this.RectangleBR.getX()) { 
//			This is for top of Object collision
			if (this.RectangleTL.getY() <= (Ballcenter.getY() + Balldiameter/2) && (Ballcenter.getY() + Balldiameter/2) <= this.RectangleBR.getY()) {
				Vector vectorT = new Vector(0,-1);
				return vectorT;
			}
//			This is for bottom of Object collision
			else if (this.RectangleBR.getY() >= (Ballcenter.getY() - Balldiameter/2) && (Ballcenter.getY() - Balldiameter/2) >= this.RectangleTL.getY()) {
				Vector vectorB = new Vector(0,1);
				return vectorB;
			}
		}
		else if (this.RectangleTL.getY() <= Ballcenter.getY() && Ballcenter.getY() <= this.RectangleBR.getY()) {
//			This is for right of Object collision
			if (this.RectangleTL.getX() <= (Ballcenter.getX() - Balldiameter/2) && (Ballcenter.getX() - Balldiameter/2) <= this.RectangleBR.getX()) {
				Vector vectorR = new Vector(1,0);
				return vectorR;
			}
//			This is for left of Object collision
			else if (this.RectangleTL.getX() <= (Ballcenter.getX() + Balldiameter/2) && (Ballcenter.getX() + Balldiameter/2) <= this.RectangleBR.getX()) {
				Vector vectorL = new Vector(-1,0);
				return vectorL;
			}
		}
		Vector vector_null = new Vector(0,0);
		return vector_null;
	}
}