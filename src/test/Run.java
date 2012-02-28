package test;

import java.util.Scanner;

public class Run {

	public static int ONEMAX = 0;
	public static int ADVANCEDONEMAX = 1;
	public static int COLONELBLOTTO = 2;
	public static int NEURONSPIKETRAIN = 3;
	
	public static int RANDOM = 0;
	public static int OVERPRODUCTION = 1;
	public static int FULLGENERATIONALREPLACEMENT = 2;
	public static int GENERATIONALMIXING = 3;
	
	public static int FITNESSPROPORTIONATE = 1;
	public static int SIGMASCALING = 2;
	public static int TOURNAMENTSELECTION = 3;
	public static int STOCHASTICUNIFORMSELECTION = 4;
	
	public static int SPIKEDISTANCEMETRIC = 1;
	public static int SPIKEINTERVALDISTANCEMETRIC = 2;
	public static int WAVEFORMDISTANCEMETRIC = 3;
	
	
	
	
	private int generationPool = 200;
	private int adultPool = 40;
	private int fitnessEvaluationMethod = SPIKEDISTANCEMETRIC;
	private int adultProtocol = GENERATIONALMIXING;
	private int parentProtocol = SIGMASCALING;
	private int targetDataset = 1;
	private double mutateRate = 0.05;

	
	
	private int bitSize = 50;
	private int problemId = NEURONSPIKETRAIN;
	static boolean finished = false;
	static double BESTOVERALLFITNESS = 0;
	private static int generationNumber = 0;
	private int numberOfGenerations = 1000;
	

	public static int getGenerationNumber() {
		return generationNumber;
	}

	public Run() {
		init();
		simulateEA();
	}

	public void init() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome");
		System.out.println("Enter training dataset (1-4)");
		targetDataset = sc.nextInt();
		System.out.println("Enter  fitness evaluation method\nSpike distance metric = 1\nSpike interval distance metric = 2\nWaveform distance metric = 3");
		fitnessEvaluationMethod = sc.nextInt();
		System.out.println("Enter generations to run");
		numberOfGenerations = sc.nextInt();

	}

	public void simulateEA() {

		// Declare populations
		PopulationChildren populationChildren = new PopulationChildren();
		PopulationAdults populationAdults = new PopulationAdults(adultPool);
		PopulationParent populationParent = new PopulationParent(generationPool);
		// Declare helper classes
		Development development = new Development(populationChildren, generationPool, bitSize, mutateRate, problemId);
		FitnessTesting fitnessTesting = new FitnessTesting(populationChildren, problemId,fitnessEvaluationMethod,targetDataset);
		Reproduction reproduction = new Reproduction(populationParent);

		while (!finished && generationNumber< numberOfGenerations) {

			generationNumber++;
			System.out.println("\nGeneration: " + generationNumber);
			if (generationNumber == 1) {
				development.createFirstGeneration();
			} else {
				development.develop(reproduction.getGenotypes());
			}

			fitnessTesting.evaluateFitness();

			populationAdults.adultSelection(populationChildren, adultProtocol);

			populationParent.parentSelection(populationAdults, parentProtocol);

			reproduction.run();

			System.out.println(populationAdults);

		}
		System.out.println("\nDone at generation: " + generationNumber);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Run();

	}

}
