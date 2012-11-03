package folder2DHP;

import ga.Fitness;
import ga.Population;

import java.util.ArrayList;
import java.util.List;

public class Foldings implements Population {

	private int initialPopulationSize;
	private Sequence sequence;
	private List<Folding> foldings;

	public Foldings(Sequence sequence, int initialPopulationSize) {
		this.initialPopulationSize = initialPopulationSize;
		this.sequence = sequence;
		this.foldings = new ArrayList<Folding>();
	}

	@Override
	public void generateRandomPopulation() {
		this.foldings = new ArrayList<Folding>();

		for (int i = 0; i < initialPopulationSize; i++) {
			foldings.add(new Folding(sequence.getSequence().size()));
		}
	}

	@Override
	public Fitness evaluation() {
		Energies energies = new Energies();

		for (Folding folding : foldings) {
			Energy energy = new Energy(sequence, folding);
			energies.getValues().add(energy.compute());
		}
		return energies;
	}

	@Override
	public Population selection() {
		return this;
	}

	@Override
	public void mutation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void crossover() {
		// TODO Auto-generated method stub

	}

	public void printAll() {
		System.out.println("Sequence: " + sequence.toString());

		Fitness fitness = evaluation();

		int w = Math.max(8, sequence.getSequence().size() + 1);

		System.out.format("%4s  %-" + w + "s%-8s", "#", "Folding", "Energy");
		System.out.println();

		String format = "%4d%" + w + "s%8d";
		int i = 0;
		for (Folding folding : foldings) {
			System.out.format(format, i, folding, fitness.getValues().get(i));
			System.out.println();
			if (fitness.getValues().get(i) > 0) {
				System.out.println();
				sequence.draw(folding);
				System.out.println();
				System.out.println();
			}
			++i;
		}
	}
}
