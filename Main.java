//import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main
{
  
	public static void main(String[] args)
	{
    int[] dimensions = {120,128};
    NeuralNetwork NN = new NeuralNetwork(3, 1, dimensions);
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

        System.out.println("call train()");

        ArrayList<String> femaleFiles = new ArrayList<String>();
        ArrayList<String> maleFiles = new ArrayList<String>();
        femaleFiles = getFileNames("Female"); //list of female image file names
        // Data[] femaleData = new Data[femaleFiles.size()]; //Array of female Data objects
        ArrayList<Data> femaleData = new ArrayList<Data>();

        for(String name : femaleFiles)
        {
          Data f = new Data(0.1, name);
          femaleData.add(f);
        }

        maleFiles = getFileNames("Male");
        //Data[] maleData = new Data[maleFiles.size()]; //Array of male Data objects
        ArrayList<Data> maleData = new ArrayList<Data>();
        for(String name : maleFiles)
        {
          Data m = new Data(0.9, maleFiles.get(0));
          maleData.add(m);
        }

        ArrayList<ArrayList<Data>> shuffledImages = new ArrayList<ArrayList<Data>>();
        shuffledImages = shuffle(maleData, femaleData);


        int setNum = 0;
        for(ArrayList<Data> s : shuffledImages)
        {
          System.out.println("\nset: " + setNum);
          for(Data dTemp : s)
          {
            dTemp.print();
          }
          setNum++;
        }

        fiveFold(shuffledImages,NN);  
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

  public static void fiveFold(ArrayList<ArrayList<Data>> ald, NeuralNetwork nn)
  {
    ArrayList<Data> tempDArray = new ArrayList<Data>();
    for(int i = 0; i < 5; i++)
    {
      for(int j = 0; j < 5; j++)
      {
        if(i != j)
        {
          tempDArray = ald.get(j);
          Data[] dTemp = new Data[tempDArray.size()];
          tempDArray.toArray(dTemp);
          nn.trainNetwork(dTemp);
        }
      }
    }
  }
}


