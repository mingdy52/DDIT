package kr.or.ddit.cal;

public class CalculateVO {

	public CalculateVO() {
		super();
	}

	public CalculateVO(long operand1, long operand2, OperatorType operator) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
	}

	private long operand1;
	private long operand2;
	private OperatorType operator;

	public long getOperand1() {
		return operand1;
	}

	public void setOperand1(long operand1) {
		this.operand1 = operand1;
	}

	public long getOperand2() {
		return operand2;
	}

	public void setOperand2(long operand2) {
		this.operand2 = operand2;
	}

	public OperatorType getOperator() {
		return operator;
	}

	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	
	public long getResult() {
		return operator.operate(operand1, operand2);
	}
	
	public String getExpression() {
		return operator.expression(operand1, operand2);
	}
}
