
public class Data
{
	public int image2DArray[][];
	public double sex;

	public static double FEMALE = 0.1;
	public static double MALE = 0.9;

	public Data(double s, int[][] array)
	{
		sex = s;
		image2DArray = array;
	}
}