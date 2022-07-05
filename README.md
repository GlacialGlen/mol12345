# 몰입캠프 공통과제 1
### 연락처, 갤러리, 그리고 계산기 - 3분반 김성혁, 박강우

###1. 팀원
*    KAIST 전산학부 김성혁
*    한양대 컴퓨터소프트웨어학부 박강우
***
###2. 개발 환경
* OS :
* Language : Kotlin
* IDE : Android Studio
* Device : Galaxy S10e
***
###3. 어플리케이션
###     TAB1 전화 기록부
(처음 전화 기록부 들어갔을 때 모습)
전화기록부에 사용되는 연락처 data는 json으로 만들어 이전에 변경된 data들을 저장하여 사용합니다.

만약 연락처 json파일에 아무 데이터가 없을 때 연락처가 없다는 Image view를 나타냈습니다.

(번호 추가했을 때 모습)
그리고 FAB를 통해 연락처를 추가했을 때 fragment에는 이름만 나타내도록 한 뒤 이름을 누른다면 data를 나타내는 ```contactactivity``` 가 나타나게 된다.

(contactactivity나왔을 때 모습)

```contactactivity``` 에서 편집 버튼과 전화 걸기, 메세지 보내기 버튼을 만들었다.

전화 걸기, 메세지 보내기 버튼을 눌렀을 때에는 intent를 이용하여 핸드폰 내부에 있는 전화와 메세지로 이동하여 현재 data의 전화 번호로 보낼 수 있도록 하였다.

편집 버튼을 눌렀을 때에는 ```editoractivity```를 실행 시킨다. 이 때 ```editoractivity```에서 바꾼 값을 ```contactactivity```에서 받을 수 있도록 ```registerForActivityResult```를 사용하여 data를 받았다.

그리고 바뀐 data를 ```Fragment01```에 있는 datalist에 적용시키기 위해 SharedPrefrence를 사용하여 미리 만든 SharedManager를 사용했다.

```editoractivity```안에는 바꾼 data를 저장하는 버튼, 바꾸지 않고 돌아가는 버튼, 그리고 현재 연락처 데이터를 삭제하는 버튼이 존재한다.
(editoractivity 사진)

그리고 각각 activity 내부에는 ```view binding```을 사용하여 xml파일과 binding을 하여 data를 다루었다.

그리고 ```registerForActivityResult```에서는 data를 보내는 activity에서는 intent에 data들을 보낸뒤 RESULT_OK로 바꾼 뒤 data를 받는 activity에서 extra로 받은 data들을 적용시켰다.

```editoractivity```에서 연락처 삭제 버튼을 눌렀을 떄에는 ```contactactivity```를 거치지 않고 바로 ```fragment01```로 가야 하므로 resultcode를 RESULT_FIRST_USER로 설정한 뒤 보냈다.



***
###     TAB2 갤러리
*            dddd
***
###     TAB3 계산기
*            계산기-logic

계산기 버튼 누른 것에 따른 결과 값을 return해주는 class인 ```Caclulator```를 만들었다.

```Calculator```에 외부 function으로 현재 data에 따른 textView에 출력해야하는 data를 return하는 function이 존재한다.

```Calculator```에는 입력 받은 값이 숫자면 CallNum, +, -,와 같은 연산자일 때 CallOp, 결과 값을 출력하는 =일 때 CallCal, +/-를 실행 했을 때 CallNeg, "."를 입력 했을 때 CallDot, "%"를 입력 했을 때 CallPer, 현재 class내부에 있는 list와 같은 variable을 초기화 하는 Clear()함수가 존재한다.

계산기에서 입력 받은 data는 모두 String으로 받는다.

Calculator 내부에는 계산 priority에 따라 계산을 하지 않고 연산자를 저장하는 oplist와, 계산해야하는 숫자를 저장하는 numlist가 존재한다.

그리고 아이폰 계산기의 특징에 따라 "="가 두번 이상 누른상황일 때 필요한 lastnum,lastop가 존재해야 한다.

그리고 바로 직전에 입력 받은 값이 무엇인지 확인하는 latest가 존재한다.

실제 계산을 할 때에는 numlist에 있는 숫자를 꺼내서 double로 바꾼 뒤 계산 후 결과 값이 만약 int라면 int로 바꾼 뒤 다시 string으로 바꿔서 저장한다.

Callcal 부분에서는 ```number op number =```, ```number op =```, ```~~~==```와 같이 세가지의 경우로 나눠서 계산을 진행하였다.