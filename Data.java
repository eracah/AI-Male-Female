import java.io.*;
import java.util.*;

public class Data
{
	public int[][] image2DArray = new int[120][128];
	public double sex;

	public static double FEMALE = 0.1;
	public static double MALE = 0.9;

	public Data()
	{
		int a = 3 + 3;
	}
	public Data(double s, int[][] array)
	{
		sex = s;
		image2DArray = array;
	}

	public Data(double s, String file)
	{
    sex = s;
		try
    {
      FileInputStream fstream = new FileInputStream(file);
            System.out.println("file: " + file);

      BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
      String strLine;
      //Read File Line By Line
      int row = 0;
      int col = 0;
      int chunk = 0;
      while ((strLine = br.readLine()) != null)
      {
        // System.out.println("strLine: " + strLine);

        String[] strArray = strLine.split(" ");
        int[] intArray = new int[strArray.length];
        
        for(int j = 0; j < strArray.length; j++) 
        {
            intArray[j] = Integer.parseInt(strArray[j]);
        }

        if(chunk >= 8)
        {
          chunk = 0;
          row++;
        }

        if(chunk >= 0)
        {
          chunk++;
        }

        for(int k = 0; k < 16; k++)
        {
          image2DArray[row][col] = intArray[k];
          col++;
        }

        if(col == 128)
        {
          col = 0;
        }
       
       }
      
      }
  

      catch (Exception e)
        {
          //Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
  }
}
