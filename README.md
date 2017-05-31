# FastCampusAndroid
FastCampus에서 배운내용을 정리합니다.

## startActivityForResult -[전체보기](https://github.com/asfrom30/FastCampusAndroid/tree/master/app/src/main/java/com/doyoon/android/fastcampusandroid/week2)

이 함수로 Activity를 실행하면 실행된 Activity가 종료되면서 아래의 onActivityResult 함수를 호출해준다. 
```java
// 액티비티를 실행하는 버튼을 구분하기 위한 플래그

final int BUTTON_START = 99;
Intent intent = new Intent(this, DetailActivity.class);
startActivityForResult(intent, BUTTON_START);
```

## setResult
 호출되는 서브.class에 작성되는
```java
 
 Intent intent = new Intent(); // 이미 생성되어 있는 Activity를 사용하기 때문에 Context를 필요로하지 않는다.
 intent.putExtra("result", "결과값");
 
 // RESULT_OK 부모 Activity에 이미 정의되어 있는 플래그 값으로 처리가 성공적이라는 것을 의미한다. 
 // setResult 함수는 Acitivity에 intent를 저장하기 때문에 18번줄에서 언급한바와 같이 Context를 따로필요로 하지 않는다
 setResult(RESULT_OK, intent);
```

## onActivityResult
```java
// requestCode = startActivityForResult를 실행한 주체를 구분하기 위한 플래그
// resultCode = 결과처리의 성공여부 | RESULT_OK = 성공
// data = 결과처리의 성공여부 | RESULT_OK = 성공
@Override
if (resultCode == RESULT_OK) {
    switch (requestCode) {
        case BTN_RESULT :
            int result = data.getIntExtra("result", 0/* Default Value */);
            Toast.makeText(this, result + "Result 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
            break;
        case BTN_START:
            Toast.makeText(this, "Start 버튼을 눌렀다가 돌아옴", Toast.LENGTH_SHORT).show();
            break;
    }
}
```
        
