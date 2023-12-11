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
  
  <!-- 서블릿 매핑 -->
  <servlet-mapping>
    <servlet-name>hello</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>
  
  (중략...)
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
- 헤더에 리프레시를 추가하는 것 말고도 head태그 사이의 meta태그를 통해서도 가능하다.
```html
out.println("<meta http-equiv='Refresh' content='1;charset=UTF-8'>");
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
- 매개변수가 선언된 서블릿에서만 사용할 수 있다.
- DD파일(web.xml)의 서블릿 배치 정보에 설정하는 방법
- 애노테이션을 사용하여 서블릿 소스 코드에 설정하는 방법
- 되도록 소스 코드에서 분리하여 외부 파일에 두는 것을 추천한다.

---
#### 20. 서블릿 초기화 매개변수 적용
- MemberUpdateServlet 클래스에 서블릿 초기화 매개변수를 적용할 것이다.
- 소스 코드에서 애노테이션으로 정의했던 @WebServlet을 삭제하고, web.xml에 서블릿 배치 정보를 생성한다.
- 소스 코드에서 DB드라이버 객체, DB드라이버 url, username, password를 서블릿 초기화 매개변수로부터 불러오도록 수정한다.

---
#### 21. 컨텍스트 초기화 매개변수
- 컨텍스트 초기화 매개변수는 모든 서블릿이 사용할 수 있다.
- 모든 서블릿에서 따로 선언해주던 JDBC 드라이버, DB연결 정보를 편리하게 설정할 수 있다.
- DD파일(web.xml)에서 서블릿에 설정했던 init-param을 분리하여 context-param으로 다시 설정한다.
- 서블릿 선언과 서블릿 URL매핑 설정을 삭제하고 소스 코드에 애노테이션 방식으로 재설정 한다.

---
#### 22. 컨텍스트 초기화 매개변수 적용
- MemberUpdateServlet에 우선적으로 적용한다.
- MemberAddServlet, MemberListServlet도 적용한다.
- MemberListServlet의 경우 GenericServlet을 HttpServlet으로 수정한다.
- 회원삭제 서블릿을 만든다.

---
#### 23. 필터
- 필터는 서블릿 실행 전후에 어떤 작업을 하고자 할때 사용하는 기술이다.
- 사전 작업: 문자집합 설정, 압축해제, 암호화된 데이터 복원, 로그 작성, 사용자 검증, 사용권한 확인 등
- 사후 작업: 응답 데이터 압축, 응답 데이터 암호화, 데이터 형식 변환 등
- DD파일(web.xml) 또는 애노테이션으로 배치 가능하다.

---
#### 24. MVC아키텍처
- MVC란 모델(Model), 뷰(View), 컨트롤러(Controller)를 구성요소로 하는 아키텍처이다.
- 새로운 기술이 등장하면 이를 활용한 시스템이 만들어지게 되고, 시스템을 만들다 보면 그 기술의 단점이 드러난다.
- MVC아키텍처는 실무 웹 애플리케이션 개발에 있어 최선의 관행으로 알려져있어 널리 사용되고 있다.
- 모델(Model): 데이터 저장소와 연동하여 사용자가 입력 또는 사용자에게 출력할 데이터를 다루는 일을 한다.
- 뷰(View): 모델이 처리한 데이터나 그 작업 결과를 가지고 사용자에게 출력할 화면을 만드는 일을 한다.
- 컨트롤러(Controller):는 모델을 호출하는 일을 하고, 모델의 작업 결과를 뷰에게 전달한다.

---
#### 25. JSP
- JSP란 Java Server Page의 약자이다.
- 서블릿의 단점을 보완하기 위한 기술이다.
- JSP엔진에 의해 .jsp파일은 .java파일이 되고 자바 컴파일러에 의해 .class이 되어 구동된다.
- 서블릿에서 작성하던 out.println("<html><head><title>회원목록")이런 코드를 더이상 작성할 필요가 없다.
- JSP엔진이 생성한 .java파일은 'HttpJspPage'인터페이스를 구현하고 있으며 최종적으로는 Servlet을 구현하고 있다.

---
#### 26. JSP 전용 태그
- 스크립트릿(Scriptlet Elements): JSP페이지 안에 자바 코드를 넣을 때 사용<br> "<% 자바 코드 %>"
- 선언문(Declarations): 서블릿 클래스의 멤버(변수나 메서드)를 선언할 때 사용<br> "<%! 멤버 변수 및 메서드 선언 %>"
- 표현식(Expressions) 문자열을 출력할 때 사용<br> "<%= 결과를 반환하는 자바 표현식 %>"

---
#### 26. MVC아키텍처 적용
- 모델(Model) 객체 생성
- 뷰(View) 객체 생성
- 서블릿에서 뷰(View) 코드 제거
- RequestDispatcher을 이용한 포워드(forward), 인클루드(include)을 통한 제어권 위임
- 인클루딩의 경우 위임이 끝난 후 제어권을 가져온다.
- 포워딩의 경우 위임이 끝난 후 제어권을 다시 가져오지 않는다.
- 회원등록, 회원수정 화면으로 접근하는 경우 각 페이지로 인클루딩 한다.
- 에러가 발생하면 에러 페이지로 포워딩 한다.
- jsp페이지에서 jsp전용태그를 통해 Header, Tail 페이지를 인클루드 할 수 있다.

---
#### 27. 데이터 보관소
- 서블릿들이 서로 협력하여 작업을 수행할 때, 데이터를 공유하는 방법으로 데이터 보관소를 사용한다.
- 총 4가지의 ServletContext, HttpSession, ServletRequest, JspContext 보관소가 있다.
- ServletContext는 웹 애플리케이션이 시작될 때 생성되어 웹 애플리케이션이 종료될 때까지 데이터가 유지된다.
- HttpSession는 클라이언트의 최초 요청 시 생성되어 브라우저를 닫을 때까지 유지된다.
- ServletRequest는 클라이언트의 요청이 들어올 때 생성되어, 클라이언트에게 응답할 때까지 유지된다.
- JspContext는 JSP페이지를 실행하는 동안만 유지된다.

---
#### 28. ServletContext 보관소 활용
- 서블릿마다 web.xml에 설정한 context-param의 DB연결 정보를 불러와 사용하고 있다.
- 모든 서블릿이 공통 사용할 수 있도록 DB연결 정보를 ServletContext보관소에 담는 공용 서블릿(AppInitServlet)을 생성한다.
- 공용 서블릿을 web.xml파일에 서블릿 선언을 해야한다.
- 이 서블릿은 어떤 요청에 의해 생성되는 것이 아니기 때문에 load-on-startup태그로 웹 애플리케이션 시작 시 자동으로 생성되도록 했다.

---
#### 29. HttpSession 보관소 활용
- 로그인, 로그아웃 기능을 추가하여 HttpSession 보관소 활용 해본다.
- 로그인을 했을 때 Header.jsp에 로그인한 사용자의 이름이 나타나고, 로그아웃 버튼을 통해 HttpSession 보관소에 들어있는 사용자를 삭제한다.

---
#### 30. ServletRequest 보관소 활용
- 로그인, 회원 목록 출력 등 ServletRequest 보관소를 통해 데이터를 이미 공유시키고 있었다.
- LoginServlet와 LogInForm.jsp 사이에서 doGet()의 매개변수를 그대로 넘겨주고 있다.
```java
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(request, response);
	}
```

---
#### 31. JspContext 보관소 활용
- JspContext 보관소는 JSP 페이지를 실행할 때 생성되고, 완료되면 사라진다.
- JSP 페이지 내부에서만 사용될 데이터를 공유할 때 사용한다.
- JspContext 보관소를 통해 데이터를 공유하기 원하는 자는 '커스텀 태그 핸들러'이다.
- '커스텀 태그 핸들러'는 MVC아키텍처로 개발 시에는 별 필요가 없다.

---
#### 32. JSP 액션 태그
- JSP 페이지를 작성할 때, 가능한 자바 코드의 삽입을 최소화하는 것이 유지 보수에 좋다.
- 이를 위해 JSP에서는 다양한 JSP 전용 태그를 제공하고 있으며 이 태그 집합을 'JSP 액선'이라 한다.

---
#### 33. JSP 액션 태그의 사용
- 뷰 컴포넌트(.jsp)에서 자바 코드를 JSP 액션 태그로 수정한다.
- jsp:useBean 액션 태그는 application, session, request, page 보관소에 저장된 자바 객체를 꺼낼 수 있다.
- 만약 보관소에 저장된 객체가 없는 경우 새로 생성하여 해당 보관소에 저장한다.
- 그 이유로 Header.jsp에서 로그인 여부를 Member객체의 존재 여부가 아니라 Member.getEamil()로 수정했다.

---
#### 34. EL 표기법
- EL(Expression Language)은 콤마(,)와 대괄호([])을 사용하여 자바 빈의 프로퍼티나 맵, 리스트, 배열의 값을 보다 쉽게 꺼내게 해주는 기술이다.
- 스태틱(static)으로 선언된 메서드를 호출할 수도 있다.
- EL은 ${}와 #{}를 사용하여 값을 표현한다.
- ${}은 '즉시 적용(immediate evalluation)', #{}은 '지연 적용(deferred evaluation)'이라 한다.
- #{}은 JSF(JavaServer Faces)기술에서 UI를 만들 때 많이 사용한다.
- ${}은 JSP가 실행될 때 JSP페이지에 즉시 반영된다.
- #{}은 시스템에서 필요하다고 판단될 때 그 값을 사용한다.
- 이런 이유로 #{}은은 객체 속성에서 값을 꺼내기보다는, 사용자가 입력한 값을 객체의 속성에 담는 용도로 많이 사용한다.
- EL도 jsp:useBean태그처럼 네 군데 보관소에서 값을 꺼낼 수 있다.
- 다만 다른 점은 EL로는 객체를 생성할 수 없다. 즉, 객체를 찾다가 없으면 null을 반환한다.<br>
  pageScope        -> JspContext<br>
  requestScope     -> ServletRequest<br>
  sessionScope     -> HttpSession<br>
  applicationScope -> ServletContext

---
#### 35. JSTL 적용
- EL과 더불어 JSTL을 사용하면 JSP 페이지에서 자바 코드를 완전히 제거할 수 있다.
- JSTL태그를 사용하려면 'JSTL API'와 'JSTL Implementation'를 준비해야 한다.

---
#### 36. DAO만들기
- 서블릿이 하는 일 중에서 DB와 연동하여 데이터를 처리하는 부분을 분리해본다.
- DAO는 보통 하나의 DB테이블이나 DB뷰에 대응한다.
- DAO는 DB나 파일, 메모리 등을 이용하여 데이터를 CRUD하는 역할을 수행한다.
- 회원 등록, 목록 조회, 수정, 삭제, 로그인 서블릿에 DAO를 적용하였다.

---
#### 37. 리스너(Listener)
- ServletContextListener 인터페이스를 구현해서 웹 애플리케이션의 시작과 종료 이벤트를 처리한다.
- 시작은 contextInitialized()에 작성하고 종료는 contextDestroyed()에 작성한다.
- web.xml에 AppInitServlet 서블릿 선언을 삭제하고, 리스너 정보를 등록한다.

```xml
<!-- 삭제 -->
<servlet>
  <servlet-name>AppInitServlet</servlet-name>
  <servlet-class>lesson02.servlets.AppInitServlet</servlet-class>
  <load-on-startup>1</load-on-startup>
</servlet>

<!-- 등록 -->
<listener>
  <listener-class>lesson02.listener.ContextLoaderListener</listener-class>
</listener>
```

---
#### 38. DB커넥션풀
- DB커넥션풀이란 DB커넥션 객체를 여러 개 생성하여 풀(Pool)에 담아 놓고 필요할 때 꺼내 쓰는 방식이다.
- DB커넥션 객체를 여러 요청이 공유할 시, 오류 발생하여 롤백(rollback) 필요할 때, 다른 요청들까지 영향을 받는다.
- DB커넥션 객체를 생성하는데 DB는 사용자 인증과 권한 검사를 수행하고 요청 처리를 위한 준비 작업을 하느라 지연이 발생한다.
- DB커넥션풀을 직접 생성해보면서 어떤 원리로 이루어지는지 확인한다.

--- 
#### 39. javax.sql 패키지, DataSource
- javax.sql 패키지는 java.sql의 패키지의 기능을 보조하기 위해 만든 확장 패키지이다.
- 서버쪽 데이터 소스에 대한 접근을 쉽게 하고, 다양한 방법으로 데이터를 다룰 수 있는 API를 제공한다.
- DriverManager를 대체할 수 있는 DataSource인터페이스 제공
- Connection 및 Statement 객체의 풀링
- 분산 트랜잭션 처리
- Rowsets의 지원
  
---













