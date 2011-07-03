package URS;

import java.util.List;

public class TokenizeHold {
	
	private String requestId = null;
	private String operation = null;
	private int parameterCount = 0;
	private List<String> parameterValues = null;
	
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getParameterCount() {
		return parameterCount;
	}

	public void setParameterCount(int parameterCount) {
		this.parameterCount = parameterCount;
	}

	public List<String> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(List<String> parameterValues) {
		this.parameterValues = parameterValues;
	}

}
