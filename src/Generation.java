import java.util.Arrays;

public class Generation 
{
	private boolean[] cellStates;
	public Generation(boolean... states)
	{
		if(states == null || states.length == 0)
			cellStates = new boolean[] {false};
		
		else
		{
			cellStates = new boolean[states.length];
			int counter = 0;
			for(boolean state : states)
			{
				cellStates[counter] = state;
				counter++;
			}
		}	
	}
	
	public Generation(String states, char trueSymbol)
	{
		if(states == "" || states == null)
		{
			cellStates = new boolean[] {false};
		}
		else
		{
			String[] charStates = states.split("");
			cellStates = new boolean[charStates.length];
			for(int i = 0; i < charStates.length; ++i)
			{
				if(charStates[i].charAt(0) == trueSymbol)
				{
					cellStates[i] = true;
				}
				else
				{
					cellStates[i] = false;
				}
			}
		}
	}
	/**
	 * 
	 * @param idx index of target state
	 * @returns cell State at specified index
	 */
	public boolean getState(int idx)
	{
		if(cellStates.length < idx)
		{
			return false;
		}
		else
			return cellStates[idx];
		
	}
	public boolean[] getStates()
	{
		return Arrays.copyOf(cellStates, cellStates.length);
	}
	public String getStates(char falseSymbol, char trueSymbol)
	{
		StringBuilder stb = new StringBuilder();
		for(boolean state : cellStates)
		{
			if(state == true)
				stb.append(trueSymbol);
			else
				stb.append(falseSymbol);
		}
		return stb.toString();
	}
	public int size()
	{
		return Arrays.copyOf(cellStates, cellStates.length).length;
	}

}
