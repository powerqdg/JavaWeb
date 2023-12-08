# JavaWeb

#### 1. 서블릿(Servlet) 프로그래밍
 - 자바에서는 웹 개발할 수 있도록 '서블릿(Servlet)'이라는 기술을 제공하고 있다.
 - 보통 HttpServlet 클래스를 상속 받아야 한다고 생각하는 경우가 많다.
 - 사실은 Servlet 인터페이스를 이해하는 것이 중요하다.
 - 서블릿 기술을 이용하여 개발하는 것을 '서블릿 프로그래밍'이라 한다.

---
#### 2. 서블릿(Servlet)
  - 서블릿을 이해하려면 'CGI'를 알아야 한다.
  - CGI(Common Gateway Interface)라하며 웹 서버와 프로그램 사이의 데이터를 주고 받는 규칙을 말한다.
  - CGI규칙에 따라서 웹 서버와 데이터를 주고 받도록 작성된 프로그램을 'CGI프로그램'이라 한다.
  - CGI프로그램은 C, C++, Java와 같은 컴파일 언어와 Perl, PHP, Python, VBScript 등 스크립트 언어로도 작성할 수 있다.
  - 자바로 만든 CGI프로그램을 서블릿(Servlet)이라 한다.

---
#### 3. 서블릿 컨테이너(Servlet Container)
 - 서블릿만의 특징은 웹 서버와 직접 데이터를 주고 받지 않으며, 전문 프로그램에 의해 관리된다는 것이다.
 - 서블릿의 생성과 실행, 소멸 등 생명주기를 관리하는 전문 프로그램을 '서블릿 컨테이너(Servlet Container)'이라 한다.
 - 서블릿 컨테이너가 존재함에 있어 개발자는 CGI규칙을 알아야 할 필요가 없고, 서블릿과 서블릿 컨테이너 사이의 규칙을 알아야 한다.

---
#### 4. javax.servlet.Servlet 인터페이스
 - HelloWorld 클래스는 javax.servlet.Servlet 인터페이스를 구현했다.
 - 서블릿 인터페이스를 구현하려면 init(), service(), destroy(), getServletInfo(), getServletConfig() 메서드를 작성해야 한다. 
 - 각 메서드에 호출 시 메서드명을 출력하도록 작성했다.
 - 서버를 실행하고 요청을 보냈면 콘솔창에 'init'와 'service'가 출력되었다.
 - 다시 요청을 보내면 이번에는 'service'만 출력되었다.
 - 서버를 종료하면 'destroy'가 출력되었다.
 - 서블릿의 생명주기를 서블릿 인터페이스를 구현하여 확인하였다.

 ---
 #### 5. GenericServlet 추상 클래스
 - HelloWorld2 클래스는 javax.servlet.GenericServlet 추상 클래스를 상속받았다.
 - GenericServlet 추상 클래스는 javax.servlet.Servlet 인터페이스를 구현한 클래스이다.
 - GenericServlet 추상 클래스에서 불필요한 메서드는 미리 구현하고 있기 때문에 service()만 작성하면 된다.
 - 서버를 실행하고 요청을 보내면 'service'만 출력되는 것을 확인하였다.
 - HelloWorld.java클래스에서 5가지 메서드를 구현하는 것보다 편해졌다.

---
#### 6. web.xml
 - Servlet 인터페이스, GenericServlet 추상 클래스 예제를 만들면서 web.xml에 서블릿을 배치하는 것을 설명 안했다.
 - web.xml은 배치 기술서(Deployment Descriptor) 또는 'DD파일'이라 한다. 웹 애플리케이션의 배치 정보를 담고 있다.
 - 서블릿을 만들면 서블릿 컨테이너가 찾을 수 있도록 web.xml에 서블릿을 선언해주어야 한다.
 - 아래 그림과 같이 서블릿명과 서블릿 클래스를 정의하는 '서블릿 선언' 부분과 서블릿명과 url패턴을 정의하는 '서블릿 매핑'부분을 작성했었다.
```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>JavaWeb</display-name>
  
  <!-- 서블릿 선언 -->
  <servlet>
    <servlet-name>hello</servlet-name>
    <servlet-class>lesson01.servlet.HelloWorld</servlet-class>
  </servlet>
  <servlet>
    <servlet-name>hello2</servlet-name>
    <servlet-class>lesson01.servlet.HelloWorld2</servlet-class>
  </servlet>
  <servlet>
    <servlet-name>calc</servlet-name>
    <servlet-class>lesson01.servlet.CalculatorServlet</servlet-class>
  </servlet>
  
  <!-- 서블릿 매핑 -->
  <servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>hello2</servlet-name>
    <url-pattern>/hello2</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>calc</servlet-name>
    <url-pattern>/calc</url-pattern>
  </servlet-mapping>
  
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```

---
#### 7. CalculatorServlet 클래스
 - 문자 집합을 설정하지 않으면 아래와 같이 한글이 깨져서 출력된다. 기본값은 ISO-8859-1이다.
```
a=4, b=2? ???? ???.  // 의 계산결과 입니다.
a + b = 6
a - b = 2
a * b = 8
a / b = 2.0
a % b = 0
```
 - 문자 집합은 두 방식으로 설정할 수 있다. 
```java
// 나뉘서
response.setContentType("text/plain");
response.setCharacterEncoding("UTF-8");

// 한 번에
response.setContentType("text/plain;charset=UTF-8");
```

---
#### 8. @WebServlet 애노테이션
 - Servlet 3.0 사양부터는 애노테이션으로 서블릿 배치 정보를 설정할 수 있다.
 - HelloWorld, HelloWorld2, CalculatorServlet 클래스에 @WebServlet을 적용했다.
 - web.xml에서도 등록했던 서블릿의 배치 정보를 지워보고 정상 작동하는지 확인했다.
```java
package lesson01.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {
	  (중략)
	}
}

```
```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>JavaWeb</display-name>
  
  <!-- 서블릿 선언 -->
  
  <!-- 서블릿 매핑 -->
  
  
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
```

---
#### 9. DB 연결하여 회원목록 조회
- DB를 연결하기 위해서 ojdbc6.jar 파일을 다운로드 한다. (https://mvnrepository.com/artifact/oracle/ojdbc6/11.2.0.3)
- ojdbc6.jar 파일을 '/WEB-INF/lib'에 위치해놓는다.
- lesson2.servlets.MemberListServlet 클래스를 만들고 GenericServlet를 상속한다.
- 서블릿 배치 정보를 등록한다. (애노테이션 방법을 사용함)
- DriverManager클래스의 registerDriver()로 오라클드라이버 객체를 등록한다.
- DriverManager클래스에서 Connection 객체를 가져오고, Statement를 준비시킨다.
- 준비한 Statement로 Query를 수행하여 ResultSet을 얻는다.
- 반드시 출력 스트림을 얻기 전에 먼저 setContentType("text.html;charset=UTF-8")를 호출한다.
- 출력 스트림으로 html을 출력한다.
- 사용한 자원은 반드시 해제하는 코드를 작성해야 한다. 해제는 역순으로 ResultSet > Statement > Connection 처리.

--- 
#### 10. HttpServlet 클래스
- 클라이언트의 요청이 들어오면 서블릿 컨테이너는 service()를 호출한다.
- HttpServlet는 GenericServlet클래스를 상속 받았다. 즉, 요청이 오면 HttpServlet의 service()가 호출된다.
- HttpServlet의 service()는 클라이언트 요청 방식에 따라 doGet(), doPost(), doPut(), ...등을 호출한다.

---
#### 11. GET 요청 발생
- 브라우저 주소창에 URL을 입력하여 요청한 경우
- a태그를 클릭하여 요청한 경우
- form태그의 method가 'GET'이거나 없는 경우(기본값 GET)에서 요청한 경우

---
#### 12. Statement와 PreparedStatement
|항목|Statement|PreparedStatement|
|:-|:-|:-|
|실행속도|질의할 때마다 컴파일|미리 컴파일<br>매개변수 값만 추가하여 서버에 전송<br>특히 여러번 반복 시, 실행속도가 빠름|
|바이너리<br>데이터 전송|불가능|가능|
|프로그래밍<br>편의성|SQL문에 매개변수 값이 포함되어 복잡|SQL문에 매개변수 값이 분리되어 있어 편리|

---
#### 13. 신규회원 추가
- MemberListServlet클래스에서 '신규 회원'이라는 <a> 태그를 추가하였다.
- MemberAddServlet클래스를 생성하고 'HttpServlet'을 상속 받았다.
- HttpServlet의 doGet()과 doPost()를 구현하였다.
- 회원목록 화면에서 <a>태그를 클릭하면 MemberAddServlet의 doGet()이 호출된다.
- 회원추가 화면에서 회원 입력폼을 작성하고 '추가'버튼을 누르면 doPost()이 호출된다.
- doGet()이 호출되면 회원 입력폼 화면을 생성하여 출력한다.
- doPost()이 호출되면 회원 입력폼에 작성한 값을 불러와 신규회원을 등록한다.

---
#### 14. 한글 입력 깨짐
- 신규회원을 추가할 경우 이름에 한글을 입력하고 등록하면 회원목록에서 한글이 깨져 출력된다.
![image](https://github.com/powerqdg/JavaWeb/assets/63497408/633b0312-22f4-4415-8b33-5d3c95b32344)
- 브라우저는 서버에 요청을 보낼 때, 브라우저의 기본 문자집합으로 인코딩하여 보낸다.
- 서버에서는 getParameter()하기 전에 요청 데이터의 문자집합을 설정한 후 빼와야 한다.
- 그렇지 않으면 매개변수 값이 기본적으로 'ISO-8859-1'로 인코딩 되어있다고 가정한 후 반환한다.
```java
request.setCharacterEncoding("UTF-8");
```

#### 15. GET 요청 매개변수의 한글 깨짐 해결책
- setCharacterEncoding()의 호출은 쿼리스트링에서는 적용되지 않는다.
- 톰캣 서버의 server.xml을 수정해줘야 한다.
```xml
<Connector connectionTimeout="20000" maxParameterCount="1000" port="8080"
   protocol="HTTP/1.1" redirectPort="8443"
   URIEncoding="UTF-8"/>
```

---
#### 16. 리프래시(Refresh)
- 신규회원을 추가한 후 등록성공 화면을 보여주고 1초 후에 회원목록으로 돌아간다.
- 그 이유는 Refresh을 헤더에 설정했기 때문이다.
```java
response.setHeader("Refresh", "1;url=list"); 또는
response.addHeader("Refresh", "1;url=list");
```
- 헤더에 리프레시를 추가하는 것 말고도 meta태그를 통해서도 가능하다.
```html
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
out.println("<html><head><title>회원 등록 결과</title>");
out.println("<meta http-equiv='Refresh' content='1;charset=UTF-8'>");
out.println("</head>");
if (cnt > 0) {
	out.println("<body><h1>등록에 성공했습니다.</h1>");
} else {
	out.println("<body><h1>등록에 실패했습니다.</h1>");
}
out.println("</body></html>");

// response.setHeader("Refresh", "1;url=list");
```

---
#### 17. 리다이렉트(Redirect)
- 리프래시(Refresh)는 회원추가 후 결과를 브라우저에 출력한 후 회원목록으로 돌아갔다.
- 리다이렉트(Redirect)는 결과를 출력하지 않고 즉시 회원목록으로 돌아갈 수 있다.
- 헤더 정보의 응답 상태 코드가 '200'이 아닌 '302'로 되어있다.
- 헤더 정보의 Location에는 이동할 페이지 URL이 있다.
- 응답 본문도 없다.
- 따라서, 리다이렉트(Redirect)를 하는 경우 본문 코드를 작성할 필요가 없으며, 작성해도 보내지 않는다.

---
#### 18. 회원정보 상세조회/수정
- 회원목록의 이름을 출혁하는 HTML에 a태그 추가하여 회원정보 상세조회를 할 수 있도록 했다.
- a태그를 통해 '/member/update'를 요청하면 MemberUpdateServlet의 doGet()이 호출된다.
- 이름, 이메일을 수정하고 수정 버튼을 클릭하면 doPost()가 호출되어 DB를 통해 수정 후 회원목록으로 리다이렉트 된다.

---
#### 19. 서블릿 초기화 매개변수
- DD파일(web.xml)의 서블릿 배치 정보에 설정하는 방법
- 애노테이션을 사용하여 서블릿 소스 코드에 설정하는 방법
- 되도록 소스 코드에서 분리하여 외부 파일에 두는 것을 추천한다.

---
#### 20. 서블릿 초기화 매개변수 적용
- MemberUpdateServlet 클래스에 서블릿 초기화 매개변수를 적용할 것이다.
- 소스 코드에서 애노테이션으로 정의했던 @WebServlet을 삭제하고, web.xml에 서블릿 배치 정보를 생성한다.
- 소스 코드에서 DB드라이버 객체, DB드라이버 url, username, password를 서블릿 초기화 매개변수로부터 불러오도록 수정한다.
```java
public class MemberUpdateServlet extends HttpServlet {
	(중략)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		(중략)
		try {
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					this.getInitParameter("url"),
					this.getInitParameter("username"),
					this.getInitParameter("password"));
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>JavaWeb</display-name>
  
  <!-- 서블릿 선언 -->
  <servlet>
    <servlet-name>update</servlet-name>
    <servlet-class>lesson02.servlets.MemberUpdateServlet</servlet-class>
    <init-param>
      <param-name>driver</param-name>
      <param-value>oracle.jdbc.driver.OracleDriver</param-value>
    </init-param>
    <init-param>
      <param-name>url</param-name>
      <param-value>jdbc:oracle:thin:@localhost:1521:orcl</param-value>
    </init-param>
    <init-param>
      <param-name>username</param-name>
      <param-value>scott</param-value>
    </init-param>
    <init-param>
      <param-name>password</param-name>
      <param-value>tiger</param-value>
    </init-param>
  </servlet>
  
  <!-- 서블릿 매핑 -->
  <servlet-mapping>
    <servlet-name>update</servlet-name>
    <url-pattern>/member/update</url-pattern>
  </servlet-mapping>
  
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
</web-app>
``` 


