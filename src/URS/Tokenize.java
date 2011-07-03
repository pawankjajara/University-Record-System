package URS;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Tokenize 
{
	private static Tokenize tokenizer = new Tokenize();
	
	public TokenizeHold getTokens(String text) 
	{
		TokenizeHold hold = new TokenizeHold();
		
		StringTokenizer tokenizer = new StringTokenizer(text, "\n");
		hold.setOperation(tokenizer.nextToken());
		hold.setRequestId(tokenizer.nextToken());
		hold.setParameterCount(Integer.valueOf(tokenizer.nextToken()));
		
		List parameters = new ArrayList();
		
		while (tokenizer.hasMoreTokens()) {
			parameters.add(tokenizer.nextElement());
		}
		
		hold.setParameterValues(parameters);
		return hold;
	}
	public static Tokenize getInstanceofTokenize() 
	{
		return tokenizer;
	}

}
