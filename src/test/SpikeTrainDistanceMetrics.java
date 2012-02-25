package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpikeTrainDistanceMetrics {

	private double activationThreshold;
	private double kTimeStep;
	private ArrayList<Integer> phenotypeSpikePositions;
	private ArrayList<Integer> targetSpikePositions;
	private ArrayList<Double> trainingSpikeTrain;
	
	public SpikeTrainDistanceMetrics(){
		activationThreshold = 0;
		kTimeStep = 5;
		
		readTrainingData(1);
		findTargetSpikePosition();
	}
	
	public void findPhenotypeSpikePosition(ArrayList<Double> phenotypeSpikeTrain){
		phenotypeSpikePositions = new ArrayList<Integer>();
		phenotypeSpikePositions = findSpikePositions(phenotypeSpikePositions,phenotypeSpikeTrain);
		
	}
	
	private void readTrainingData(int trainingDataSet){
		trainingSpikeTrain = new ArrayList<Double>();
		Scanner sc = new Scanner("");
		try {
			if (trainingDataSet == 1) {	
				sc = new Scanner(new File("src/trainingData/izzy-train1.dat"));
			}else if (trainingDataSet == 2) {
				sc = new Scanner(new File("src/trainingData/izzy-train2.dat"));
			}else if (trainingDataSet == 3) {
				sc = new Scanner(new File("src/trainingData/izzy-train3.dat"));
			}else if (trainingDataSet == 4) {
				sc = new Scanner(new File("src/trainingData/izzy-train4.dat"));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (sc.hasNext()) {
			trainingSpikeTrain.add(sc.nextDouble());
		}
		
	}
	
	public void findTargetSpikePosition(){
		targetSpikePositions = new ArrayList<Integer>();
		targetSpikePositions = findSpikePositions(targetSpikePositions,trainingSpikeTrain);
	}
	
	private ArrayList<Integer> findSpikePositions(ArrayList<Integer> newList, ArrayList<Double> phenotypeSpikeTrain){
		int trainLength = (int) (phenotypeSpikeTrain.size()-kTimeStep+1);
		for (int i = 0; i < trainLength; i++) {
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
	
	public double getFintesss(){
		return 0;
	}
	
	public String toString(){
		String newString ="";
		newString +="spikeTrainPositions\n";
		for (int i = 0; i < phenotypeSpikePositions.size(); i++) {
			newString += phenotypeSpikePositions.get(i)+", ";
		}
		
		return newString;
	}
}
