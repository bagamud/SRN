<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Журнал УДС</title>
</head>
<body class="bg-light">
<jsp:include page="../template/_menu.jsp"/>

<div class="container-fluid">
    <div class="py-3 row">
        <main class="col-md-auto m-auto px-md-4" role="main">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Журнал</h1>
            </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Номер</th>
<%--                        <th>Недостаток</th>--%>
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
        </main>
    </div>

</div>
<jsp:include page="../template/_footer.jsp"/>
</body>
</html>

