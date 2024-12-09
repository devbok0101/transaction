## RuntimeException.class
* RuntimeException은 JVM(Java Virtual Machine)의 정상적인 작동 중에 발생할 수 있는 예외의 슈퍼클래스입니다.
  * RuntimeException and its subclasses are unchecked exceptions.
* uncheckedException에 해당


## Exception.class
* Exception 클래스와 RuntimeException의 하위 클래스가 아닌 모든 하위 클래스는 checked exceptions이다.
  * The class Exception and any subclasses that are not also subclasses of RuntimeException are checked exceptions.

```java

/**
 * checked exception에 해당
 * rollbackfor 옵션 사용시 롤백 대상
 */
public class CheckedException extends Exception {
    public CheckedException(String message) {
        super(message);
    }
}


/**
 * unchecked exception에 해당
 * rollbackfor 사용시, CheckedException.class를 추가하지 않는 경우 롤백 안됨.
 */
public class CheckedException extends RuntimeException {
    public CheckedException(String message) {
        super(message);
    }
}

```

## rollbackfor의 주석을 읽어 보자
* By default, a transaction will be rolled back 
* on RuntimeException and Error but not on checked exceptions (business exceptions).
-> 기본적으로 RuntimeExcetpion과 Error만 롤백한다.

## 만약, checkedException에도 롤백을 원한다면?
*  @Transactional(rollbackFor = Exception.class)
*  @Transactional(rollbackFor = {직접 생성한 비즈니스 Exception}.class)