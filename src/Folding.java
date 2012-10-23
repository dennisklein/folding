import java.util.ArrayList;
import java.util.List;

public class Folding {

	private List<Direction> folding;
	private List<Point2D> coordinates;
	private boolean generateNewCoordinates;

	public enum Direction {
		F, L, R
	}

	public Folding(String folding) {
		coordinates = null;
		generateNewCoordinates = true;
		this.folding = new ArrayList<Folding.Direction>();
		
		this.folding.add(Direction.F);

		for (char c : folding.toCharArray()) {
			switch (c) {
			case 'F':
				this.folding.add(Direction.F);
				break;
			case 'L':
				this.folding.add(Direction.L);
				break;
			case 'R':
				this.folding.add(Direction.R);
			default:
				break;
			}
		}
	}

	public List<Direction> getFolding() {
		generateNewCoordinates = true;
		return folding;
	}

	private void generateCoordinates() {
		coordinates = new ArrayList<Point2D>();

		Point2D point = new Point2D(0, 0);
		Point2D.Neighbour lastNeighbour = Point2D.Neighbour.N;
		Point2D.Neighbour nextNeighbour = null;
		coordinates.add(point);
		for (Direction e : folding) {
			switch (e) {
			case L:
				switch (lastNeighbour) {
				case N:
					nextNeighbour = Point2D.Neighbour.W;
					break;
				case S:
					nextNeighbour = Point2D.Neighbour.E;
					break;
				case E:
					nextNeighbour = Point2D.Neighbour.N;
					break;
				case W:
					nextNeighbour = Point2D.Neighbour.S;
					break;
				default:
					break;
				}
				break;
			case F:
				switch (lastNeighbour) {
				case N:
					nextNeighbour = Point2D.Neighbour.N;
					break;
				case S:
					nextNeighbour = Point2D.Neighbour.S;
					break;
				case E:
					nextNeighbour = Point2D.Neighbour.E;
					break;
				case W:
					nextNeighbour = Point2D.Neighbour.W;
					break;
				default:
					break;
				}
				break;
			case R:
				switch (lastNeighbour) {
				case N:
					nextNeighbour = Point2D.Neighbour.E;
					break;
				case S:
					nextNeighbour = Point2D.Neighbour.W;
					break;
				case E:
					nextNeighbour = Point2D.Neighbour.S;
					break;
				case W:
					nextNeighbour = Point2D.Neighbour.N;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			point = point.getNeighbour(nextNeighbour);
			lastNeighbour = nextNeighbour;
			coordinates.add(point);
		}
	}
	
	public List<Point2D> getCoordinates() {
		if (generateNewCoordinates) {
			generateCoordinates();
			generateNewCoordinates = false;
		}
		
		return coordinates;
	}
}
