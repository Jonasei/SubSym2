package test;

import java.util.ArrayList;

public class SpikingNeuronPhenotype extends BasicPhenotype {

	private double a, b, c, d, k;
	private double u, v;
	private double tau;
	private double I;

	private ArrayList<Double> spikeTrain;
	private ArrayList<Integer>spikeTrainPositions;

	public SpikingNeuronPhenotype(Genotype genotype) {
		super(genotype);
		tau = 10;
		I = 10;
		u = 0;
		v = -60;
		
		createNeuronValues();
		createSpikeTrain(1000);
	}

	public void createNeuronValues() {

		for (int i = 0; i < phenotype.length; i += 10) {
			double value = 0;
			
			value= convertBinaryToDecimal(i);
			
			if(i <10){
				a = calculateParameter(0.001, 0.2, value);
			}
			else if(i <20){
				b = calculateParameter(0.01, 0.3, value);
			}
			else if(i <30){
				c = calculateParameter(-80, -30, value);
			}
			else if(i <40){
				d = calculateParameter(0.1, 10, value);
			}
			else{
				k = calculateParameter(0.01, 1.0, value);
			}
				
		}
	}
	
	public double calculateParameter(double lowerBound, double higherBound, double value){
		double parameter = 0;
		int maxValue = 1023;
		parameter = lowerBound + (value / maxValue) * (higherBound - lowerBound);
		return parameter;
	}
	
	public double convertBinaryToDecimal(int i){
		double decimalValue = 0;
		decimalValue += phenotype[i]* 512;
		decimalValue += phenotype[i+1]* 256;
		decimalValue += phenotype[i+2]* 128;
		decimalValue += phenotype[i+3]* 64;
		decimalValue += phenotype[i+4]* 32;
		decimalValue += phenotype[i+5]* 16;
		decimalValue += phenotype[i+6]* 8;
		decimalValue += phenotype[i+7]* 4;
		decimalValue += phenotype[i+8]* 2;
		decimalValue += phenotype[i+9];
		return decimalValue;
	}

	public void createSpikeTrain(int numberOfIterations) {
		spikeTrain = new ArrayList<Double>();
		for (int i = 0; i < numberOfIterations; i++) {
		
			spikeTrain.add(v);
			checkThreshold();
			v = (1/tau)*(k*Math.pow(v, 2)+5*v+140-u+I);
			u = (a/tau)*(b*v-u);
		
		}
		
	}
	

	public void checkThreshold(){
		if(v >= 35){
			v = c;
			u = u + d;
		}
	}
	@Override
	public void calculateFitnessEvaluation() {
		
	}
	
	public ArrayList<Double> getSpikeTrain(){
		return spikeTrain;
	}
	
	public void setSpikeTrainPositions(ArrayList<Integer>spikeTrainPositions){
		spikeTrainPositions = new ArrayList<Integer>();
		this.spikeTrainPositions = (ArrayList<Integer>) spikeTrainPositions.clone();
	}
	
	public void setFitness(double fintessValue){
		fitnessScore = fintessValue;
	}

	@Override
	public int getProblemId() {
		return 3;
	}

	@Override
	public double getMaxFitness() {
		return 1;
	}
	
	public String getSpikes(){
		String newString ="\nspikeTrainPositions: ";
		newString += spikeTrainPositions.size();
		newString +="\n";
		for (int i = 0; i < spikeTrainPositions.size(); i++) {
			newString += spikeTrainPositions.get(i)+", ";
		}
		return newString;
	}

	
	public String toString(){
		String output = "";
		for (int i = 0; i < spikeTrain.size(); i++) {
			output += spikeTrain.get(i) + ";";
		}
		output += "\n"+ a + ", " + b + ", " + c + ", " + d + ", " + k;
		return output;
	}
}
