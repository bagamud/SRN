<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Информационный портал УГИБДД</title>
    <link href="${pageContext.request.contextPath}/css/features.css" rel="stylesheet">
</head>
<body class="text-center">
<main>

    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="/"
               class="d-flex align-items-center justify-content-center mb-3 mb-md-0 text-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#bootstrap"></use>
                </svg>
                <span class="fs-4">Информационный портал УГИБДД</span>
            </a>

        </header>
    </div>
    <div class="b-example-divider"></div>

    <div class="container px-4 py-5" id="hanging-icons">
        <div class="row g-4 py-5 row-cols-1 row-cols-lg-3">
            <div class="col d-flex align-items-start">
                <div class="icon-square bg-light text-dark flex-shrink-0 me-3">
                    <img src="${pageContext.request.contextPath}/img/1.25.png" width="95%"/>
                </div>
                <div>
                    <h2>Журнал учета недостатков УДС</h2>
                    <p>В данном журнале очуществляется формирование и работа
                        с записями о выявленных недостатках в содержании улично-дорожной сети
                    </p>
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">
                        Перейти
                    </a>
                </div>
            </div>
            <%--            <div class="col d-flex align-items-start">--%>
            <%--                <div class="icon-square bg-light text-dark flex-shrink-0 me-3">--%>
            <%--                    <svg class="bi" width="1em" height="1em">--%>
            <%--                        <use xlink:href="#cpu-fill"/>--%>
            <%--                    </svg>--%>
            <%--                </div>--%>
            <%--                <div>--%>
            <%--                    <h2>Инструкции</h2>--%>
            <%--                    <p>В данном разделе размещены инструкции</p>--%>
            <%--                    <a href="#" class="btn btn-close">--%>
            <%--                        Перейти--%>
            <%--                    </a>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <div class="col d-flex align-items-start">--%>
            <%--                <div class="icon-square bg-light text-dark flex-shrink-0 me-3">--%>
            <%--                    <svg class="bi" width="1em" height="1em">--%>
            <%--                        <use xlink:href="#tools"/>--%>
            <%--                    </svg>--%>
            <%--                </div>--%>
            <%--                <div>--%>
            <%--                    <h2>Отчеты</h2>--%>
            <%--                    <p>Комплексные отчеты по линиям службы</p>--%>
            <%--                    <a href="#" class="btn btn-close">--%>
            <%--                        Перейти--%>
            <%--                    </a>--%>
            <%--                </div>--%>
            <%--            </div>--%>
        </div>
    </div>

    <div class="b-example-divider"></div>

</main>

<script src="./webjars/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>

</body>
</html>