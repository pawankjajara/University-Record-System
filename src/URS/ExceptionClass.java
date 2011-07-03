package URS;

public class ExceptionClass  extends Throwable 
{
		private int exception;
				
		public ExceptionClass(int ExceptionCode) 
		{
			this.exception = ExceptionCode;
		}						
		
		public int getException()
		{
			return this.exception;
		}							
}
