package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CaculatorTest {
	private StringChecker stringChecker = new StringChecker();
	
	private void assertChecker(String expression, char addChar, StringCheckResult expResult) {
		StringCheckResult realResult = stringChecker.checkInputString(expression, addChar);
		assertEquals(realResult, expResult);
	}
	
	// 정상적으로 입력되는 경우
	@Test
	void normalInput_Then_Pass() {
		assertChecker("123+456",'3', StringCheckResult.PASS);
		assertChecker("123+456-321",'5', StringCheckResult.PASS);
		assertChecker("123",'+', StringCheckResult.PASS);
	}
	
	// 20자가 넘는 경우
	@Test
	void lengthOverLimit_Then_Fail() {
		assertChecker("1234+1234+1234+12345", '3', StringCheckResult.LENGTH_OVER);
		assertChecker("1234+1234+1234+12345", '+', StringCheckResult.LENGTH_OVER);
	}
	
	// 첫 입력이 연산자나 닫는 괄호인 경우
	@Test
	void firstInputNAN_Then_Fail() {
		assertChecker("", '+', StringCheckResult.FAIL);
		assertChecker("", '*', StringCheckResult.FAIL);
		assertChecker("", '0', StringCheckResult.PASS);
	}
	
	// 연산자를 연속으로 입력하는 경우
	@Test
	void operationInaRow_Then_Update() {
		assertChecker("123+", '+', StringCheckResult.UPDATE);
		assertChecker("123+", '*', StringCheckResult.UPDATE);
		assertChecker("123/", '+', StringCheckResult.UPDATE);
		assertChecker("123/", '2', StringCheckResult.PASS);
	}
	
	// 숫자 뒤에 여는 괄호가 오는 경우
	@Test
	void numberAndBucket_Then_Multiplication() {
		assertChecker("123", '(', StringCheckResult.MULTIPLICATION);
		assertChecker("123567", '(', StringCheckResult.MULTIPLICATION);
	}
	
	// 연산자 뒤에 닫는 괄호가 오는 경우
	@Test
	void operatorAndBucket_Then_Ignore() {
		assertChecker("(123*", ')', StringCheckResult.IGNORE);
		assertChecker("(123/", ')', StringCheckResult.IGNORE);
		assertChecker("(123+", ')', StringCheckResult.IGNORE);
	}
	
	// 닫는 괄호 뒤에 연산자가 아닌 문자가 오는 경우 (숫자, 여는 괄호)
	@Test
	void closeAndNumber_Then_Multiplication() {
		assertChecker("(123)", '1', StringCheckResult.MULTIPLICATION);
		assertChecker("(123)", ')', StringCheckResult.PASS);
		assertChecker("(123)", '(', StringCheckResult.MULTIPLICATION);
	}

	// 닫는 괄호 뒤에 .이 오는 경우
	@Test
	void closeAndDot_Then_MultiplyZero() {
		assertChecker("(123)", '.', StringCheckResult.MULTIPLY_ZERO);
		assertChecker("(1223*453)", '.', StringCheckResult.MULTIPLY_ZERO);
	}	
	
	// 여는 괄호 뒤에 연산자나 닫는 괄호가 오는 경우
	@Test
	void bucketAndOperator_Then_Fail() {
		assertChecker("(", '+', StringCheckResult.FAIL);
		assertChecker("(123+345)*(", ')', StringCheckResult.FAIL);
	}
	
	// 숫자 없이 .만 입력하는 경우
	@Test
	void inputDot_Then_Dot() {
		assertChecker("", '.', StringCheckResult.DOT);
		assertChecker("123+", '.', StringCheckResult.DOT);
		assertChecker("123", '.', StringCheckResult.PASS);
	}
	
	// .이 연속으로 오는 경우
	@Test
	void doubleDot_Then_Fail() {
		assertChecker("123.", '.', StringCheckResult.FAIL);
	}
	
}
