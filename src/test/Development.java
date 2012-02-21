package test;

import java.util.ArrayList;

public class Development {
	
	private PopulationChildren childrenPopulation;
	private int generationPool;
	private int bitSize;
	private double mutateRate;
	private int problemId;
	
	public Development(PopulationChildren childrenPopulation, int generationPool, int bitSize,double mutateRate,int problemId){
		this.childrenPopulation = childrenPopulation;
		this.generationPool = generationPool;
		this.bitSize = bitSize;
		this.mutateRate = mutateRate;
		this.problemId = problemId;
	}
	

	public void createFirstGeneration(){
		for (int j = 0; j < generationPool; j++) {
			Genotype genotype = new Genotype(bitSize,mutateRate);
			BasicPhenotype phenotype = null;
			if(problemId == 0){				
				phenotype = new OneMaxPhenotype(genotype);
			}else if(problemId ==1){
				phenotype = new OneMaxAdvencedPhenotype(genotype);
			}else{
				phenotype = new ColonelBlottoPhenotype(genotype);
			}
			childrenPopulation.addChild(phenotype);
		}
	}
	
	public void developOneMax(ArrayList<Genotype> parentGenotypes){
		for (int i = 0; i < generationPool; i++) {			
			OneMaxPhenotype phenotype = new OneMaxPhenotype(parentGenotypes.get(i));
			childrenPopulation.addChild(phenotype);		
		}
	}
	
	public void developOneMaxAdvanced(ArrayList<Genotype> parentGenotypes){
		for (int i = 0; i < generationPool; i++) {			
			OneMaxAdvencedPhenotype phenotype = new OneMaxAdvencedPhenotype(parentGenotypes.get(i));
			childrenPopulation.addChild(phenotype);		
		}
	}
	
	public void developColonelBlotto(ArrayList<Genotype> parentGenotypes){
		for (int i = 0; i < generationPool; i++) {			
			ColonelBlottoPhenotype phenotype = new ColonelBlottoPhenotype(parentGenotypes.get(i));
			childrenPopulation.addChild(phenotype);		
		}
	}
	
	public void develop(ArrayList<Genotype> parentGenotypes){
		if(problemId == 0){
			developOneMax(parentGenotypes);
		}else if(problemId ==1){
			developOneMaxAdvanced(parentGenotypes);
		}else{
			developColonelBlotto(parentGenotypes);
		}
	}
}
