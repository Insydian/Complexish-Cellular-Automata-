import java.util.StringJoiner;

public class ElementaryRule extends Rule {

	public ElementaryRule(int aRuleNum) throws RuleNumException{
		super(aRuleNum);
		if(aRuleNum < 0 || aRuleNum > 256)
			throw new RuleNumException(0,255);

	}

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		return getNeighborhoodByRadius(idx, 1, gen);
	}

	/**
	 * @param neighborhood - give it a neighborhood array of 3
	 * @return a boolean that states whether the method evolved or not
	 */
	public boolean evolve(boolean[] neighborhood) {
		boolean leftNeighbor = neighborhood[0];
		boolean idx = neighborhood[1];
		boolean rightNeighbor = neighborhood[2];
		int howManyTrue = 0;
		boolean newState;
		for(boolean state : neighborhood)
		{
			if(state == true)
			{
				howManyTrue++;
			}
		}
		if(howManyTrue == 3)
		{
			newState = changeCellState(0);
		}
		else if(howManyTrue == 0)
		{
			newState = changeCellState(7);
		}
		else if(howManyTrue == 2)
		{
			if(leftNeighbor == true && idx == true)
				newState = changeCellState(1);
			else if(leftNeighbor == true && rightNeighbor == true)
				newState = changeCellState(2);
			else
				newState = changeCellState(4);
		}
		else
		{
			if(leftNeighbor == true)
				newState = changeCellState(3);
			else if(idx == true)
				newState = changeCellState(5);
			else
				newState = changeCellState(6);
		}
		return newState;
	}
	/**
	 *@category Helper Method for Rule from project 1 
	 * @param idx
	 * @return a boolean that changes the cell state
	 */
	private boolean changeCellState(int idx)
	{
		if(rule.charAt(idx) == '1')
			return true;
		else 
			return false;

	}
	/**
	 * @category Helper Method from project 1
	 * @return locates an index
	 */
	private int whereIsIDX()
	{
		if(rule.length() == 7)
			return 7;
		if(rule.length()==6)
			return 6;
		return 0;
	}

	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		StringJoiner sj = new StringJoiner(" ");
		sj.add(String.valueOf(trueSymbol) + String.valueOf(trueSymbol) + String.valueOf(trueSymbol));
		sj.add(String.valueOf(trueSymbol) + String.valueOf(trueSymbol) + String.valueOf(falseSymbol));
		sj.add(String.valueOf(trueSymbol) + String.valueOf(falseSymbol) + String.valueOf(trueSymbol));
		sj.add(String.valueOf(trueSymbol) + String.valueOf(falseSymbol) + String.valueOf(falseSymbol));
		sj.add(String.valueOf(falseSymbol) + String.valueOf(trueSymbol) + String.valueOf(trueSymbol));
		sj.add(String.valueOf(falseSymbol) + String.valueOf(trueSymbol) + String.valueOf(falseSymbol));
		sj.add(String.valueOf(falseSymbol) + String.valueOf(falseSymbol) + String.valueOf(trueSymbol));
		sj.add(String.valueOf(falseSymbol) + String.valueOf(falseSymbol) + String.valueOf(falseSymbol));
		
		StringJoiner sj2 = new StringJoiner(" ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(0))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(1))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(2))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(3))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(4))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(5))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(6))) + " ");
		sj2.add(" " + String.valueOf(toSymbol(falseSymbol,trueSymbol,rule.charAt(7))) + " ");
		
		return(sj.toString() + System.lineSeparator() + sj2.toString());
		}
	/**
	 * helper method to turn chars into the correct symbols
	 * @param falseSymbol
	 * @param trueSymbol
	 * @param idxChar
	 * @return
	 */
	private char toSymbol(char falseSymbol, char trueSymbol, char idxChar)
	{
		if(idxChar == '1')
			return trueSymbol;
		else return falseSymbol;
	}
	

}
