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
			
			switch (problemId) {
			case 0:
				phenotype = new OneMaxPhenotype(genotype);
				break;
			case 1:
				phenotype = new OneMaxAdvencedPhenotype(genotype);
				break;
			case 2:
				phenotype = new ColonelBlottoPhenotype(genotype);
				break;
			case 3:
				phenotype = new SpikingNeuronPhenotype(genotype);
				break;
			default:
				break;
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
	
	public void developSpikingNeuron(ArrayList<Genotype> parentGenotypes){
		for (int i = 0; i < generationPool; i++) {			
			SpikingNeuronPhenotype phenotype = new SpikingNeuronPhenotype(parentGenotypes.get(i));
			childrenPopulation.addChild(phenotype);		
		}
	}
	
	
	public void develop(ArrayList<Genotype> parentGenotypes){
		if(problemId == 0){
			developOneMax(parentGenotypes);
		}else if(problemId ==1){
			developOneMaxAdvanced(parentGenotypes);
		}else if(problemId ==2){
			developColonelBlotto(parentGenotypes);
		}else{
			developSpikingNeuron(parentGenotypes);
		}
	}
}
