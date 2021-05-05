/*
 * Class qui permet la lecture du fichier .txt
 */

package project_java.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	
	public static String loadFileAsString(String path)
	{
		StringBuilder builder = new StringBuilder();
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String ligne;
			while((ligne = reader.readLine()) != null)
			 	builder.append(ligne + "\n");
			 	
			reader.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return builder.toString();
		
	}
	
	public static int parsInt(String number)
	{
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
