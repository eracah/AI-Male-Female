import java.lang.Math;
public class NeuralNetwork
{
	HiddenUnit[] hiddenUnitArray;
	OutputUnit outputUnit;
	double[] hiddenOutputs, hiddenErrors;
	double outputError;
	int numberOfHiddenUnits, numberOfOutputUnits;
	double learningRate = 0.3; 
	boolean terminate;


	public NeuralNetwork(int hiddenUnits, int outputUnits, int[] inputDimensions)
	{
		numberOfHiddenUnits = hiddenUnits;
		numberOfOutputUnits = outputUnits;
		hiddenUnitArray = new HiddenUnit[numberOfHiddenUnits];

		for(int i = 0; i < numberOfHiddenUnits; i ++)
			hiddenUnitArray[i] = new HiddenUnit(inputDimensions);
		
		outputUnit = new OutputUnit(numberOfHiddenUnits);
		hiddenOutputs = new double[numberOfHiddenUnits];
		hiddenErrors = new double[numberOfHiddenUnits];
	}


	//randomly initializes all the output weights to numbers b/w lowrange and endrange
	public void initializeOutputWeightsToBetween(double lowRange, double endRange)
	{
		for (int j = 0 ; j < outputUnit.length; j++)
				outputUnit.changeWeight(j, lowRange + Math.random() * (endRange - lowRange)); 


	}

	//
	public void trainNetwork(Data[] trainingSet) 
	{

		terminate = false;
		//initialize all output weights to random numbers between -0.5 and 0.5.
		//keep hidden weights the same
		initializeOutputWeightsToBetween(-0.05, 0.05);
		//System.out.println("initial");
		//outputUnit.printWeights();

		while(!terminate)
		{
			//System.out.println("hello!");
			for(int imageIndex = 0; imageIndex < trainingSet.length; imageIndex++)
			{
				Data currentImage = trainingSet[imageIndex];
				//propagate forward through the network
				double output = passThroughNetwork(currentImage);

				//for each output unit (just one) calculate error
				outputError = output * (1 - output) * (currentImage.sex - output);
				

				//for each hidden unit calculate its error
				for(int hiddenIndex = 0; hiddenIndex < numberOfHiddenUnits; hiddenIndex++)
					hiddenErrors[hiddenIndex] = hiddenOutputs[hiddenIndex] * (1 - hiddenOutputs[hiddenIndex]) * 
																			outputError * outputUnit.weights[hiddenIndex];
				//update each network weight wji
				//for hidden units
				for(int hiddenIndex = 0; hiddenIndex < numberOfHiddenUnits; hiddenIndex++)
					for(int i = 0; i < hiddenUnitArray[hiddenIndex].height; i++)
						for(int j = 0; j < hiddenUnitArray[hiddenIndex].width; j++)
							hiddenUnitArray[hiddenIndex].weights[i][j] += learningRate * hiddenErrors[hiddenIndex] * 
																													currentImage.image2DArray[i][j];
				//for(int hiddenIndex = 0; hiddenIndex < numberOfHiddenUnits; hiddenIndex++)
					//hiddenUnitArray[hiddenIndex].printWeights();

				//for output units
				for(int weightIndex = 0; weightIndex < outputUnit.length; weightIndex++)
					outputUnit.weights[weightIndex] += learningRate * outputError * hiddenOutputs[weightIndex];
				
				//outputUnit.printWeights();




			}
			terminate = true;
			
		}
		
		

	}
	public double calcSigmoid(double net)
	{
		return 1 / (1 + Math.exp(-net));
	}

	public double passThroughNetwork(Data imageDataObject)
	{
		for (int i = 0; i < numberOfHiddenUnits; i++)
			hiddenOutputs[i] = hiddenUnitArray[i].getOutput(imageDataObject.image2DArray);
		return outputUnit.getOutput(hiddenOutputs);

	}

	public int gender(double output)
	{
		if(output < 0.5)
			return 0; //female
		else
			return 1; //male
	}

	public double testImages(Data[] testImages, int length)
	{
		int output;
		double numberCorrect = 0.0;
		for(int i = 0; i < length; i++)
		{
			if (gender(passThroughNetwork(testImages[i])) == gender(testImages[i].sex))
				numberCorrect++;

		}
		return numberCorrect / length;
	}

	public TestData[] testTest(TestData[] testDataArray, int length)
	{
		for(int i = 0; i < length; i++)
		{
			testDataArray[i].sex = passThroughNetwork(testDataArray[i]);
		}
		return testDataArray;
	}


	public final class HiddenUnit
	{
			double[][] weights;
			int output;
			int height;
			int width;

			public HiddenUnit(int[] inputDim)
			{
				height = inputDim[0];
				width = inputDim[1];
				weights = new double[height][width];
			}

			public double getOutput(int[][] imageInput)
			{
				double output = 0;
				for(int i = 0; i < height; i++)
					for(int j = 0; j < width; j++)
						output += weights[i][j] * imageInput[i][j];
			
				return calcSigmoid(output);
			}
			public void changeWeight(int height, int width, double newWeightValue)
			{
				weights[height][width] = newWeightValue;
			}
			public void printWeights()
			{
				for(int i = 0; i < height; i++)
					for(int j = 0; j < width; j++)
						System.out.println(weights[i][j]);
			}

		}

		public class OutputUnit
		{
			double [] weights;
			int output;
			int length;

			public OutputUnit(int inputLength)
			{
				length = inputLength;
				weights = new double[length];
			}
			public double getOutput(double [] hiddenUnitOutputs)
			{
				double output = 0;
				for(int i = 0; i < length; i++)
					output += weights[i] * hiddenUnitOutputs[i];
				return calcSigmoid(output);
			}
			public void changeWeight(int weightIndex, double newWeightValue)
			{
				weights[weightIndex] = newWeightValue;
			}
			public void printWeights()
			{
				for (int j = 0 ; j < length; j++)
					System.out.println(weights[j]);
			}

		}

		
}