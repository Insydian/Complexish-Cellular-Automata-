/**
 * 
 * @author Caleb LaRue
 * @note Thanks to discord and specifically @blakeH for the help on gNBR
 *
 */
public abstract class Rule 
{
	private int ruleNum;
	public String rule;
	
	public Rule(int aRuleNum)
	{
		
		
			ruleNum = aRuleNum;
		String binaryString = Integer.toBinaryString(ruleNum);
		rule = String.format("%8s", binaryString).replace(" ", "0");
		
	}
	/**
	 * 
	 * @param idx the index of the number that you want the neighborhood for
	 * @param gen is the generation of the neighborhood of the index
	 * @return a boolean array that is the left and right neighbors with the initial @param idx in the middle
	 */
	public abstract boolean[] getNeighborhood(int idx, Generation gen);
	public abstract boolean evolve(boolean[] neighborhood);

	/*
	 */	public abstract String ruleTableString(char falseSymbol, char trueSymbol);
	
	/**
	 * @param idx index of the cell that is wanted for the neighborhood
	 * @param radius  The radius refers to the additional number of cells to the left/right of the given index.
	 * @param gen what generation is the target cell in
	 * @return a boolean array of the neighbors in a radius with the specific parameters
	 */
	public static boolean[] getNeighborhoodByRadius(int idx, int radius, Generation gen)
	{
		boolean[] neighbors = new boolean[radius + radius + 1];
		neighbors[radius] = gen.getState(idx);
		
		for(int i = idx - radius, j = 0; i <= idx + radius; ++i, ++j)
		{
			neighbors[j] = gen.getState(Math.floorMod(i, gen.size()));
		}
		return neighbors;
	}
	public int getRuleNum()
	{
		return Integer.parseInt(rule,2);
	}
	/**
	 * 
	 * @param gen 
	 * takes Generation and evolves it 1 generation
	 * @return
	 * the evolved generation
	 */
	public Generation evolve(Generation gen)
	{
		boolean[] newStates = new boolean[gen.size()];
		for(int i = 0; i < gen.size(); ++i)
		{
			boolean[] neighborhood = getNeighborhood(i, gen);
			newStates[i] = evolve(neighborhood);
		}
		return new Generation(newStates);
	}
	

}
