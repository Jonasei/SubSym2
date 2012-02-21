package test;

import java.util.ArrayList;

public abstract class Population {

	protected ArrayList<BasicPhenotype> population;
	
	public int getPopulationSize() {
		return population.size();
	}
	
	public BasicPhenotype getPhenotypeAt(int index) {
		return population.get(index);
	}
}
