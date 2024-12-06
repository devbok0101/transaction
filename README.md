# transaction
transaction에 대해 공부합니다.


## 2024-12-02 Transactional.java 주석 읽기

jakarta.transaction.Transactional 어노테이션
- CDI 관리하는 빈에서 트랜잭션 경계를 선언적으로 제어하는 기능을 어플리케이션에 제공
- Jakrta EE 명세에 따라 관리되는 빈으로 정의된 클래스의 경우
  - 클래스 수준과 메서드 수준에서 모두 어노테이션 사용 가능
  - 메서드 수준의 주석이 클래스 수준의 주석을 재정의 >> 테스트해보기

Transactional 인터셉터는 비즈니스 메서드 호출에만 개입
### 왜 @Transactional 은 인터셉터야?
- AOP를 기반으로 동작하기 때문
- AOP의 프록시 패턴을 사용
- 메서드가 호출될 경우, 프록시가 해당 메서드를 가로챔(intercept) 트랜잭션 시작, 커밋, 롤백 트랜잭션 관리 처리


### 라이프사이클 이벤트에는 개입하지 않음
- 라이프 사이클 이벤트란 객체가 생성, 소멸 되는 과정에 호출 되는 메서드
- JPA 엔티티의 @PostConstruct, @PreDestory, @PostLoad 등
- 이런 메서드는 트랜 잭션 적용 대상이 아님
라이프사이클 메서드는 지정되지 않는 트랜잭션 컨텍스트에서호출 됨

### 이 때는 IllegalStatException이 발생합니다.
- @Transactional 어노테이션이 적용된 빈
- Transactional.TxType이 NOT_SUPPORTED 또는 NEVER가 아닌 경우
-> UserTransaction 인터페이스 호출시 발생

### TransactionSynchronizationRegistry의 사요은 @Transactional 어노테이션과 상관 없음

**TransactionSynchronizationRegistry이 뭐야?**
- 현재 실행중인 트랜잭션 관련 정보 제공 및 트랜잭션 동기화 작업 지원 인터페이스
- TransactionSynchronizationRegistry을 직접 사용시, 코드가 복잡해질 가능성 높음
  - 가능하면 프레임워크에 책임을 위임하자
- @Transactional, TransactionSynchronizationManager, TransactionTemplate 을 쓰는게 스프링 환경에 더 적합.

### CDI 관리란?
- Contexts and Dependency Injection
- Jakarta EE의 의존성 주입 및 컨텍스트 관리 기능

*의존성 주입(Dependency Injection)*
- 컨테이너가 필요한 객체 자동으로 주입
- @Autowired @Inject

*컨텍스트 관리(Context Management)*
- CDI는 객체 생명주기(스코프)를 관리
- 스코프 어노테이션 : @RequestScoped, @SessionScoped, @ApplicationScoped

*CDI 관리 빈 *
- CDI 컨테이너가 생성, 초기화, 소멸, 주입 관리하는 객체 
- 클래스가 @Named, @Dependent @ApplicationScoped 같은 주석으로 정의되면 CDI 컨테이너에서 관리되는 빈으로 동작


### CDI 관리 빈?
- CDI 컨테이너가 의존성과 생명주기를 자동으로 관리하는 객체
- 개발자는 객체 관리에 신경쓰지 않고, 비즈니스 로직에 집중 가능


### 
