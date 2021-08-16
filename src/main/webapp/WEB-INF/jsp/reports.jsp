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

