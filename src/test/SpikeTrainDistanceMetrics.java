package test;

import java.util.ArrayList;
import java.util.Scanner;

public class SpikeTrainDistanceMetrics {

	private double activationThreshold;
	private double kTimeStep;
	private ArrayList<Integer> phenotypeSpikePositions;
	private ArrayList<Integer> targetSpikePositions;
	private ArrayList<Double> phenotypeSpikeTrain;
	private ArrayList<Double> trainingSpikeTrain;
	
	public SpikeTrainDistanceMetrics(ArrayList<Double> phenotypeSpikeTrain){
		activationThreshold = 0;
		kTimeStep = 5;
		this.phenotypeSpikeTrain = phenotypeSpikeTrain;
		
		findPhenotypeSpikePosition();
		findTargetSpikePosition();
	}
	
	public void findPhenotypeSpikePosition(){
		phenotypeSpikePositions = new ArrayList<Integer>();
		phenotypeSpikePositions = findSpikePositions(phenotypeSpikePositions,phenotypeSpikeTrain);
		
	}
	
	private void readTrainingData(int trainingDataSet){
		Scanner sc;
		
	}
	
	public void findTargetSpikePosition(){
		targetSpikePositions = new ArrayList<Integer>();
		
	}
	
	private ArrayList<Integer> findSpikePositions(ArrayList<Integer> newList, ArrayList<Double> phenotypeSpikeTrain){
		
		for (int i = 0; i < phenotypeSpikeTrain.size()-kTimeStep/2; i++) {
			double maxValue = activationThreshold -1;
			double index = -1;
			for (int j = 0; j < kTimeStep; j++) {
				
				// find max value in the k-timestep 
				if (phenotypeSpikeTrain.get(j+i)> maxValue){
					 maxValue = phenotypeSpikeTrain.get(j+i);
					 index = j;
				}		 
				
			}
			
			// check if the middle value is maximum and above threshold
			int middleIndex = (int) (kTimeStep/2+1);
			System.out.println(middleIndex);
			if (index == middleIndex && maxValue > activationThreshold){
				newList.add(i+middleIndex);
			}
			
			
		}
		
		return newList;
	}
	
	
	public double spikeTimeDistanceMetrics(ArrayList<Double> neuronSpikeTrain, ArrayList<Double> targetSpikeTrain){
		
		return 0;
	}
	
	public double spikeIntervalDistanceMetrics(ArrayList<Double> neuronSpikeTrain, ArrayList<Double> targetSpikeTrain){
		
		return 0;
	}
	
	public double waveFormDistanceMetrics(ArrayList<Double> neuronSpikeTrain, ArrayList<Double> targetSpikeTrain){
		
		return 0;
	}
	
	public double spikeCountDifferancePenalty(ArrayList<Double> neuronSpikeTrain, ArrayList<Double> targetSpikeTrain){
		
		return 0;
	}
}
