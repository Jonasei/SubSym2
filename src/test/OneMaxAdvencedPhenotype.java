package test;


public class OneMaxAdvencedPhenotype extends BasicPhenotype {

	private int[] optimalSet;
	
	public OneMaxAdvencedPhenotype(Genotype genotype){
		super(genotype);
		createOptimalSet();
	}
	
	private void createOptimalSet(){
		optimalSet = new int[phenotype.length];
		for (int i = 0; i < 8; i++) {
			optimalSet[i] = 1;
		}
		for (int i = 8; i < 12; i++) {
			optimalSet[i] = 0;
		}
		for (int i = 12; i < 14; i++) {
			optimalSet[i] = 1;
		}
		for (int i = 14; i < 20; i++) {
			optimalSet[i] = 0;
		}
		for (int i = 20; i < phenotype.length; i++) {
			optimalSet[i] = 1;
		}
	}
	
	
	@Override
	public void calculateFitnessEvaluation(){
		fitnessScore = 0;
		for (int i = 0; i < phenotype.length; i++) {
			if (phenotype[i] == optimalSet[i]) {
				fitnessScore++;
			}
		}
		if(fitnessScore==genotype.getSize()){
			Run.finished = true;
		}
	}

	@Override
	public int getProblemId() {
		return 1;
	}

	@Override
	public double getMaxFitness() {
		// TODO Auto-generated method stub
		return genotype.getSize();
	}

	
}
