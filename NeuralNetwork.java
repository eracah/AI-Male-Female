
public class NeuralNetwork
{
	HiddenUnit[] hiddenUnitArray;
	OutputUnit[] outputUnitArray;
	public NeuralNetwork(int numberOfHiddenUnits, int numberOfOutputUnits, int[] inputDimensions)
	{
		hiddenUnitArray = new HiddenUnit[numberOfHiddenUnits];
		for(int i = 0; i < numberOfHiddenUnits; i ++)
		{
			hiddenUnitArray[i] = new HiddenUnit(inputDimensions);
		}
		
		outputUnitArray = new outputUnitArray[numberOfOutPutUnits];
		for(int i = 0; i < numberOfOutputUnits; i ++)
		{
			outputUnitArray[i] = new OutputUnit(numberOfHiddenUnits);
		}


	}
	public double test(Data dataObject)
	{

	}
	public double test(Data[])
	public final class HiddenUnit
	{
			int[][] weights;
			int output;

			public HiddenUnit(int[] inputDim)
			{
				int height = inputDim[0];
				int width = inputDim[1];
				weights = new int[height][width];
			}

			public int getOutput()
			{
				for(int i = 0; i < weights.length; i++)
					for(int j = 0; j < weights[0].length; j++)
						System.out.println(weights[i][j]);
				return 0;
			}

		}

		public class OutputUnit
		{
			int [] weights;
			int output;

			public OutputUnit(int[] inputDim)
			{
				
				weights = new int[inputDim];
			}

		}

		
}