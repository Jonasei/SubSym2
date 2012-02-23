package test;

import java.util.ArrayList;

public class SpikingNeuronPhenotype extends BasicPhenotype {

	private double a, b, c, d, k;
	private double u, v;
	private double tau;
	private double I;

	private ArrayList<Double> spikeTrain;

	public SpikingNeuronPhenotype(Genotype genotype) {
		super(genotype);
		tau = 10;
		I = 10;
	}

	public void createNeuronValues() {
		u = 0;
		v = -60;

		for (int i = 0; i < phenotype.length; i += 10) {
			double temp = 0;
			temp += phenotype[i]* 512;
			temp += phenotype[i+1]* 256;
			temp += phenotype[i+2]* 128;
			temp += phenotype[i+3]* 64;
			temp += phenotype[i+4]* 32;
			temp += phenotype[i+5]* 16;
			temp += phenotype[i+6]* 8;
			temp += phenotype[i+7]* 4;
			temp += phenotype[i+8]* 2;
			temp += phenotype[i+9];
			
			if(i <10){
				a = 0.001 + (temp / 1023 )* (0.2 - 0.001);
			}
			else if(i <20){
				b = 0.01 + (temp /1023) * (0.3 - 0.01);
			}
			else if(i <30){
				c = -80 + (temp / 1023) * (-30 + 80);
			}
			else if(i <40){
				d = 0.1 + (temp / 1023) * (10 - 0.1);
			}
			else{
				k = 0.01 + (temp / 1023) * (1.0 - 0.01);
			}
				
		}
	}

	public void createSpikeTrain(int numberOfIteations) {
		spikeTrain = new ArrayList<Double>();
		for (int i = 0; i < numberOfIteations; i++) {
			spikeTrain.add(v);
			v = (1/tau)*(k*Math.pow(v, 2)+5*v+140-u+I);
			u = (a/tau)*(b*v-u);
		}
	}

	@Override
	public void calculateFitnessEvaluation() {

	}

	@Override
	public int getProblemId() {
		return 3;
	}

	@Override
	public double getMaxFitness() {
		return 0;
	}

}
