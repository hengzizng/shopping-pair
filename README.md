# spring-shopping-precourse

## 기능사항 

### 상품
- [ ] 상품은 ID, Name, Price, ImageUrl을 가진다.
- [x] 상품 ID는 마지막 값에서 1을 자동 증가시킨다.
- [x] 상품 ImageUrl은 http://나 https://로 시작하고 이미지 확장자로 끝나야 한다. (.png, ~~~)
- [x] 상품 가격은 0보다 크거나 같아야 한다.
- [x] 상품 생성할 땐 Name, Price, ImageUrl가 필요하다.
- [ ] 상품 수정할 땐 ID는 필수고 Name, Price, ImageUrl 중에 하나라도 필요하다.
- [ ] 상품 삭제할 땐 ID가 필요하다.
- [ ] 상품 조회할 때는 전체 상품을 보여준다.




public class Product {
    private String name; // 15자 이하  
    private Long price; // 가격은 음수일수 없고
    
    public Product(name, price) {
        // 여기서 올바른 값인지 체크 
        this.name = name;
        this.price = price;
    }
}

public class Product {
    private Name name;
}

class Name {
}

class Price {
    
}