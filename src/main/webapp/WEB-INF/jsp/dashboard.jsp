<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Журнал УДС</title>
</head>
<body class="bg-light">
<jsp:include page="../template/_menu.jsp"/>

<nav id="sidebarMenu"
     class="col-md-3 col-lg-2 d-md-block mb-auto d-flex mt-5 flex-column sidebar flex-shrink-0 p-3 text-dark bg-light">
    <hr>
    <form action="${pageContext.request.contextPath}/dashboard" method="get" name="fastFilter">
        <div class="form-check form-switch">
            <input class="form-check-input" type="radio" name="filter" id="all" value="all"
                   onchange="this.form.submit()" checked>
            <label class="form-check-label" for="all">Все</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="radio" name="filter" id="inWork" value="inWork"
                   onchange="this.form.submit()">
            <label class="form-check-label" for="inWork">В работе</label>
        </div>
        <div class="form-check form-switch">
            <input class="form-check-input" type="radio" name="filter" id="underControl" value="underControl"
                   onchange="this.form.submit()">
            <label class="form-check-label" for="underControl">На контроле</label>
        </div>
    </form>
    <hr>
    <form method="post"
          name="form" id="formId">
        <div>
            <label for="department">Подразделение</label>
            <select class="form-control custom-select d-block w-100"
                    id="department"
                    name="department"
            ${form_disable}>
                <option value=""></option>
                ${departments}
            </select>
        </div>
        <div>
            <label for="foundPeriodStart">Дата выявления</label>
            <input class="form-control" id="foundPeriodStart" type="date" name="foundPeriodStart"
                   value="${formRequest.foundPeriodStart.toLocalDate()}">
        </div>
        <div>
            <label for="foundPeriodEnd">Дата выявления</label>
            <input class="form-control" id="foundPeriodEnd" type="date" name="foundPeriodEnd"
                   value="${formRequest.foundPeriodEnd.toLocalDate()}">
        </div>
        <div>
            <label for="shortcoming">Характер недостатка</label>
            <select class="form-control custom-select d-block w-100"
                    id="shortcoming"
                    name="shortcoming" disabled>
                <option value="${formRequest.shortcoming.id}">${formRequest.shortcoming.shortTitle}</option>
                ${shortcomings}
            </select>
        </div>
        <div>
            <label for="measures">Принятые меры</label>
            <select class="form-control custom-select d-block w-100"
                    id="measures"
                    name="measures" disabled>
                <option value=""></option>
                ${measure}
            </select>
        </div>
        <div>
            <label for="status">Статус</label>
            <select class="form-control custom-select d-block w-100" id="status" name="status" disabled>
                <option value="${formRequest.status.id}">${formRequest.status.title}</option>
                ${status}
            </select>
        </div>

        <hr>

        <div class="row">
            <div class="col-auto btn-group">
                <input class="btn btn-primary" type="submit" value="Поиск"
                       formaction="${pageContext.request.contextPath}/dashboard/filter"/>
                <input class="btn btn-primary" type="button"
                       onclick="location.href='${pageContext.request.contextPath}/dashboard'"
                       value="Очистить"/>
            </div>
        </div>
    </form>
</nav>


<div class="col-9 ms-sm-auto col-lg-10 overflow-scroll" style="max-height: 89vh">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr class="text-center">
                <th>Номер</th>
                <th>Дата создания</th>
                <%--                <th>Недостаток</th>--%>
                <th>Подразделение</th>
                <th>Кем выявлено</th>
                <th>Участок УДС</th>
                <th>Дата выявления</th>
                <th>Характер недостатка</th>
                <%--                        <th>Результаты замеров</th>--%>
                <%--                        <th>Примененные средства измерения</th>--%>
                <%--                        <th>Принятые меры</th>--%>
                <%--                        <th>Кому / в отношении кого</th>--%>
                <%--                        <th>Дата принятия мер</th>--%>
                <th>Статус</th>
                <th>Контроль</th>

            </tr>
            </thead>
            <tbody>
            ${srnAllsb}
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../template/_footer.jsp"/>
<script>document.getElementById('${fastFilterRadio}').setAttribute('checked', true)</script>
</body>
</html>

