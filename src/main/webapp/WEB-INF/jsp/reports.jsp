<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Журнал УДС</title>

</head>
<body class="bg-white">
<jsp:include page="../template/_menu.jsp"/>
<nav id="sidebarMenu"
     class="col-md-3 col-lg-2 d-md-block mb-auto d-flex mt-5 flex-column sidebar flex-shrink-0 p-3 text-dark bg-light">
    <hr>

    <%--    <form action="${pageContext.request.contextPath}/reports" method="post"--%>
    <%--          name="form" id="formId" ${form_hidden}>--%>
    <%--        <div>--%>
    <%--            <label for="department">Подразделение</label>--%>
    <%--            <select class="form-control custom-select d-block w-100"--%>
    <%--                    id="department"--%>
    <%--                    name="department"--%>
    <%--            ${form_disable}>--%>
    <%--                <option value=""></option>--%>
    <%--                ${departments}--%>
    <%--            </select>--%>
    <%--        </div>--%>
    <%--        <div>--%>
    <%--            <label for="foundPeriodStart">Дата выявления</label>--%>
    <%--            <input class="form-control" id="foundPeriodStart" type="date" name="foundPeriodStart"--%>
    <%--                   value="${formRequest.foundPeriodStart.toLocalDate()}">--%>
    <%--        </div>--%>
    <%--        <div>--%>
    <%--            <label for="foundPeriodEnd">Дата выявления</label>--%>
    <%--            <input class="form-control" id="foundPeriodEnd" type="date" name="foundPeriodEnd"--%>
    <%--                   value="${formRequest.foundPeriodEnd.toLocalDate()}">--%>
    <%--        </div>--%>
    <%--        <div>--%>
    <%--            <label for="shortcoming">Характер недостатка</label>--%>
    <%--            <select class="form-control custom-select d-block w-100"--%>
    <%--                    id="shortcoming"--%>
    <%--                    name="shortcoming">--%>
    <%--                <option value="${formRequest.shortcoming.id}">${formRequest.shortcoming.title}</option>--%>
    <%--                ${shortcomings}--%>
    <%--            </select>--%>
    <%--        </div>--%>
    <%--        <div>--%>
    <%--            <label for="measures">Принятые меры</label>--%>
    <%--            <select class="form-control custom-select d-block w-100"--%>
    <%--                    id="measures"--%>
    <%--                    name="measures">--%>
    <%--                <option value=""></option>--%>
    <%--                ${measure}--%>
    <%--            </select>--%>
    <%--        </div>--%>
    <%--        <div>--%>
    <%--            <label for="status">Статус</label>--%>
    <%--            <select class="form-control custom-select d-block w-100" id="status" name="status">--%>
    <%--                <option value="${formRequest.status.id}">${formRequest.status.title}</option>--%>
    <%--                ${status}--%>
    <%--            </select>--%>
    <%--        </div>--%>


    <ul class="nav nav-pils flex-column mb-auto">
        <li>
            <a class="nav-link link-dark" href="${pageContext.request.contextPath}">Общий отчет</a>
        </li>
    </ul>
    <hr>

    <%--        <div class="row">--%>
    <%--            <div class="col-auto btn-group">--%>
    <%--                <input class="btn btn-primary" type="submit" value="Поиск"--%>
    <%--                       formaction="${pageContext.request.contextPath}/dashboard/filter"/>--%>
    <%--                <input class="btn btn-primary" type="button"--%>
    <%--                       onclick="location.href='${pageContext.request.contextPath}/dashboard'"--%>
    <%--                       value="Очистить"/>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    </form>--%>

</nav>

<div class="col-9 ms-sm-auto col-lg-10 overflow-scroll" <%--style="max-height: 89vh"--%>>
    <%--        <main class="m-auto col-lg-9 px-md-4">--%>
    <%--            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">--%>
    <%--                <h1 class="h2">Отчеты</h1>--%>
    <%--                <div class="btn-toolbar mb-2 mb-md-0">--%>
    <%--                    <div class="btn-group me-2">--%>
    <%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Скачать</button>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <div class="table-responsive">
        ${report}
    </div>
    <%--        </main>--%>
</div>
<jsp:include page="../template/_footer.jsp"/>
</body>
</html>

