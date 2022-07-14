package kr.or.ddit.cal;

public enum OperatorType {
	PLUS('+',new ByOperandOperator(){

		@Override 
		public long operate(long operand1,long operand2){
			return operand1+operand2;
		}

	})
	, MINUS('-',(op1, op2)->op1-op2)
	, MULTIPLY('*',(op1,op2)->op1*op2)
	, DIVIDE('/',(op1,op2)->op1/op2);

	private ByOperandOperator realOperator;
	private char sign;

	private OperatorType(char sign, ByOperandOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}

	public char getSign() {

		return sign;
	}

	public long operate(long operand1, long operand2) {
		return realOperator.operate(operand1, operand2);
	}

	public String expression(long operand1, long operand2){
		return String.format("%d %s %d = %d", operand1, sign, operand2, operate(operand1, operand2));
	}
}
