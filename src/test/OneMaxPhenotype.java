package test;

public class OneMaxPhenotype extends BasicPhenotype{
	
	
	
	public OneMaxPhenotype(Genotype genotype){
		super(genotype);
	}
	
	@Override
	public void calculateFitnessEvaluation(){
		fitnessScore = 0;
		for (int i = 0; i < phenotype.length; i++) {
			if (phenotype[i] == 1) {
				fitnessScore++;
			}
		}
		if(fitnessScore==genotype.getSize()){
			Run.finished = true;
		}
	}

	@Override
	public int getProblemId() {
		return 0;
	}

	@Override
	public double getMaxFitness() {
		return genotype.getSize();
	}

	
	


	

}
