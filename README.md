# transaction
transaction에 대해 공부합니다.


## 2024-12-02 Transactional.java 주석 읽기

jakarta.transaction.Transactional 어노테이션
- CDI 관리하는 빈에서 트랜잭션 경계를 선언적으로 제어하는 기능을 어플리케이션에 제공
- Jakrta EE 명세에 따라 관리되는 빈으로 정의된 클래스의 경우
  - 클래스 수준과 메서드 수준에서 모두 어노테이션 사용 가능
  - 메서드 수준의 주석이 클래스 수준의 주석을 재정의 >> 테스트해보기

Transactional 인터셉터는 비즈니스 메서드 호출에만 개입
라이프사이클 이벤트에는 개입하지 않음
- 라이프 사이클 이벤트란 객체가 생성, 소멸 되는 과정에 호출 되는 메서드
- JPA 엔티티의 @PostConstruct, @PreDestory, @PostLoad 등
- 이런 메서드는 트랜 잭션 적용 대상이 아님
라이프사이클 메서드는 지정되지 않는 트랜잭션 컨텍스트에서호출 됨



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
