import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
/**
 * 
 * @author Caleb LaRue
 *
 */
public abstract class Automaton 
{
	private Rule rule;
	private ArrayList<Generation> generations;
	public char falseSymbol;
	public char trueSymbol;
	public Automaton(int ruleNum, Generation initial)throws RuleNumException
	{
		rule = createRule(ruleNum);
		generations = new ArrayList<Generation>();
		generations.add(initial);
		falseSymbol = '0';
		trueSymbol = '1';
	}
	public Automaton(String fileName) throws IOException, RuleNumException
	{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		BufferedReader br2 = new BufferedReader(new FileReader(fileName));

		String currentLine;
		boolean[] states;
		//while((currentLine = br.readLine()) != null)
		{
			rule = createRule(Integer.parseInt(br2.readLine()));
			generations = new ArrayList<Generation>();
			String symbolLine = br2.readLine();
			String[] symbols = symbolLine.split(" ");
			falseSymbol = symbols[0].charAt(0);
			trueSymbol = symbols[1].charAt(0);
			String cellStates = br2.readLine();
			String[] cellStatesArr = cellStates.split("");
			states = new boolean[cellStatesArr.length];
			int counter = 0;
			for(String s : cellStatesArr)
			{
				if(s.charAt(0) == trueSymbol)
					states[counter] = true;
				else
					states[counter] = false;
				counter++;
			}
			generations.add(new Generation(states));
		}
		br.close();
		br2.close();
	}
	public int getRuleNum()
	{
		try {
			return rule.getRuleNum();
		}catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}
	public int evolve(int numSteps)
	{
		int numEvos = 0;
		if(numSteps <= 0)
			return 0;
		for(int i = 0; i < numSteps; ++i)
		{
			generations.add(rule.evolve(generations.get(generations.size()-1)));
			numEvos++;
		}
		return numEvos;
	}
	public Generation getGeneration(int stepNum)
	{
		if(generations.size() < stepNum + 1)
		{
			evolve((stepNum + 1) - generations.size());
		}
		if(stepNum <= 0)
			return generations.get(0);
		else
		{
			int arrayPos = stepNum;
			return generations.get(arrayPos);
		}

	}
	public Generation getCurrentGeneration()
	{
		
		return generations.get(generations.size()-1);
	}
	public int getTotalSteps()
	{
		if(generations.size() == 0)
			return 0;
		return generations.size()-1;
	}
	/**
	 * @return a string representation of the whole automaton
	 */
	public String toString()
	{
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		for(Generation current : generations)
		{
			sj.add(current.getStates(falseSymbol, trueSymbol));
		}
		return sj.toString();
	}
	/**
	 * Takes the current automaton and saves it to a file
	 * @param fileName
	 * @throws IOException when the IO is incorrect
	 */
	public void saveEvolution(String fileName)throws IOException
	{
		FileWriter fw = new FileWriter(fileName , false);
		fw.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		for(int i = 0; i < generations.size(); ++i)
		{
			bw.write(generations.get(i).getStates(falseSymbol, trueSymbol));
			if(i <= generations.size()-1)
				bw.write(System.lineSeparator());
		}
		bw.close();
	}
	protected abstract Rule createRule(int ruleNum) throws RuleNumException;
	public String ruleTableString()
	{
		return rule.ruleTableString(falseSymbol, trueSymbol);
	}
	public static Automaton createAutomaton(CellularAutomaton ca, int ruleNum, Generation initial) throws RuleNumException
	{
		if(ca == null)
			return null;
		if(ca.toString().equalsIgnoreCase("ECA"))
			return new ElementaryAutomaton(ruleNum, initial);
		else if(ca.toString().equalsIgnoreCase("TCA"))
			return new TotalisticAutomaton(ruleNum, initial);
		else
			return null;
	}

	

}
