import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fitness {

	private Sequence sequence;
	private Folding folding;

	public Fitness(Sequence sequence, Folding folding) {
		this.sequence = sequence;
		this.folding = folding;
	}

	// Computes the fitness of a given pair of a sequence and a folding
	// 1. Check, if folding has correct length.
	// 2. Check, if folding has overlaps (occupies the same grid point).
	// 3. Count adjacent H elements which are not in sequence.
	// The fitness is 0, if one of either check 1 or 2 fails.
	// Otherwise, the fitness is the result of the counting 3.
	public int compute() {
		if (!checkLength())
			return 0;
		if (!checkOverlaps())
			return 0;
		return countAdjacentH();
	}

	// folding must length(sequence) - 1 elements,
	// because of length(sequence) - 1 edges and first direction is implicitly
	// always F, it is added by the constructor of the Folding class.
	private boolean checkLength() {
		return (sequence.getSequence().size() == (folding.getFolding().size() + 1));
	}

	// Convert coordinate List into a Set and compare the size of the
	// collection.
	// If the set has the same length as the list, all coordinates were unique.
	private boolean checkOverlaps() {
		List<Point2D> coordinatesList = folding.getCoordinates();
		Set<Point2D> coordinatesSet = new HashSet<Point2D>(coordinatesList);

		return (coordinatesList.size() == coordinatesSet.size());
	}

	private int countAdjacentH() {		
		int count = 0;
		
		for (int i = 0; i < sequence.getSequence().size(); i++) {
			Sequence.Element element = sequence.getSequence().get(i);
			if (element.equals(Sequence.Element.H)) {
				for (int k = i+2; k < sequence.getSequence().size(); k++) {
					double distance = folding.getCoordinates().get(i)
							.getDistanceTo(folding.getCoordinates().get(k));
					if (distance == 1.) {
						++count;
					}
				}
			}
		}
		
		return count;
	}
}
