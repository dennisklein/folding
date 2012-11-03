package ga;

public interface Population {
	public void generateRandomPopulation();

	public Fitness evaluation();

	public Population selection();

	public void mutation();

	public void crossover();
}
