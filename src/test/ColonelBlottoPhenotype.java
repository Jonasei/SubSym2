package test;

import java.util.ArrayList;

public class ColonelBlottoPhenotype extends BasicPhenotype {

	private ArrayList<Double> battlePositions;
	private ArrayList<Double> clonedBattlePositions; 
	private double normalizeFactor;
	private int battlesWon;
	private int battlesDraw;
	private int numberOfWars;
	private double strengthFactor;
	private double strenghtRemainer;
	private double redeployFactor;
	private double strategyEntropy;

	public ColonelBlottoPhenotype(Genotype genotype) {
		super(genotype);
		setUpBattleFormation();
		normalizeBattleFormation();
		calculateStrategyEntropy();
		battlesWon = 0;
		battlesDraw = 0;
		numberOfWars = 0;
		strengthFactor = 1;
		strenghtRemainer = 0.9;
		redeployFactor = 0.6;
	}
	
	public void reset(){
		battlesWon = 0;
		battlesDraw = 0;
		numberOfWars = 0;
		strengthFactor = 1;
	}


	private void setUpBattleFormation() {
		battlePositions = new ArrayList<Double>();
		normalizeFactor = 0;
		for (int i = 0; i < phenotype.length; i += 4) {
			double soldiers = 0;
			soldiers += phenotype[i] * 8;
			soldiers += phenotype[i + 1] * 4;
			soldiers += phenotype[i + 2] * 2;
			soldiers += phenotype[i + 3];
			battlePositions.add(soldiers);
			normalizeFactor += soldiers;
		}
	}

	private void normalizeBattleFormation() {
		if (normalizeFactor > 0) {
			for (int i = 0; i < battlePositions.size(); i++) {
				battlePositions.set(i,
						(battlePositions.get(i) / normalizeFactor));
			}
		}
	}
	
	private void calculateStrategyEntropy(){
		strategyEntropy = 0;
		for (int i = 0; i < battlePositions.size(); i++) {
			if(battlePositions.get(i)!=0){			
				strategyEntropy += battlePositions.get(i)*Math.log(battlePositions.get(i))/Math.log(2);
			}
		}
		strategyEntropy = strategyEntropy*-1;
	}

	@Override
	public void calculateFitnessEvaluation() {
		fitnessScore = 0;
		fitnessScore += battlesWon * 2;
		fitnessScore += battlesDraw;
			
		if(fitnessScore==battlePositions.size()*2*numberOfWars){
			Run.finished = true;
		}
	}
	
	public double getStartegyEntropy(){
		return strategyEntropy;
	}
	
	@SuppressWarnings("unchecked")
	private void cloneFormation(){
		clonedBattlePositions = (ArrayList<Double>) battlePositions.clone();
	}

	public void battleWith(ColonelBlottoPhenotype opponent) {
		strengthFactor = 1;
		cloneFormation();
		opponent.strengthFactor = 1;
		opponent.cloneFormation();
		for (int i = 0; i < battlePositions.size(); i++) {
			double selfSize = clonedBattlePositions.get(i)*strengthFactor;
			double opponentSize = opponent.clonedBattlePositions.get(i)*opponent.strengthFactor;
			
			if (opponentSize > selfSize) {
				opponent.battlesWon += 1;
				opponent.redeployTroops(opponentSize-selfSize,i);
				strengthFactor = strengthFactor*strenghtRemainer;
			} else if (opponentSize < selfSize) {
				battlesWon += 1;
				redeployTroops(selfSize-opponentSize,i);
				opponent.strengthFactor = opponent.strengthFactor*strenghtRemainer;
			} else{
				opponent.battlesDraw += 1;
				battlesDraw +=1;
			}
			
		}
		numberOfWars++;
		opponent.numberOfWars++;
	}
	
	private void redeployTroops(double troops,int battleNumber){
		
		int battlesLeft = clonedBattlePositions.size() - battleNumber;
		double troopPerBattle = (troops/(double)battlesLeft)*redeployFactor;
		
		for (int i = battleNumber+1; i < clonedBattlePositions.size(); i++) {
			clonedBattlePositions.set(i, clonedBattlePositions.get(i)+troopPerBattle);
		}
	}
	
	
	public String toString(){
		String newString = "";
		for (int i = 0; i < battlePositions.size(); i++) {
			newString += battlePositions.get(i);
			if(i<battlePositions.size()-1){
				newString += ",";
			}
		}
		
		return newString;
	}

	@Override
	public int getProblemId() {
		return 2;
	}



	@Override
	public double getMaxFitness() {
		return battlePositions.size()*2*numberOfWars;
	}

}
