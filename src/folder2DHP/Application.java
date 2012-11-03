package folder2DHP;

import ga.GeneticAlgorithms;


public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sequence sequence = new Sequence(args[0]);
		
		Foldings foldings = new Foldings(sequence, 5); // 5 initial foldings
		foldings.generateRandomPopulation();
		foldings.printAll();
		
		GeneticAlgorithms optimizer = new GeneticAlgorithms(foldings);
		optimizer.simple(2); // compute 2 generations
		
		foldings = (Foldings)optimizer.getPopulation();
		foldings.printAll();
	}

}
