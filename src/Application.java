
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sequence sequence = new Sequence(args[0]);
		System.out.println("Sequence: " + args[0]);
		// folding must length(sequence) - 2 elements, 
		// because of length(sequence) - 1 edges and first direction is implicitly always S.
		Folding folding =   new Folding (args[1]);
		System.out.println("Folding: " + args[1]);
		System.out.print("Coordinates: ");
		for (Point2D point: folding.getCoordinates())
			System.out.print(point + " ");
		System.out.println("");
		sequence.draw(folding);
		Fitness fitness = new Fitness(sequence, folding);
		System.out.println("Fitness: " + fitness.compute());
	}

}
