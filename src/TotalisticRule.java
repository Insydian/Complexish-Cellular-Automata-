import java.util.StringJoiner;

public class TotalisticRule extends Rule {

	public TotalisticRule(int aRuleNum)throws RuleNumException {
		super(aRuleNum);
		if(aRuleNum > 63 || aRuleNum < 0)
			throw new RuleNumException(0 , 63);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[] getNeighborhood(int idx, Generation gen) {
		// TODO Auto-generated method stub
		return getNeighborhoodByRadius(idx, 2, gen);
		
	}

	@Override
	public boolean evolve(boolean[] neighborhood) 
	{
		int totalisticCount = 0;
		for(boolean idx : neighborhood)
		{
			if(idx == true)
				totalisticCount++;
		}
		if(totalisticCount == 5)
			return toBooleanSymbol(rule.charAt(2));
		else if(totalisticCount == 4)
			return toBooleanSymbol(rule.charAt(3));
		else if(totalisticCount == 3)
			return toBooleanSymbol(rule.charAt(4));
		else if(totalisticCount == 2)
			return toBooleanSymbol(rule.charAt(5));
		else if(totalisticCount == 1)
			return toBooleanSymbol(rule.charAt(6));
		else return toBooleanSymbol(rule.charAt(7));
	}

	@Override
	public String ruleTableString(char falseSymbol, char trueSymbol) {
		String tableLegend = "5 4 3 2 1 0";
		String totalRuleNum = rule.substring(2);
		StringJoiner sj = new StringJoiner(" ");
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(0))));
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(1))));
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(2))));
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(3))));
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(4))));
		sj.add(String.valueOf(toSymbol(falseSymbol, trueSymbol, totalRuleNum.charAt(5))));
		return (tableLegend + System.lineSeparator() + sj.toString()); 
	}
	private boolean toBooleanSymbol(char idx)
	{
		if(idx == '1')
			return true;
		else return false;
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
