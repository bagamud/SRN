<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Журнал УДС</title>

</head>
<body class="bg-white">
<jsp:include page="../template/_menu.jsp"/>

<div class="container-fluid">
    <div class="row">
        <%--        <nav id="sidebarMenu" class="col-md-6 col-lg-3 d-md-block bg-light">--%>
        <%--            <form action="${pageContext.request.contextPath}/reports" method="post"--%>
        <%--                  name="form" id="formId">--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-md-8 mb-3">--%>
        <%--                        <label for="department">Подразделение</label>--%>
        <%--                        <select class="form-control custom-select d-block w-100"--%>
        <%--                                id="department"--%>
        <%--                                name="department">--%>
        <%--                            <option value=""></option>--%>
        <%--                            ${departments}--%>
        <%--                        </select>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-md-4 mb-3">--%>
        <%--                        <label for="foundPeriodStart">Дата выявления</label>--%>
        <%--                        <input class="form-control" id="foundPeriodStart" type="date" name="foundDate"--%>
        <%--                               value="${srn.foundDate.toLocalDate()}">--%>
        <%--                    </div>--%>
        <%--                    <div class="col-md-4 mb-3">--%>
        <%--                        <label for="foundPeriodEnd">Дата выявления</label>--%>
        <%--                        <input class="form-control" id="foundPeriodEnd" type="date" name="foundDate"--%>
        <%--                               value="${srn.foundDate.toLocalDate()}">--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-md-8 mb-3">--%>
        <%--                        <label for="shortcoming">Характер недостатка</label>--%>
        <%--                        <select class="form-control custom-select d-block w-100"--%>
        <%--                                id="shortcoming"--%>
        <%--                                name="shortcoming">--%>
        <%--                            <option value="${srn.shortcoming.id}">${srn.shortcoming.title}</option>--%>
        <%--                            ${shortcomings}--%>
        <%--                        </select>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-md-8 mb-3">--%>
        <%--                        <label for="measures">Принятые меры</label>--%>
        <%--                        <select class="form-control custom-select d-block w-100"--%>
        <%--                                id="measures"--%>
        <%--                                name="measures">--%>
        <%--                            <option value=""></option>--%>
        <%--                            ${measure}--%>
        <%--                        </select>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-md-8 mb-3">--%>
        <%--                        <label for="status">Результат</label>--%>
        <%--                        <select class="form-control custom-select d-block w-100" id="status" name="status">--%>
        <%--                            <option value=""></option>--%>
        <%--                            ${status}--%>
        <%--                        </select>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--                <hr class="mb-5">--%>
        <%--                <div class="row">--%>
        <%--                    <div class="col-auto btn-group-lg">--%>
        <%--                        <input class="btn btn-primary" type="submit" value="Сформировать"--%>
        <%--                               formaction="${pageContext.request.contextPath}/report"/>--%>
        <%--                        <input class="btn btn-primary" type="button"--%>
        <%--                               onclick="location.href='${pageContext.request.contextPath}/report'"--%>
        <%--                               value="Очистить"/>--%>
        <%--                    </div>--%>
        <%--                </div>--%>
        <%--            </form>--%>
        <%--        </nav>--%>

        <main class="col-md-9 m-auto col-lg-9 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Отчеты</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group me-2">
                        <button type="button" class="btn btn-sm btn-outline-secondary">Скачать</button>
                    </div>
                </div>
            </div>

            <div class="table-responsive">
                ${report}
            </div>
        </main>
    </div>
</div>
<jsp:include page="../template/_footer.jsp"/>
</body>
</html>

