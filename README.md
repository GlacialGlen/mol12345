# 몰입캠프 공통과제 1
### 연락처, 갤러리, 그리고 계산기 - 3분반 김성혁, 박강우

연락처, 갤러리, 그리고 계산기를 다룰 수 있는 간단한 안드로이드 어플리케이션입니다.

## 1. 팀원
*    KAIST 전산학부 김성혁
*    한양대 컴퓨터소프트웨어학부 박강우

## 2. 개발 환경
* 개발 OS : Android (minSDKversion: 26, targetSDKversion: 32)
* 개발 언어 : Kotlin
* 테스트 디바이스 : Galaxy S10e

## 3. 어플리케이션
###     전체 구조
저희 앱의 전체적인 구조는 ```TabLayout```을 사용하여 각 탭에 ```Fragment```들을 연결한 구조로 되어 있습니다.   
전체 구조는 ```MainActivity``` 클래스가 맡고 있으며, 각 탭에는 탭의 작업을 나타내는 아이콘이 표시됩니다.   
각 ```Fragment```에서 여러 권한이 사용되므로 이 권한들을 ```MainActivity```에서 요청합니다.

###     TAB1  전화 기록부

<img src="/screenshots/contact_empty.png" width="216px" height="456px" title="연락처 없음"></img>

전화기록부에 사용되는 연락처 data는 json으로 만들어 이전에 변경된 data들을 저장하여 사용합니다.

만약 연락처 json파일에 아무 데이터가 없을 때 연락처가 없다는 Image view를 나타냈습니다.

<img src="/screenshots/contact_some.png" width="216px" height="456px" title="연락처 있음"></img>

그리고 FAB를 통해 연락처를 추가했을 때 fragment에는 이름만 나타내도록 한 뒤 이름을 누른다면 data를 나타내는 ```contactactivity``` 가 나타납니다.

<img src="/screenshots/contact_info.png" width="216px" height="456px" title="연락처 정보"></img>

```contactactivity``` 에서 편집 버튼과 전화 걸기, 메세지 보내기 버튼을 만들었습니다.

전화 걸기, 메세지 보내기 버튼을 눌렀을 때에는 intent를 이용하여 핸드폰 내부에 있는 전화와 메세지로 이동하여 현재 data의 전화 번호로 보낼 수 있도록 구현하였습니다.

편집 버튼을 눌렀을 때에는 ```editoractivity```를 실행 시킵니다. 이 때 ```editoractivity```에서 바꾼 값을 ```contactactivity```에서 받을 수 있도록 ```registerForActivityResult```를 사용하여 data를 받았습니다.

그리고 바뀐 data를 ```Fragment01```에 있는 datalist에 적용시키기 위해 SharedPrefrence를 사용하여 미리 만든 SharedManager를 사용했습니다.

```editoractivity```안에는 바꾼 data를 저장하는 버튼, 바꾸지 않고 돌아가는 버튼, 그리고 현재 연락처 데이터를 삭제하는 버튼이 존재합니다.

<img src="/screenshots/contact_edit.png" width="216px" height="456px" title="연락처 수정"></img>

그리고 각각 activity 내부에는 ```view binding```을 사용하여 xml파일과 binding을 하여 data를 다루었습니다.

그리고 ```registerForActivityResult```에서는 data를 보내는 activity에서는 intent에 data들을 보낸뒤 RESULT_OK로 바꾼 뒤 data를 받는 activity에서 extra로 받은 data들을 적용시켰습니다.

```editoractivity```에서 연락처 삭제 버튼을 눌렀을 떄에는 ```contactactivity```를 거치지 않고 바로 ```fragment01```로 가야 하므로 resultcode를 RESULT_FIRST_USER로 설정한 뒤 보냈다.



***
###     TAB2  갤러리
탭 2는 장치의 이미지를 보여주는 간단한 갤러리입니다.   
<img src="/screenshots/gallery_empty.png" width="216px" height="456px" title="갤러리 이미지 없음"></img>
<img src="/screenshots/gallery_hasimage.png" width="216px" height="456px" title="갤러리 이미지 있음"></img>
#### 갤러리 사진 리스트
갤러리에 표시되는 이미지들은 장치의 개인 디렉터리인 ```storage/emulated/0/``` 안의 사진들로,   
```Java.nio.file```의 클래스들을 이용하여 [사진들의 리스트를 불러오도록](https://github.com/glacya/mol12345/blob/e6476d51fb01dc3be7584da183fecf021885ff47/app/src/main/java/com/example/mol12345/Fragment02.kt#L49-L72) 구현되었습니다.

불러온 사진들은 ```RecyclerView```를 사용해 배치되고, ```RecyclerView```의 각 요소들은 불러온 사진들을 사용하는 ```ImageButton```입니다.   
```onResume()``` 메서드를 활용하여 앱을 사용자가 다시 볼 때마다 사진들의 리스트를 업데이트하도록 설계했습니다.

#### 갤러리 사진 확대
각 사진의 ```ImageButton```을 누르면 누른 사진을 확대하여 보여주는 새로운 ```Activity```가 열립니다.   
이 ```Activity```는 ```ViewPager2```로 구현되어 다음 기능을 지원합니다.
* 좌우로 스와이프하여 다른 사진을 볼 수 있습니다.
* ```ViewPager2```가 보여주는 사진이 사용자가 누른 사진을 보여주도록 자동으로 스크롤됩니다.


###     TAB3  계산기
탭 3은 간단한 계산을 할 수 있는 계산기입니다.   
<img src="/screenshots/calculator.png" width="216px" height="456px" title="계산기"></img>

* 계산기-logic

  계산기 버튼 누른 것에 따른 결과 값을 return해주는 class인 ```Caclulator```를 만들었습니다.

  ```Calculator```에 외부 function으로 현재 data에 따른 textView에 출력해야하는 data를 return하는 function이 존재합니다.

  ```Calculator```에는 입력 받은 값이 숫자면 CallNum, +, -,와 같은 연산자일 때 CallOp, 결과 값을 출력하는 =일 때 CallCal, +/-를 실행 했을 때 CallNeg, "."를 입력 했을 때 CallDot, "%"를 입력 했을 때 CallPer, 현재 class내부에 있는 list와 같은 variable을 초기화 하는 Clear()함수가 존재합니다.

  계산기에서 입력 받은 data는 모두 String으로 받습니다.

  Calculator 내부에는 계산 priority에 따라 계산을 하지 않고 연산자를 저장하는 oplist와, 계산해야하는 숫자를 저장하는 numlist가 존재합니다.

  그리고 아이폰 계산기의 특징에 따라 "="가 두번 이상 누른상황일 때 필요한 lastnum,lastop가 존재해야 합니다.

  그리고 바로 직전에 입력 받은 값이 무엇인지 확인하는 latest가 존재합니다.

  실제 계산을 할 때에는 numlist에 있는 숫자를 꺼내서 double로 바꾼 뒤 계산 후 결과 값이 만약 int라면 int로 바꾼 뒤 다시 string으로 바꿔서 저장합니다.

  Callcal 부분에서는 ```number op number =```, ```number op =```, ```~~~==```와 같이 세가지의 경우로 나눠서 계산을 진행하였습니다.

* 계산기-UI   
  계산기의 레이아웃은 ```ConstraintLayout```에 계산기의 현재 텍스트를 표시하는 ```TextView```와
  계산기의 버튼들을 나타내는 ```Button```들로 구성됩니다.   
  특정한 버튼들을 누름에 따라 AC 버튼이 C로 바뀌거나, 연산자 버튼의 색이 유지되는 등의 요소가 포함되어 있습니다.
