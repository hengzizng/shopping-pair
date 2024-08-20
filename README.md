# spring-shopping-precourse

## 기능사항 

### 상품
- [x] 상품은 ID, Name, Price, ImageUrl을 가진다.
- [x] 상품 ID는 마지막 값에서 1을 자동 증가시킨다.
- [x] 상품 ImageUrl은 http://나 https://로 시작하고 이미지 확장자로 끝나야 한다. (.png, ~~~)
- [x] 상품 가격은 0보다 크거나 같아야 한다.
- [x] 상품 생성할 땐 Name, Price, ImageUrl가 필요하다.
- [x] 상품 수정할 땐 ID는 필수고 Name, Price, ImageUrl 중에 하나라도 필요하다.
- [x] 상품 삭제할 땐 ID가 필요하다.
- [x] 상품 조회할 때는 전체 상품을 보여준다.

---
- [ ] 상품을 일부 특수 문자는 허용하지 않는다.
    * (할인) 아메리카노!
    * 아메리카노 *할인
    * ...

```gherkin
Given 상품 이름이 "(할인) 아메리카노!" 일 때
When 상품을 생성하면
Then 400 Bad Request를 응답한다
And "올바르지 않은 상품 이름입니다."라고 응답한다.
```
```gherkin
Given 기존 상품이 존재할 때
  And 변경하고자 하는 상품 이름이 "아메리카노 *할인" 일 때
When 상품을 수정하면
Then 400 Bad Request를 응답한다
And "올바르지 않은 상품 이름입니다."라고 응답한다.
```

---
- [ ] 상품은 비속어를 포함하지 않는다.
```gherkin
Given 상품 이름이 "fuck 아메리카노" 일 때
When 상품을 생성하면
Then 400 Bad Request를 응답한다
And "올바르지 않은 상품 이름입니다."라고 응답한다.
```
```gherkin
Given 기존 상품이 존재할 때
  And 변경하고자 하는 상품 이름이 "아메리카노 fuck" 일 때
When 상품을 수정하면
Then 400 Bad Request를 응답한다
And "올바르지 않은 상품 이름입니다."라고 응답한다.
```