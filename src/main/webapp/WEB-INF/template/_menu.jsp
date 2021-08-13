<%@ page contentType="text/html;charset=UTF-8" %>

<header class="p-1 bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none" href="/">
                <img alt="Russian Federation"
                     height="30"
                     src="${pageContext.request.contextPath}/img/mvd.png"
                     style="margin-left: 10px"
                     width="52"/>
            </a>

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

            <div class="text-end">
                <button class="btn btn-outline-light"
                        onclick="location.href='${pageContext.request.contextPath}/logout'">
                    <svg class="bi bi-power" fill="currentColor" height="1em" viewBox="0 0 16 16" width="1em"
                         xmlns="http://www.w3.org/2000/svg">
                        <path d="M5.578 4.437a5 5 0 1 0 4.922.044l.5-.866a6 6 0 1 1-5.908-.053l.486.875z"
                              fill-rule="evenodd"></path>
                        <path d="M7.5 8V1h1v7h-1z" fill-rule="evenodd"></path>
                    </svg>
                </button>
            </div>
        </div>
    </div>
</header>
