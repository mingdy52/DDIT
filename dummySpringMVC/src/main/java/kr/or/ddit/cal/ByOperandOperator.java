package kr.or.ddit.cal;

@FunctionalInterface
public interface ByOperandOperator {
	public long operate(long operand1, long operand2);
}
