package com.doyoon.android.fastcampusandroid.week2.lecture;

/**
 * Created by DOYOON on 5/21/2017.
 */

public class Summary {

    /* Background */
    /* 안드로이드에서 개발 버전 타케팅을 선택해야한다. 버전에 따라 굳이 구현할필요가 없는 부분도 생기기 때문에 타케팅을 잘 선택 */
    /* Travis = Continuous Integration : 돌려놓고 나는 계속 코딩을 할 수 있다. */
    /* Lint and Graddle */
    /* 라이브러리를 계속해서 배워나가겠지만 배우고 나서 1개월안에 사장되는 상황도 자주 생긴다. */
    /* 북미에선 Travis, 한국일본에서는 Jenkins를 사용 Jenkins는 내부 서버를 구축해서 사용(보안) */
    /* 클래스 R이 모두 빨간줄로 나올때는 폴더경로에서 파일을 삭제(해당프로젝트파일\app\build\generated 안에 모든 파일 삭제) */
    /* manifest파일 안에서 app 정보를 설정 최저버전, 액티비티는 몇개인지, 어떤 액티비티를 먼저 가져올 것인지 */
        // 다른 액티비티를 그냥 복사해서 사용하면 manifest에 등록이 되어 있지 않아서 에러가 난다.

    /* Acitivity를 따로 띄우면 메모리 관리가 수월하다, 하나에 다 몰아 넣으면 속도는 빠르지만, 메모리를 많이 잡아먹는다. */
        // Fragment

    /* Layout */
    // Contraints vs Reliatve Layout : Constraints는 희소행렬을 사용해서 조금 더 빠르다고 한다.
    // Margin(Web으로 이해하자면 해당 Div와 나를 감싸고 있는 Contianer와의 관계이다.)
    // Padding(Web으로 이해하자면 해당 Div와 그 안에 있는 다른 elemr과의 관계이다.)
    // .xml파일에서 앞쪽에 작성된 코드가 먼저 그려져서 UI에서 가장 아래쪽에 쌓인다.

    /* Usage */
    //    Intent intent = new Intent(this, MainActivity.class);
        // 객체가 아니고 클래스를 넘겨준다. 전달받은 곳에서 해당 클래스를 이용해서 객체를 만들고 사용해라는 의미

    /* Font */
    // Sp = Scaled
    // Dp = not Scaled
    // Dp <-> px 변환하는 코드를 다운받아서 사용할것.

    // .setText() 에서 정수만 들어가면 error가 난다. (구글 설계 미스 다형성을 잘못 형성한 예, int와 resource를 받는다.)

    // 소스코드를 찾아 들어갈때는 getText -> mText -> 초기화 하는 부분을 찾으면 초기값이 null인지 ""인지 알 수있다.
    // 그래서 getText에서 아무것도 입력되지 않았을때 ""이 반환되는지 null이 반환되는지 알 수 있다.

    // Javascript의 중요성

    // 불필요한 것은 입력에서 막으면 편하다. 이건 PLC랑 똑같네 아에 입력이 안되게끔 하면 로직단에서 처리가 간편해진다.

    // 컬렉션이 느린 것은 맞으나, 객체지향에서는 배열을 직접 INDEX로 접근하는 것을 지양한다. 오히려 컬렉션을 사용.


}
