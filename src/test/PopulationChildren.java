package test;

import java.util.ArrayList;
import java.util.Collections;

public class PopulationChildren extends Population{
	
	
	public PopulationChildren(){
		population = new ArrayList<BasicPhenotype>();
	}
	
	public void addChild(BasicPhenotype phenotype){
		population.add(phenotype);
	}
	
	public void removeAllChildren(){
		population.clear();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<BasicPhenotype> getAllChildren(){
		return  (ArrayList<BasicPhenotype>) population.clone();
	}
	
	public ArrayList<BasicPhenotype> getNumberedPopulation(int number){
		Collections.sort(population);
		ArrayList<BasicPhenotype> newPopulation = new ArrayList<BasicPhenotype>();
		
		for (int i = 0; i < number; i++) {		
			newPopulation.add(population.remove(population.size()-1));
			
		}
		
		return  newPopulation;
	}
	
	
	public String toString(){
		String newString = "Children population - ";
		double meanFitness = 0;
		for (int i = 0; i < population.size(); i++) {
			
			meanFitness += population.get(i).fitnessEvaluation();
			
		}
		meanFitness = meanFitness/population.size();
		
		newString += "mean fitness value: " + meanFitness;
		return newString;
	}

}
