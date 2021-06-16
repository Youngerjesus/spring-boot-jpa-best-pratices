# OneToOne 연관관계 설정 

***

OneToOne 연관관계를 설정할 때 키의 주인을 어디에 둘 것인가? 에 대한 내용을 주로 살펴볼 것이다.

다음과 같은 엔터티들이 있다고 가정해보자.

##### Coupon
```java
@Entity
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;
    
    @Column(name = "is_use")
    private boolean isUse = false; 

    @OneToOne(?)
    private Order order;
}
```

##### Order 

````java
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id; 
    
    private double price; 
    
    @OneToOne(?)
    private Coupon coupon; 
}
````

## 외래 키는 어디에 두어야 하는가? 

일대다 관계에서는 다 쪽에서 외래 키를 관리 하게 되지만 상대적으로 일대일 관계 설정에는 외래키를 어디에 두어야 할지 생각해야 한다.

JPA 에서는 외래 키를 가지는 쪽이 연관관계의 주인이 되고 키를 등록 수정 삭제 할 수 있기 떄문에 중요한 사항이다.  

여기서는 해당 분야 도메인에 대한 전문적인 지식으로 나눴고 이때 일대일 관계가 나왔다고 가정하겠다. 

그러면 이제부터 고려해야 할 건 __OneToOne 에서 OneToMany가 될 가능성이 있는가?__ 를 생각해보자. 

Many 가 될 가능성이 꽤 높다면 그쪽에서 키를 관리하는 것이 좋다. 

이 부분이 되게 애매하다면 조회를 할 때 생각해보자. JPA 에서는 OneToOne 관계에서 N+1 문제가 터질 수 있다. 

이 이유로는 __키가 없는 쪽 엔터티에서 조회를 할 경우에는 지연 로딩이 되지 않는다.__ 

애초에 키가 없기 때문에 대상 객체가 있는지 없는지 알 수 없기 때문에 쿼리를 날려봐야 하므로 무조건 즉시 로딩만 된다.

JPA 사용 표준으로는 무조건 지연 로딩을 해두는 것이므로 즉시 로딩을 해도 괜찮은 쪽에 키를 두는 것이 좋다.

그 다음으로 고려해야 할 사항으로는 뭐 __키가 없는 쪽에서 INSERT 쿼리를 날릴 때는 연관관계 주인 쪽에서 키를 업데이트 해야하니까 UPDATE 쿼리도 같이 나간다.__ 는 정도가 있는데 
이는 크게 중요한 이슈는 아니지만 고려해보면 좋을 것 같다. 

## Coupon 과 Order 중 키의 주인은 누구인가? 

도메인에 대한 지식이 있다면 하나의 주문이 여러개의 쿠폰 사용할 수도 있기 때문에 Coupon 쪽에 키를 두는 것이 좋다. 

이렇게 하면 주문 객체를 가지고 올 때 N+1 문제가 터지니까 대량의 주문 객체를 가지고 올때는 조인을 패치조인을 걸어서 한번에 잘 가져오도록 하자.  

 

  

 


