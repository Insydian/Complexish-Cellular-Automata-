import java.io.IOException;

public class ElementaryAutomaton extends Automaton {

	public ElementaryAutomaton(int ruleNum, Generation initial)throws RuleNumException {
		super(ruleNum, initial);
		// TODO Auto-generated constructor stub
	}

	public ElementaryAutomaton(String fileName) throws IOException, RuleNumException {
		super(fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Rule createRule(int ruleNum)throws RuleNumException {
		// TODO Auto-generated method stub
		Rule aRule;
		/*
		 * try { aRule = new ElementaryRule(ruleNum); return aRule; } catch
		 * (RuleNumException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return null;
		 */		
		aRule = new ElementaryRule(ruleNum);
		return aRule;
	}

}
