package test;

import java.util.BitSet;

public class Genotype {

	private BitSet genotype;
	private int bitSize;
	private double mutateRate;
	
	public Genotype(int size, double mutateRate){
		genotype = new BitSet(size);
		bitSize = size;
		this.mutateRate = 1 - mutateRate;
		createRandomGenotype();
	}
	
	public Genotype(Genotype oldGenotype){
		genotype = (BitSet) oldGenotype.genotype.clone();
		bitSize  = oldGenotype.bitSize;
		mutateRate = oldGenotype.mutateRate;
	}
	
	private void createRandomGenotype(){
		for (int i = 0; i < bitSize; i++) {
			if (Math.random() >= 0.5){
				genotype.set(i);
			}
		}
		
	}
	
	public int getBitAt(int index){
		if (genotype.get(index)) {
			return 1;
		}else{
			return 0;
		}
	}
	
	public void setBitAt(int bitIndex, int value){
		if(value==1){
			genotype.set(bitIndex, true);
		}else{
			genotype.set(bitIndex, false);
		}	
	}
	
	public int getSize(){
		return bitSize;
	}
	
	public void mutate(){
		for (int i = 0; i < bitSize; i++) {
			if(Math.random()>=mutateRate){
				genotype.flip(i);
			}
			
		}
	}
	
	
	
	public String toString(){
		String stringToReturn ="";
		for (int i = 0; i < bitSize; i++) {
			if (genotype.get(i)) {
				stringToReturn += 1;
			}else{
				stringToReturn += 0;
			}
		}
		return stringToReturn;
	}
}
