# 문자열 계산기

## 프로젝트 목표
- TDD 적용
- [자바 코드 컨벤션](https://naver.github.io/hackday-conventions-java/) 엄수

## 사용 기술
- Java 8
- Java Swing

## 기능 요구사항
숫자와 사칙연산(+, -, *, /), 괄호를 이용할 수 있는 계산기를 구현한다.
- 0으로 나눌 경우 '0으로 나눌 수 없습니다.' 팝업 표시
- 괄호의 짝이 맞지 않을 경우 '괄호를 잘못 입력하였습니다.' 팝업 표시

## 입출력 요구사항
### 입력
- 키보드 입력 : 숫자 및 +, -, *, /, (, ), enter, backspace만 입력 가능
- 연산자는 연속으로 입력할 수 없으며 마지막으로 입력된 연산자로 변환됨
- 첫 입력은 숫자만 가능 
- 닫는 괄호 뒤에 바로 숫자 혹은 여는 괄호가 나올 경우 * 연산자 추가
- 버튼 입력
- 마우스 커서 이동

## 첨고 사항
갤럭시 기본 계산기 앱 디자인 참고


<img src="https://camo.githubusercontent.com/f74076546ff6048024653bd9d930875f9e123692f33b99a54c7b1d0b2b26c80b/68747470733a2f2f696d6167652e77696e7564662e636f6d2f76322f696d616765312f593239744c6e4e6c59793568626d527962326c6b4c6d4677634335776233423163474e6862474e316247463062334a6663324e795a575675587a42664d54597a4e4449774f5467344e3138774d44672f73637265656e2d302e6a70673f66616b6575726c3d3126747970653d2e6a7067" width="300">
