
public class NeuralNetwork
{
	HiddenUnit[] hiddenUnitArray;
	OutputUnit[] outputUnitArray;
	double[] outputsHidden;
	double[] outputsOutput;
	int numberOfHiddenUnits;
	int numberOfOutputUnits;

	public NeuralNetwork(int hiddenUnits, int outputUnits, int[] inputDimensions)
	{
		numberOfHiddenUnits = hiddenUnits;
		numberOfOutputUnits = outputUnits;

		hiddenUnitArray = new HiddenUnit[numberOfHiddenUnits];
		for(int i = 0; i < numberOfHiddenUnits; i ++)
		{
			hiddenUnitArray[i] = new HiddenUnit(inputDimensions);
		}
		
		outputUnitArray = new OutputUnit[numberOfOutputUnits];
		for(int i = 0; i < numberOfOutputUnits; i ++)
		{
			outputUnitArray[i] = new OutputUnit(numberOfHiddenUnits);
		}

		outputsHidden = new double[numberOfHiddenUnits];
		outputsOutput = new double[numberOfOutputUnits];
	}
	public double passThroughNetwork(Data imageDataObject)
	{
		for (int i = 0; i < numberOfHiddenUnits; i++)
			outputsHidden[i] = hiddenUnitArray[i].getOutput(imageDataObject.image2DArray);
		
		//for (int j = 0; j < numberOfOutputUnits; j++)
			//outputOutputs[j] = outputUnitArray[j].getOutput(outputsHidden);


		return outputUnitArray[0].getOutput(outputsHidden);

	}


	public final class HiddenUnit
	{
			int[][] weights;
			int output;
			int height;
			int width;

			public HiddenUnit(int[] inputDim)
			{
				height = inputDim[0];
				width = inputDim[1];
				weights = new int[height][width];
			}

			public double getOutput(int[][] imageInput)
			{
				int output = 0;
				for(int i = 0; i < height; i++)
					for(int j = 0; j < width; j++)
						output += weights[i][j] * imageInput[i][j];
				return output;
			}

		}

		public class OutputUnit
		{
			int [] weights;
			int output;
			int length;

			public OutputUnit(int inputLength)
			{
				length = inputLength;
				weights = new int[length];
			}
			public double getOutput(double [] hiddenUnitOutputs)
			{
				int output = 0;
				for(int i = 0; i < length; i++)
					output += weights[i] * hiddenUnitOutputs[i];
				return output;
			}

		}

		
}