package test;

public class FitnessTesting {

	private PopulationChildren childrenPopulation;
	private int problemId;
	
	public FitnessTesting(PopulationChildren childrenPopulation,int problemId){
		this.childrenPopulation = childrenPopulation;
		this.problemId = problemId;
	}
	
	public void evaluateFitness(){
		if(problemId==0||problemId == 1){
			for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
				childrenPopulation.getPhenotypeAt(i).calculateFitnessEvaluation();
			}
		}else if(problemId==2){
			for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
				ColonelBlottoPhenotype generalOne = (ColonelBlottoPhenotype) childrenPopulation.getPhenotypeAt(i);
				for (int j = i+1; j < childrenPopulation.getPopulationSize(); j++) {
					generalOne.battleWith((ColonelBlottoPhenotype) childrenPopulation.getPhenotypeAt(j));
				}
				generalOne.calculateFitnessEvaluation();
			}
		}
		else if(problemId == 3){
			for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
				childrenPopulation.getPhenotypeAt(i).calculateFitnessEvaluation();
			}
		}
	}
}
