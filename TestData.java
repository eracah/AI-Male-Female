import java.io.*;
import java.util.*;

public class TestData extends Data
{
	String fileName = new String();
	
	public TestData(String file)
	{
		sex = -1;
		fileName = file;
		try
		{
		  FileInputStream fstream = new FileInputStream(file);
		  //System.out.println("file: " + file); // file used to create object

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

	public void print()
	{
		// for(int i = 0; i < 120; i++)
  //    	{
  //      		System.out.println();
  //      		for(int j = 0; j < 128; j++)
  //      		{
  //        		System.out.print(image2DArray[i][j] + " ");
  //      		}
  //   	}
    	if(sex == -1)
    	{
    		System.out.println("\nFile: " + fileName);
    		System.out.println("Image has not been processed.");
    	}

    	else
    	{
    		System.out.println("File: " + fileName);
    		System.out.println("The sex was determined to be: " + ((sex == 0.1) ? "Female" : "Male"));
    	}
	}

}