package test;

public class StringChecker {

	public StringCheckResult checkInputString(String expression, char addChar) {
		int expLen = expression.length();
		char lastChar = '\u0000';
		
		if(expLen >= 20) {
			return StringCheckResult.LENGTH_OVER;
		}else if(expLen > 0) {
			lastChar = expression.charAt(expression.length() - 1);
		}else {
			if(!(isNumber(addChar) || addChar=='.' || addChar=='('))
				return StringCheckResult.FAIL;
		}		
		
		if(isOperator(lastChar) && (addChar==')'))
			return StringCheckResult.IGNORE;
		
		if(isOperator(lastChar) && !(isNumber(addChar) || addChar=='.' || addChar=='('))
			return StringCheckResult.UPDATE;

		if(lastChar == ')' && !isOperator(addChar)) {
			if(addChar == '.') {
				return StringCheckResult.MULTIPLY_ZERO;
			}else if(addChar == ')') {
				return StringCheckResult.PASS;
			}
			return StringCheckResult.MULTIPLICATION;
		}
		
		if(!isNumber(lastChar) && addChar == '.') {
			if(lastChar == '.')
				return StringCheckResult.FAIL;
			return StringCheckResult.DOT;
		}
		
		if(isNumber(lastChar) && addChar == '(')
			return StringCheckResult.MULTIPLICATION;
		
		if(lastChar == '(' && (addChar == ')' || isOperator(addChar)))
			return StringCheckResult.FAIL;
		
		return StringCheckResult.PASS;
	}
	
	private boolean isOperator(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/')
			return true;
		return false;
	}
	
	private boolean isNumber(char c) {
		if(c >= '0' && c <= '9')
			return true;
		return false;
	}

}
