import java.nio.file.*;
import java.io.*;

public class Main
{
	public static void main(String[] args)
	{	File file = new File(args[0]);
		InputStream in = Files.newInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;

		while((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}

	}
}