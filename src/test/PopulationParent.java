package test;

import java.util.ArrayList;
import java.util.Collections;

public class PopulationParent extends Population {
	private int numberOfChildrenToCreate;

	public PopulationParent(int numberOfChildrenToCreate) {
		population = new ArrayList<BasicPhenotype>();
		this.numberOfChildrenToCreate = numberOfChildrenToCreate;
	}

	/**
	 * Select the type of protocol to select children to adult. <br>
	 * Random - type = 0 <br>
	 * Fitness proportionate - type = 1 <br>
	 * Sigma-scaling - type = 2 <br>
	 * Tournament selection - type = 3 <br>
	 * Stochastic uniform selection - type = 4
	 * 
	 * @param populationAdults
	 * @param type
	 */
	public void parentSelection(PopulationAdults populationAdults, int type) {
		if (type == 0) {
			double randomSelect = Math.random();
			if(randomSelect>0.75){
				fitnessProportionate(populationAdults);
			}else if(randomSelect>0.5){
				sigmaScaling(populationAdults);
			}else if(randomSelect>0.25){
				tournamentSelection(populationAdults);
			}else{
				stochasticUniformSelection(populationAdults);
			}
		} else {
			if (type == 1) {
				fitnessProportionate(populationAdults);
			} else if (type == 2) {
				sigmaScaling(populationAdults);
			} else if (type == 3) {
				tournamentSelection(populationAdults);
			} else {
				stochasticUniformSelection(populationAdults);
			}
		}
	}

	public void fitnessProportionate(PopulationAdults populationAdults) {
		population.clear();
		int totalFitnessScore = 0;
		for (int i = 0; i < populationAdults.getPopulationSize(); i++) {
			totalFitnessScore += populationAdults.getPhenotypeAt(i)
					.fitnessEvaluation();
		}
		for (int i = 0; i < numberOfChildrenToCreate; i++) {
			int newParent = (int) (Math.random() * totalFitnessScore);
			int fitnessSum = 0;
			for (int j = 0; j < populationAdults.getPopulationSize(); j++) {
				fitnessSum += populationAdults.getPhenotypeAt(j)
						.fitnessEvaluation();
				if (fitnessSum >= newParent) {
					population.add(populationAdults.getPhenotypeAt(j));
					break;
				}
			}
		}
	}

	public void sigmaScaling(PopulationAdults populationAdults) {
		population.clear();
		ArrayList<Double> sigmaValues = new ArrayList<Double>();
		double standardDeviation = populationAdults.getStandardDeviation();
		
		if (standardDeviation == 0) {
			fitnessProportionate(populationAdults);
		} else {
			double sigmaValue = 0;
			for (int i = 0; i < populationAdults.getPopulationSize(); i++) {
				double expValue = 1
						+ (populationAdults.getPhenotypeAt(i).fitnessEvaluation() - populationAdults
								.getMeanFitness()) / (2 * standardDeviation);
				sigmaValues.add(expValue);
				sigmaValue += expValue;
			}
			for (int i = 0; i < numberOfChildrenToCreate; i++) {
				double newParent = Math.random() * sigmaValue;
				double sigmaSum = 0;
				for (int j = 0; j < sigmaValues.size(); j++) {
					sigmaSum += sigmaValues.get(j);
					if (sigmaSum >= newParent) {
						population.add(populationAdults.getPhenotypeAt(j));
						break;
					}
				}
			}
		}

	}

	public void tournamentSelection(PopulationAdults populationAdults) {
		population.clear();
		int groupSize = (int) (populationAdults.getPopulationSize() * 0.3);
		ArrayList<BasicPhenotype> parentGroup = new ArrayList<BasicPhenotype>();
		double randomAdultChance = 0.3;

		for (int i = 0; i < numberOfChildrenToCreate; i++) {
			parentGroup.clear();
			while (parentGroup.size() < groupSize) {
				int parent = (int) (Math.random() * (populationAdults
						.getPopulationSize()));
				if (!population.contains(populationAdults
						.getPhenotypeAt(parent))) {
					parentGroup.add((populationAdults.getPhenotypeAt(parent)));
				}
				
			}
			Collections.sort(parentGroup);
			if(Math.random()>randomAdultChance){
				population.add(populationAdults.getPhenotypeAt(populationAdults.getPopulationSize()-1));
			}else{
				int randomParent = (int) (Math.random()*parentGroup.size());
				population.add(populationAdults.getPhenotypeAt(randomParent));
			}
			

		}
	}
	
	public void  stochasticUniformSelection(PopulationAdults populationAdults) {
		population.clear();
		for (int i = 0; i < numberOfChildrenToCreate; i++) {
			int randomParent = (int) (Math.random()*populationAdults.getPopulationSize());
			population.add(populationAdults.getPhenotypeAt(randomParent));
		}
	}



	public String toString() {
		String newString = "Parent population - ";
		double meanFitness = 0;
		for (int i = 0; i < population.size(); i++) {

			meanFitness += population.get(i).fitnessEvaluation();

		}
		meanFitness = meanFitness / population.size();

		newString += "mean fitness value: " + meanFitness;
		return newString;
	}

}
