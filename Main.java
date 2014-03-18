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
      if(args[i].equalsIgnoreCase("-train"))
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

        System.out.println("called train()");
        try
          {
            ArrayList<String> femaleFiles = new ArrayList<String>();
            ArrayList<String> maleFiles = new ArrayList<String>();
            femaleFiles = getFileNames("Female"); //list of female image file names
            // Data[] femaleData = new Data[femaleFiles.size()]; //Array of female Data objects
            ArrayList<Data> femaleData = new ArrayList<Data>();

            //int fIndex = 0;
            for(String name : femaleFiles)
            {
              Data f = new Data(0.1, name);
              femaleData.add(f);
              // f.print();
              // System.out.println();
              // fIndex++;
            }

            //int mIndex = 0;
            maleFiles = getFileNames("Male");
            //Data[] maleData = new Data[maleFiles.size()]; //Array of male Data objects
            ArrayList<Data> maleData = new ArrayList<Data>();
            for(String name : maleFiles)
            {
              Data m = new Data(0.9, maleFiles.get(0));
              maleData.add(m);
              // m.print();
              // System.out.println();
              //mIndex++;
            }

            ArrayList<ArrayList<Data>> shuffledImages = new ArrayList<ArrayList<Data>>();
            shuffledImages = shuffle(maleData, femaleData);


            int setNum = 0;
            for(ArrayList<Data> s : shuffledImages)
            {
              System.out.println("\nset: " + setNum);
              for(Data d : s)
              {
                d.print();
              }
              setNum++;
            }
                
          }

          catch (Exception e)
        {
          //Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
      }

      else if(args[i].equalsIgnoreCase("-test"))
      {
        System.out.println("called test()");
        ArrayList<String> testFiles = getFileNames("Test");
        ArrayList<TestData> tData = new ArrayList<TestData>();
        for(String s : testFiles)
        {
          TestData t = new TestData(s);
          tData.add(t);
        }

        for(TestData t : tData)
        {
          t.print();
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

  public static ArrayList<String> getFileNames(String folderName)
  {
    ArrayList<String> fileNames = new ArrayList<String>();
    final File folder = new File("./" + folderName);  // go to specified folder
    System.out.println("folder: " + folder);
    
    for (final File fileEntry : folder.listFiles())   // get text files from that folder
    {
      String fileName = fileEntry.getName();
      int length = fileName.length();
      if(length > 4)
      {
        String substr = fileName.substring(length - 4, length);

        if(substr.equals(".txt"))  //make sure they're text files
        {
          fileNames.add("./" + folderName + "/" + fileName);
          //System.out.println("./" + folderName + "/" + fileName); //prints list of txt files in folder

        }

      }

    }
    return fileNames;
  }

  public static ArrayList<ArrayList<Data>> shuffle(ArrayList<Data> m, ArrayList<Data> f)
  {
    System.out.println("Entered shuffle function.");
    ArrayList<ArrayList<Data>> shuffled = new ArrayList<ArrayList<Data>>();
    ArrayList<Data> set0 = new ArrayList<Data>();
    ArrayList<Data> set1 = new ArrayList<Data>();
    ArrayList<Data> set2 = new ArrayList<Data>();
    ArrayList<Data> set3 = new ArrayList<Data>();
    ArrayList<Data> set4 = new ArrayList<Data>();
    shuffled.add(set0);
    shuffled.add(set1);
    shuffled.add(set2);
    shuffled.add(set3);
    shuffled.add(set4);

    Random generator = new Random();
    
    for(Data d : m)
    {  
      int i = generator.nextInt(5);             //randomly add in male data objects
      shuffled.get(i).add(m.iterator().next()); 
      // System.out.println("index i: " + i);
    }

    for(Data d : f)
    {
      int i = generator.nextInt(5);           //randomly add in female data objects
      shuffled.get(i).add(f.iterator().next());
    }

    for(ArrayList<Data> ald : shuffled)
    {
      Collections.shuffle(ald);
    }
    return shuffled;
  }

}


