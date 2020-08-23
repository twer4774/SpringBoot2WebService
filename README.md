# Spring Boot2 WebService <br>
## 스프링 부트와 AWS로 혼자구현하는 웹 서비스 책을 보며.. <br>
### 책의 환경과 실제 시스템의 환경의 차이로 에러가 발생하는 것들을 정리 <br>
- p72. lombok과 그래들 버전에 관한 오류 <br>
https://github.com/jojoldu/freelec-springboot2-webservice/issues/2 <br>
내 컴퓨터의 그래들 버전 6. 책에 집필한 내용은 4.10.2까지 그래들을 지원해준다고 한다. <br>
gradle Package -> gradle-wrapper.properties -> distributionUrl=https\://services.gradle.org/distributions/gradle-4.10.2-bin.zip <br>
위와 같이 그래들을 변경해주면 오류 해결 - lombok만 그래들에 맞추는 방법도 있지만, 추후의 예제들도 버전4에서 실습을 권장 
