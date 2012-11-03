package folder2DHP;
public class Point2D {
	public int x;
	public int y;

	public enum Neighbour {
		N, S, E, W
	}

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point2D getNeighbour(Neighbour neighbour) {
		switch (neighbour) {
		case N:
			return new Point2D(x, y + 1);
		case S:
			return new Point2D(x, y - 1);
		case E:
			return new Point2D(x + 1, y);
		case W:
			return new Point2D(x - 1, y);
		default:
			return null;
		}
	}

	public double getDistanceTo(Point2D point) {
		return Math.sqrt(Math.pow(((double)this.x - (double)point.x), 2)
				+ Math.pow(((double)this.y - (double)point.y), 2));
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj ) return true;
		if ( !(obj instanceof Point2D) ) return false;
		return ((this.x == ((Point2D)obj).x) && (this.y == ((Point2D)obj).y));
	}
	
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
