package test;

public abstract class BasicPhenotype implements Comparable<BasicPhenotype>{
	
	protected int[] phenotype;
	protected double fitnessScore;
	protected Genotype genotype;
	
	public BasicPhenotype(Genotype genotype){
		this.genotype = genotype;
		phenotype =new int[genotype.getSize()];
		for (int i = 0; i < phenotype.length; i++) {
			phenotype[i] = genotype.getBitAt(i);
		}
	}
	
	
	public String toString(){
		String stringToReturn ="";
		for (int i = 0; i < phenotype.length; i++) {
			stringToReturn += phenotype[i];
		}
		return stringToReturn;
	}
	
	public Genotype getGenotype(){
		return genotype;
	}

	
	public int getLength(){
		return phenotype.length;
	}
	
	public int getItemAtIndex(int index){
		return phenotype[index];
	}
	
	public abstract void calculateFitnessEvaluation();

	public abstract int getProblemId();
	
	public abstract double getMaxFitness();

	@Override
	public int compareTo(BasicPhenotype o) {
		if(this.fitnessScore > o.fitnessScore){
			return 1;
		}else if(this.fitnessScore < o.fitnessScore){
			return -1;
		}else{
			return 0;
		}
	}

	public double fitnessEvaluation() {
		return fitnessScore;
	}
}
