package ga;

import java.util.List;

public interface Fitness {
	public boolean notGoodEnough();
	public List<Integer> getValues();
}
