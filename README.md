# java-chess
체스 게임 구현을 위한 저장소

## 기능 구현 목록
- 체스판 초기화
    - 가로는 a~h 까지, 세로는 1~8까지
    - 진영은 검은색(대문자), 흰색(소문자)로 나뉜다.

- 체스말의 이동 (show source target)
    - 이동 규칙을 구현한다.
        - 체스말의 이동 규칙과 맞지 않는 target 위치
        - source에 체스말이 존재하지 않는 경우
        - 가는 도중 장애물이 있는 경우를 확인한다.
        - source, target으로 체스판을 벗어난 위치가 입력되는 경우 
    - 이동은 진영을 번갈아 가면서 할 수 있도록 한다.

- 이동 가능 위치 표시 (show position)
    - 체스말이 이동 가능한 모든 위치를 표시한다.

- 점수 계산과 출력 (gameStatus)
    - king이 잡히면 점수가 없다.
    - Q : 9, R : 5, B : 3, N : 2.5, P : 1
    - 단, 같은 세로 줄에 같은 색의 폰이 있는 경우 폰은 각 0.5점으로 한다.
    
- 말의 이동 경로를 나타내는 Path
    - Position의 List를 가지는 일급 컬렉션

- 입/출력 
    - 체스 게임 시작을 알린다.
    - 게임 명령어를 안내한다. 
        - \> 게임 시작 : start
        - \> 게임 종료 : end
        - \> 게임 이동 : move source 위치 target 위치
    - 유저의 입력을 받는다.
    

## 4단계 요구사항
- 콘솔 UI와 더불어 웹으로 체스 게임이 가능해야 한다.
- 웹 UI를 적용할 때 도메인 객체의 변경을 최소화 해야한다.

## 5단계 요구사항
필수 요구사항
- 웹 서버를 재시작 하더라도 이전에 하던 체스게임을 다시 시작할 수 있어야 한다.
선택 요구사항
- 체스 게임방을 만들고 체스 게임방에 입장할 수 있는 기능을 추가한다.
  * 게임방 생성 시 입력할 정보
    * White 유저 이름
    * black 유저 이름
    * 게임방 제목
- 체스 게임방에 ```ID``` 또는 ```방 이름``` 으로 입장할 수 있다.
- 사용자 별로 체스 게임 기록을 관리할 수 있다.