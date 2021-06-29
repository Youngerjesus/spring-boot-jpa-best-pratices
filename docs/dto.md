## DTO 컬렉셭 조회 최적화  

***

##### 기존 컬렉션 조회 코드 문제점 N + 1 문제가 발생함.  
```java
public List<OrderQueryDto> findOrderQueryDtos() {
    List<OrderQueryDto> result = findOrders(); 
    result.forEach(o -> {
        List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId()); // Query N번 
        o.setOrderItems(orderItems);
    });
    return result; 
}

private List<OrderQueryDto> findOrders() {
    return em.createQuery(
        "select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id, m.name , o.orderDate, o.status. d.address)" + 
        " from Order o" +
        " join o.member m" +
        " join o.delivery d", OrderQueryDto.class)
        .getResultList();
}
```

- 이 경우 findOrders() 에서 Order 5개를 가지고왔다면 5번의 추가적인 쿼리가 나가게 된다. 

##### 컬렉션 최적화 코드 

```java
public List<OrderQueryDto> findAllByDto_Optimization(){
    List<OrderQueryDto> result = findOrders(); 
    
    List<Long> orderIds = result.stream()
            .map(o -> o.getOrderId())
            .collect(Collectors.toList());
    
    List<OrderItemQueryDto> orderItems = em.createQuery(
            "select new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" + 
            " from OrderItem oi" +
            " join oi.item i" +
            " where oi.order.id in :orderIds",
            ,OrderItemQueryDto.class)
        .setParameter("orderIds", orderIds)
        .getResultList();
    
    Map<Long, List<OrderItemQueryDto>> orderItemMap =  orderItems.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId())); 
    
    result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())))
}
```

- 쿼리 두번을 날려서 총 데이터를 메모리에 가져오고 Dto 를 만드는 방법이다. 이때 IN 절을 통해서 해당 조건에 맞는 값들만 가지고온다.    

