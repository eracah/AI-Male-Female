import java.nio.file.*;
import java.io.*;
import java.util.*;
















public class Main
{
	public static void main(String[] args)
	{	
		if(args.length != 1) 
		{
  			System.err.println("Invalid command line, exactly one argument required");
  			System.exit(1);
		}

		try
		{
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
              // Print the content on the console
              System.out.println (strLine);
            }
            //Close the input stream
            //in.close();
            }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
            }

	}

}
