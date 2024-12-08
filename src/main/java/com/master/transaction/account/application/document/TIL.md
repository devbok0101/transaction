## RuntimeException.class
* RuntimeException은 JVM(Java Virtual Machine)의 정상적인 작동 중에 발생할 수 있는 예외의 슈퍼클래스입니다.
* uncheckedException에 해당


## Exception.class
* Exception 클래스와 RuntimeException의 하위 클래스가 아닌 모든 하위 클래스는 checked exceptions이다.

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
