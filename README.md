# 몰입캠프 공통과제 1
## 연락처, 갤러리, 그리고 계산기 - 3분반 김성혁, 박강우

연락처, 갤러리, 그리고 계산기를 다룰 수 있는 간단한 안드로이드 어플리케이션입니다.

## 1. 팀원
*    KAIST 전산학부 김성혁
*    한양대 컴퓨터소프트웨어학부 박강우

## 2. 개발 환경
* 개발 OS : Android (minSDKversion: 26, targetSDKversion: 32)
* 개발 언어 : Kotlin
* 테스트 디바이스 : Galaxy S10e

## 3. 어플리케이션
###     0) 전체 구조
저희 앱의 전체적인 구조는 ```TabLayout```을 사용하여 각 탭에 ```Fragment```들을 연결한 구조로 되어 있습니다.   
전체 구조는 ```MainActivity``` 클래스가 맡고 있으며, 각 탭에는 탭의 작업을 나타내는 아이콘이 표시됩니다.   
각 ```Fragment```에서 여러 권한이 사용되므로 이 권한들을 ```MainActivity```에서 요청합니다.

***
###     TAB1 전화 기록부
*            (처음 전화 기록부 들어갔을 때 모습)
*            전화기록부에 사용되는 연락처 data는 json으로 만들어 이전에 변경된 data들을 저장하여 사용합니다.
*            만약 연락처 json파일에 아무 데이터가 없을 때 연락처가 없다는 Image view를 나타냈습니다.
*            그리고 FAB를 통해 연락처를 추가했을 때 fragment에는 이름만 나타내도록 한 뒤 

***
###     TAB2 갤러리
탭 2는 장치의 이미지를 보여주는 간단한 갤러리입니다.
갤러리에 표시되는 이미지들은 장치의 개인 디렉터리인 ```storage/emulated/0/``` 안의 사진들로,   
```Java.nio.file```의 클래스들을 이용하여 [사진들을 불러오도록](https://github.com/glacya/mol12345/blob/master/app/src/main/java/com/example/mol12345/Fragment02.kt#L49-72) 구현되었습니다.


###     TAB3 계산기
*            계산기-logic
*            계산기-UI
