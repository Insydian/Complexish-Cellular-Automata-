import javax.management.RuntimeErrorException;

/**
 * 
 * @author Caleb LaRue
 */
public class Application 
{
	
	

	private static final int NUM_EXPECTED_ARGS = 6;

	private static final int IDX_CA = 0;
	private static final int IDX_RULE_NUM = 1;
	private static final int IDX_FALSE_SYMBOL = 2;
	private static final int IDX_TRUE_SYMBOL = 3;
	private static final int IDX_INITIAL_GENERATION = 4;
	private static final int IDX_NUM_EVOLVE = 5;

	private static final String ARG_NAMES = "ca rule-num false-symbol true-symbol initial-generation num-evolutions";
	
	// Source and class file names must match, so Application can be hard-coded
	private static final String USAGE_FMT_STRING_CLASS = "Usage: java Application " + ARG_NAMES;

	// The jar file may be renamed, so this will be varied
	private static final String USAGE_FMT_STRING_JAR = "Usage: java -jar %s " + ARG_NAMES;

	private String[] appArgs;
	
	public Application(String[] args) {
		// TODO: Validate the number of arguments passed 
		// and set the appArgs variable.
		validateNumArgs(args);
		appArgs = args;
	}

	private void validateNumArgs(String[] args) {
		// TODO: Validate the number of arguments and throw an exception
		// if they do not match the expected amount.
		if(args.length != NUM_EXPECTED_ARGS)
			throwRuntimeExceptionWithUsageMessage();
	}

	private void throwRuntimeExceptionWithUsageMessage() {
		// Implementation provided
		if (runningAsJar()) {
			// Get the path to the executing file
			String path = Application.class
					.getProtectionDomain()
					.getCodeSource()
					.getLocation()
					.getPath();
			// Only take path after last slash (to get file name).
			// A hard-coded slash is fine since Java URLs use /
			String file = path.substring(path.lastIndexOf("/") + 1);
			throw new RuntimeException(String.format(USAGE_FMT_STRING_JAR, file));
		} else {
			throw new RuntimeException(String.format(USAGE_FMT_STRING_CLASS));
		}
	}

	private boolean runningAsJar() {
		// Implementation provided
		return Application.class
				.getResource("Application.class")
				.toString()
				.startsWith("jar");
	}

	private void parseArgs(String[] args)throws RuntimeException, RuleNumException {
		// TODO: Parse each of the six arguments, construct the appropriate 
		// Automaton, and print out the full evolution to System.out. 
		// Refer to the README for details on exception handling.
		
		String ca = args[0];
		int ruleNum = Integer.parseInt(args[1]);
		char falseSymbol = args[2].charAt(0);
		char trueSymbol = args[3].charAt(0);
		String initialGeneration = args[4];
		int numEvolutions = Integer.parseInt(args[5]);
		if(ca.equalsIgnoreCase(CellularAutomaton.ECA.toString()))
		{
			ElementaryAutomaton a = new ElementaryAutomaton(ruleNum, new Generation(initialGeneration, trueSymbol));
			a.evolve(numEvolutions);
			a.falseSymbol = falseSymbol;
			a.trueSymbol = trueSymbol;
			System.out.println(a.toString());
		}
		else if(ca.equalsIgnoreCase(CellularAutomaton.TCA.toString()))
		{
			TotalisticAutomaton a = new TotalisticAutomaton(ruleNum, new Generation(initialGeneration, trueSymbol));
			a.evolve(numEvolutions);
			a.falseSymbol = falseSymbol;
			a.trueSymbol = trueSymbol;
			System.out.println(a.toString());
		}
		else
			throw new RuntimeException("Unknown cellular automaton type " + ca);
		
	}

	public void run() {
		// TODO: Call the parseArgs method using the previously 
		// given arguments.
		try {
			parseArgs(appArgs);
		}catch( RuleNumException e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void main(String[] args) {
		// TODO: Construct and run an Application using the 
		// supplied main method arguments. Refer to the README
		// for details on RuntimeException handling.
		try {
			new Application(args);
		}catch(RuntimeException e)
		{
			System.err.print(e.toString());
		}
	}
}
