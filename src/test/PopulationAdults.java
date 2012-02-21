package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class PopulationAdults extends Population {
	private int populationMaxSize;
	private double meanFitness;
	private double maxFitness;
	private double avrageStrategyEntropy;
	private double standardDeviation;
	private BufferedWriter bf;
	private int bestIndex;

	public PopulationAdults(int size) {
		population = new ArrayList<BasicPhenotype>();
		populationMaxSize = size;
		try {
			bf = new BufferedWriter(new FileWriter("output.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Select the type of protocol to select children to adult.
	 * <br>Random - type = 0
	 * <br>Over production - type = 1
	 * <br>Full generational replacement - type = 2
	 * <br>Generational mixing - type = 3
	 * @param childrenPopulation
	 * @param type
	 */
	public void adultSelection(PopulationChildren childrenPopulation, int type) {
		if (type == 0) {
			if (Math.random() >= 0.7) {
				if (childrenPopulation.getPopulationSize() > populationMaxSize) {
					overProduction(childrenPopulation);
				} else {
					fullGenerationalReplacement(childrenPopulation);
				}
			} else {
				generationalMixing(childrenPopulation);
			}
		} else {
			if(type==1 ){
				overProduction(childrenPopulation);
			}else if(type==2){
				fullGenerationalReplacement(childrenPopulation);
			}else{
				generationalMixing(childrenPopulation);
			}
		}
		calculateFitness();
		calculateStandardDeviation();
		if(population.get(0).getProblemId()==2){
			calculateAvrageStrategyEntropy();
			writeToFilePartB();
		}else{			
			writeToFile();
		}
	}

	public void fullGenerationalReplacement(
			PopulationChildren childrenPopulation) {
		population.clear();
		population = childrenPopulation.getAllChildren();
		childrenPopulation.removeAllChildren();
		Collections.sort(population);
	}

	public void overProduction(PopulationChildren childrenPopulation) {
		population.clear();
		population = childrenPopulation
				.getNumberedPopulation(populationMaxSize);
		childrenPopulation.removeAllChildren();
		Collections.sort(population);
	}

	public void generationalMixing(PopulationChildren childrenPopulation) {
		int childrenSize = 0;
		if(childrenPopulation.getPopulationSize()>populationMaxSize){
			childrenSize = populationMaxSize;
		}else{
			childrenSize = childrenPopulation.getPopulationSize();
		}
		ArrayList<BasicPhenotype> newAdults = childrenPopulation.getNumberedPopulation(childrenSize);
		for (int i = 0; i < newAdults.size(); i++) {
			population.add(newAdults.get(i));
		}		
		
		childrenPopulation.removeAllChildren();
		
		generationalMixingHelper();
		
		Collections.sort(population);
		
		int adultsToRemove = population.size() - populationMaxSize;
			if(adultsToRemove>0){
			for (int i = 0; i < childrenSize; i++) {
				population.remove(0);
			}
		}
		
	}
	
	

	public int getPopulationMaxSize() {
		return populationMaxSize;
	}

	
	public double getMeanFitness(){
		return meanFitness;
	}
	
	public void calculateFitness(){
		meanFitness = 0;
		maxFitness = 0;
		bestIndex = 0;
		for (int i = 0; i < population.size(); i++) {
			meanFitness += population.get(i).fitnessEvaluation();
			if (population.get(i).fitnessEvaluation() > maxFitness){
				maxFitness = population.get(i).fitnessEvaluation();
				bestIndex = i;
			}
		}
		System.out.println(population.get(bestIndex));
		meanFitness = meanFitness / population.size();
		
		// normalize
		meanFitness = meanFitness/population.get(0).getMaxFitness();
		maxFitness = maxFitness/population.get(0).getMaxFitness();
		
	}
	
	private void generationalMixingHelper(){
		if(population.get(0).getProblemId()==2){
			for (int i = 0; i < population.size(); i++) {
				ColonelBlottoPhenotype generalOne = (ColonelBlottoPhenotype) population.get(i);
				generalOne.reset();
			}
			for (int i = 0; i < population.size(); i++) {
				ColonelBlottoPhenotype generalOne = (ColonelBlottoPhenotype) population.get(i);
				for (int j = i+1; j < population.size(); j++) {
					generalOne.battleWith((ColonelBlottoPhenotype) population.get(j));
				}
				generalOne.calculateFitnessEvaluation();
			}
		}
	}
	
	public void calculateAvrageStrategyEntropy(){
		avrageStrategyEntropy = 0;
		for (int i = 0; i < population.size(); i++) {
			ColonelBlottoPhenotype phenotype = (ColonelBlottoPhenotype) population.get(i);
			avrageStrategyEntropy += phenotype.getStartegyEntropy();	
		}
		avrageStrategyEntropy = avrageStrategyEntropy / population.size();
	}
	
	public void calculateStandardDeviation(){
		standardDeviation = 0;
		for (int i = 0; i < population.size(); i++) {
			double temp = population.get(i).fitnessEvaluation()/population.get(0).getMaxFitness()
					- meanFitness;
			standardDeviation += Math.pow(temp, 2);
		}
		standardDeviation = Math.sqrt(standardDeviation/ population.size());

	}
	
	public double getStandardDeviation(){
		return standardDeviation;
	}
	
	public void writeToFile(){
		try {
			bf.write(meanFitness+","+maxFitness+","+standardDeviation+"\n");
			bf.flush();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void writeToFilePartB(){
		try {
			bf.write(meanFitness+","+maxFitness+","+standardDeviation+","+avrageStrategyEntropy+","+population.get(bestIndex)+"\n");
			bf.flush();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


	public String toString() {
		String newString = "Adult population - ";
		newString += "mean fitness value: " + meanFitness;
		newString += ", max fitness value: " + maxFitness;
		newString += ", standard deviation: " + standardDeviation;
		if(population.get(0).getProblemId()==2){
			newString += ", avrage strategy entropy: " + avrageStrategyEntropy;
		}
		return newString;
	}

}
