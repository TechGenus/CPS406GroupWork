package jacob_files;
import java.io.*;
public class IncomeStatementWork
{
	public static void statementWrite(String name, String income)
	{
		try
		{
			File file = new File("IncomeStatement.txt");
			file.createNewFile();
		
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
			String treasurerName = name;
			String clubIncome = income;
		
			writer.write("Treasurer name: " + treasurerName);
			writer.newLine();
			writer.write("Club Income: " + clubIncome);
			writer.flush();
			writer.close();
		}
		catch(FileNotFoundException e)
		{
			
		}
		catch(IOException e)
		{
			
		}
		return;
	}
}