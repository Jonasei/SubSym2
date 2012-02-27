package test;

import java.util.ArrayList;

public class FitnessTesting {

	private PopulationChildren childrenPopulation;
	private int problemId;
	private SpikeTrainDistanceMetrics spikeTrainDistanceMetrics;

	
	public FitnessTesting(PopulationChildren childrenPopulation,int problemId,int evaluationMethod){
		this.childrenPopulation = childrenPopulation;
		this.problemId = problemId;
		spikeTrainDistanceMetrics = new SpikeTrainDistanceMetrics();
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
			SpikingNeuronPhenotype neuron = (SpikingNeuronPhenotype) childrenPopulation.getPhenotypeAt(i);
			spikeTrainDistanceMetrics.findPhenotypeSpikePosition(neuron.getSpikeTrain());
			neuron.setFitness(spikeTrainDistanceMetrics.getFintesss(1));
			ArrayList<Integer> spikeTrainPositions = spikeTrainDistanceMetrics.getSpikePosition();
			neuron.setSpikeTrainPositions(spikeTrainPositions);
		}
	}
}
