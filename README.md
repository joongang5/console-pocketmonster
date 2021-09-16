# PocketMonster Java console version

### 게임 흐름
1. System.out.print 관련 함수를 통해 선택지가 출력됩니다.
2. Scanner 클래스를 통해 선택지를 입력받습니다.
3. 선택지에 따라 페이지가 이동되거나, 전투 등 컨텐츠 로직을 진행시킵니다.
4. 위의 1~3을 반복하면서 포켓몬을 수집하고 레벨업합니다.
5. 게임 종료 시 Json형식으로 플레이 데이터를 저장해 이어하기가 가능하도록 합니다.

### 코딩 가이드
1. 페이지를 추가하는 방법
> * Page 추상 클래스를 상속받아 추상 메소드를 구현합니다.
> ```java
> public abstract PageType getType();
> public abstract void printAction();
> ```
> * PageType은 PageManager가 Command패턴으로 페이지들을 관리하기 위한 타입 값입니다. <br/>
>   각 페이지마다 고유한 타입이 필요하기 때문에 Enum값을 추가 해야합니다.
> * printAction() 함수는 해당 페이지에 진입했을 때 출력되는 내용들을 담습니다. <br/>
>   그 내용들은 보통은 선택지가 될 것입니다.
