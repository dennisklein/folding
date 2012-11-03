package ga;

public class GeneticAlgorithms {

	private Population population;
	private Fitness fitness;

	public GeneticAlgorithms(Population population) {
		this.population = population;
		this.fitness = null;
	}

	public void simple(int maxGeneration) {
		int generation = 0;
		fitness = population.evaluation();
		while (fitness.notGoodEnough() && (generation < maxGeneration)) {
			++generation;
			population = population.selection();
			population.mutation();
			population.crossover();
			fitness = population.evaluation();
		}
	}
	
	public Population getPopulation() {
		return population;
	}
}
