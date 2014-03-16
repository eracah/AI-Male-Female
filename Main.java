import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{	
		if(args.length < 1) 
		{
  			System.err.println("Invalid command line, at least one argument required");
  			System.exit(1);
		}
    int i = 0;
    while(i < args.length)
    {
      if(args[i].equalsIgnoreCase("-test"))
      {
        System.out.println("call test()");
      }

      else if(args[i].equalsIgnoreCase("-train"))
      {
        System.out.println("call train()");
      }

  		else
      { 
          try
    		  {
                // Open the file that is the first 
                // command line parameter
                FileInputStream fstream = new FileInputStream(args[i]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                //Read File Line By Line
                int[][] image = new int[120][128];
                int row = 0;
                int col = 0;
                int chunk = 0;
                while ((strLine = br.readLine()) != null)
                {
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

                  else if(chunk > 0)
                  {
                    chunk++;
                  }

                  for(int k = 0; k < 16; k++)
                  {
                    image[row][col] = intArray[k];
                    col++;
                  }

                  if(col == 128)
                  {
                    col = 0;
                  }

                }
                //Close the input stream
                //in.close();
                Data d = new Data(0.1, image);
                for(int rows = 0; row < 120; row++)
                { System.out.println();
                  for(int cols = 0; cols < 128; cols++)
                  {
                    System.out.print(d.image2DArray[rows][cols]);
                    System.out.print(" ");
                  }
                }
                System.out.println();
                System.out.println("Sex is: " + d.sex);

          }


        catch (Exception e)
        {
          //Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
      }
      i++;
    }
 	}
}
