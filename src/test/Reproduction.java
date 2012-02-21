package test;

import java.util.ArrayList;

public class Reproduction {

	private PopulationParent parentsPopulation;
	private ArrayList<Genotype> parentGenotypes;
	
	
	public Reproduction (PopulationParent parentsPopulation){
		this.parentsPopulation = parentsPopulation;
		parentGenotypes = new ArrayList<Genotype>();
	}
	
	
	public void findParentGenotypes(){
		parentGenotypes.clear();
		for (int i = 0; i < parentsPopulation.getPopulationSize(); i++) {			
			Genotype parentGenotype = new Genotype(parentsPopulation.getPhenotypeAt(i).getGenotype());
			parentGenotypes.add(parentGenotype);		
		}
		
	}
	
	public void mutate(){
		for (int i = 0; i < parentGenotypes.size(); i++) {			
			parentGenotypes.get(i).mutate();			
		}
	}
	
	public void crossover(){
		for (int i = 0; i < parentGenotypes.size(); i+=2) {	
			int randomLength = (int) (parentGenotypes.get(i).getSize()*Math.random());
			for (int j = 0; j < randomLength ; j++) {
				int bitParent1 = parentGenotypes.get(i).getBitAt(j);
				int bitParent2 = parentGenotypes.get(i+1).getBitAt(j);
				parentGenotypes.get(i).setBitAt(j, bitParent2);
				parentGenotypes.get(i+1).setBitAt(j, bitParent1);
			}		
		}
	}
	
	public ArrayList<Genotype> getGenotypes(){
		return parentGenotypes;
	}
	
	
	public void run(){
		findParentGenotypes();
		crossover();
		mutate();
	}
}
