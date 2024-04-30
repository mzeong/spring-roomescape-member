# 방탈출 사용자 예약

## 1단계 - 예외 처리와 응답 

- [ ] 발생할 수 있는 예외 상황에 대한 처리를 하여, 사용자에게 적절한 응답을 한다.
  - [x] 시간 생성 시 시작 시간에 유효하지 않은 값이 입력되었을 때
  - [ ] 예약 생성 시 예약자명, 날짜, 시간에 유효하지 않은 값이 입력 되었을 때
  - [x] 특정 시간에 대한 예약이 존재하는데, 그 시간을 삭제하려 할 때
  - [x] 특정 시간이 존재하지 않는데, 그 시간을 삭제하려 할 때
  - [x] 특정 예약이 존재하지 않는데, 그 예약을 삭제하려 할 때
- [ ] 아래와 같은 서비스 정책을 반영한다. 
  - [ ] 지나간 날짜와 시간에 대한 예약 생성은 불가능하다. 
  - [ ] 중복 예약은 불가능하다.
  - [ ] 중복 예약 시간은 불가능하다.

