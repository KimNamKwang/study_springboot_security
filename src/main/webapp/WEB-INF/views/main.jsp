<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
        </head>

        <body>
            <div>Main</div>
            <hr>
            <div>Spring Security Area</div>
            <%-- security에서 property(principal)라는 자원을 가져온다 --%>
                <sec:authentication property="principal" var="userDetailsBean" />
                <div>userDetails : ${userDetailsBean} </div>
                <sec:authorize access="isAnonymous()"> <%-- anonymous인지 확인(로그인이 안 되어 있는지) --%>
                        <div>
                            <%-- 로그인이 안되있으므로 로그인 링크 띄움 --%>
                                <a href="/loginForm">Login</a>
                        </div>
                        <div>
                            <a href="/joinForm">Join Form</a>
                        </div>
                </sec:authorize>
                <hr>
                <%-- 로그인이 되어있을때 --%>
                    <sec:authorize access="isAuthenticated()">
                        <div>
                            ID : ${userDetailsBean.username} , Name = ${userDetailsBean.memberName}
                        </div>
                        <div>

                            <a href="/logoutForm">Logout Form</a>
                        </div>
                    </sec:authorize>
                    <hr>
        </body>

        </html>