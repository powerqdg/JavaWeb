# JavaWeb

#### 1. 서블릿(Servlet) 프로그래밍
 - 자바에서는 웹 개발할 수 있도록 '서블릿(Servlet)'이라는 기술을 제공하고 있다.
 - 보통 HttpServlet 클래스를 상속 받아야 한다고 생각하는 경우가 많다.
 - 사실은 Servlet 인터페이스를 이해하는 것이 중요하다.
 - 서블릿 기술을 이용하여 개발하는 것을 '서블릿 프로그래밍'이라 한다.

#### 2. 서블릿(Servlet)
  - 서블릿을 이해하려면 'CGI'를 알아야 한다.
  - CGI(Common Gateway Interface)라하며 웹 서버와 프로그램 사이의 데이터를 주고 받는 규칙을 말한다.
  - CGI규칙에 따라서 웹 서버와 데이터를 주고 받도록 작성된 프로그램을 'CGI프로그램'이라 한다.
  - CGI프로그램은 C, C++, Java와 같은 컴파일 언어와 Perl, PHP, Python, VBScript 등 스크립트 언어로도 작성할 수 있다.
  - 자바로 만든 CGI프로그램을 서블릿(Servlet)이라 한다.

#### 3. 서블릿 컨테이너(Servlet Container)
 - 서블릿만의 특징은 웹 서버와 직접 데이터를 주고 받지 않으며, 전문 프로그램에 의해 관리된다는 것이다.
 - 서블릿의 생성과 실행, 소멸 등 생명주기를 관리하는 전문 프로그램을 '서블릿 컨테이너(Servlet Container)'이라 한다.
 - 서블릿 컨테이너가 존재함에 있어 개발자는 CGI규칙을 알아야 할 필요가 없고, 서블릿과 서블릿 컨테이너 사이의 규칙을 알아야 한다.

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


