//import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{	
    int[] imageDim = {120,128};
    NeuralNetwork NN = new NeuralNetwork(3,1,imageDim);
    Data d = new Data();
    boolean test = false;
     boolean train = false;
   
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
        test = true;
      }

      else if(args[i].equalsIgnoreCase("-train"))
      {
        System.out.println("call train()");
        train = true;
      }

  		else
      { 
          try
    		  {

                d = new Data(0.1, args[i]);
                System.out.println("created Data object");
                for(int rows = 0; rows < 120; rows++)
                { 
                  //System.out.println();
                  for(int cols = 0; cols < 128; cols++)
                  {
                    //System.out.print(d.image2DArray[rows][cols]);
                    //System.out.print(" ");
                  }
                }
                //System.out.println();
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
    Data[] dataArray = new Data[200];
    for (int j = 0; j < 200; j ++)
    {
      dataArray[j] = d;
    }
    if(train)
    {
      NN.trainNetwork(dataArray);
    }
    
    if(test)
      System.out.println(NN.passThroughNetwork(d));
    
 	}
}



 



                
