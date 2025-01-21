import java.io.IOException;

public class TotalisticAutomaton extends Automaton {

	public TotalisticAutomaton(int ruleNum, Generation initial)throws RuleNumException {
		super(ruleNum, initial);
		// TODO Auto-generated constructor stub
	}

	public TotalisticAutomaton(String fileName) throws IOException, RuleNumException {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Rule createRule(int ruleNum)throws RuleNumException {
		// TODO Auto-generated method stub
		Rule aRule;
		/*
		 * try { aRule = new TotalisticRule(ruleNum); return aRule; } catch
		 * (RuleNumException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return null;
		 */
		aRule = new TotalisticRule(ruleNum);
		return aRule;
	}

}
