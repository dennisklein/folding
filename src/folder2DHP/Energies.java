package folder2DHP;

import ga.Fitness;

import java.util.ArrayList;
import java.util.List;

public class Energies implements Fitness {

	private List<Integer> energies;
	
	public Energies() {
		energies = new ArrayList<Integer>();
	}
	
	@Override
	public boolean notGoodEnough() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<Integer> getValues() {
		return energies;
	}

}
