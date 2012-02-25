package test;

public class FitnessTesting {

	private PopulationChildren childrenPopulation;
	private int problemId;
	
	public FitnessTesting(PopulationChildren childrenPopulation,int problemId){
		this.childrenPopulation = childrenPopulation;
		this.problemId = problemId;
	}
	
	public void evaluateFitness(){	
		switch (problemId) {
		case 0:
			basicFitnessEvaluation();
			break;
		case 1:
			basicFitnessEvaluation();
			break;
			
		case 2:
			colonelBlottoFitnessEval();
			break;
			
		case 3:
			spikingNeuronTrainFitnessEval();
			break;
		default:
			break;
		}
	}
	
	public void basicFitnessEvaluation(){
		for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
			childrenPopulation.getPhenotypeAt(i).calculateFitnessEvaluation();
		}
	}
	
	public void colonelBlottoFitnessEval(){
		for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
			ColonelBlottoPhenotype generalOne = (ColonelBlottoPhenotype) childrenPopulation.getPhenotypeAt(i);
			for (int j = i+1; j < childrenPopulation.getPopulationSize(); j++) {
				generalOne.battleWith((ColonelBlottoPhenotype) childrenPopulation.getPhenotypeAt(j));
			}
			generalOne.calculateFitnessEvaluation();
		}
	}
	
	public void spikingNeuronTrainFitnessEval(){
		for (int i = 0; i < childrenPopulation.getPopulationSize(); i++) {
			childrenPopulation.getPhenotypeAt(i).calculateFitnessEvaluation();
		}
	}
}
