<%@ page contentType="text/html;charset=UTF-8" %>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <div class="navbar-brand col-md-3 col-lg-2 me-0 px-3 d-flex align-items-center text-decoration-none">
        <a class="nav-item text-decoration-none align-content-center text-white" href="/">
            <img alt="Russian Federation"
                 height="30"
                 src="${pageContext.request.contextPath}/img/mvd.png"
                 style="margin-left: 10px"
                 width="52"/>
        </a>
    </div>
    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li>
            <a class="nav-link px-2 text-white" href="${pageContext.request.contextPath}/dashboard">Журнал</a>
        </li>
        <li>
            <a class="nav-link px-2 text-white" href="${pageContext.request.contextPath}/manager">Карточка</a>
        </li>
        <li>
            <a class="nav-link px-2 text-white" href="${pageContext.request.contextPath}/reports">Отчеты</a>
        </li>
    </ul>

    <label class="nav-link px-2 text-white" id="principal">${user.name}</label>

    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="${pageContext.request.contextPath}/logout">Выйти</a>
        </div>
    </div>
</header>