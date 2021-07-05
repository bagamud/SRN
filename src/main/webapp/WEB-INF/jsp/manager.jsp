<%@ page import="ru.ic.information_portal.entity.Journal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.ic.information_portal.entity.Users" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <jsp:include page="../template/_metaStyle.jsp"/>
    <title>Журнал УДС</title>
</head>
<body class="bg-light">
<jsp:include page="../template/_menu.jsp"/>
<div class="container-fluid">
    <div class="py-3 row">
        <main class="col-md-9 m-auto col-lg-10 px-md-4" role="main">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Карточка</h1>
            </div>
            <div class="row">

                <div class="col-md-7 order-md-1">
                    <form class="needs-validation" action="${pageContext.request.contextPath}/manager" method="post"
                          name="form" id="formId">
                        <div class="row">
                            <div class=" col-md-4 mb-3">
                                <label for="id">Номер</label>
                                <div class="input-group">
                                    <input class="form-control <%if (request.getAttribute("error") != null) out.print("is-invalid");%>"
                                           id="id" type="number" min="0" pattern="^[0-9]+$" name="id"
                                           value="${srn.id}">
                                    <div class="invalid-feedback">
                                        Неправильный номер записи
                                    </div>
                                    <div class="input-group-append">
                                        <button class="btn-light" type="submit" formaction="/manager/get"
                                                formmethod="get" formnovalidate>Поиск
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <input class="form-control"
                                   id="username" type="hidden" name="username" hidden
                                   value="${pageContext.request.remoteUser}">
                            <div class="col-md-4 mb-3">
                                <label for="createDate">Дата создания карточки</label>
                                <input class="form-control" id="createDate" type="date" name="createDate"
                                       value="${srn.createDate.toLocalDate()}" disabled>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="closeDate">Дата закрытия заявки</label>
                                <input class="form-control" id="closeDate" name="closeDate" type="text"
                                       value="${srn.closeDate}" disabled>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="department">Подразделение</label>
                                <select class="form-control custom-select d-block w-100"
                                        id="department"
                                        name="department" required>
                                    <option value="${srn.department.id}">${srn.department.title}</option>
                                    ${departments}
                                </select></div>
                            <div class="col-md-6 mb-3">
                                <label for="foundWho">Кем выявлено</label>
                                <input class="form-control" id="foundWho"
                                       required
                                       type="text" name="foundWho"
                                       value="${srn.foundWho}">
                            </div>
                            <div class=" col-md-8 mb-3">
                                <label for="foundPlace">Участок УДС</label>
                                <div class="input-group">
                                    <input class="form-control"
                                           id="foundPlace" type="text" name="foundPlace"
                                           value="${srn.foundPlace}" required>
                                </div>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="foundDate">Дата выявления</label>
                                <input class="form-control" id="foundDate" type="date" name="foundDate"
                                       value="${srn.foundDate.toLocalDate()}" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <label for="shortcoming">Характер недостатка</label>
                                <select class="form-control custom-select d-block w-100"
                                        id="shortcoming"
                                        name="shortcoming" required>
                                    <option value="${srn.shortcoming.id}">${srn.shortcoming.title}</option>
                                    ${shortcomings}
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8 mb-3">
                                <label for="devices">Примененные средства измерения</label>
                                <select class="form-control custom-select d-block w-100"
                                        id="devices"
                                        name="devices">
                                    ${devices}
                                </select>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="comment">Результаты замеров</label>
                                <input class="form-control" id="comment" name="comment"
                                       type="tel" value="${srn.comment}">
                            </div>
                        </div>
                        <hr class="mb-3">
                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="measures">Принятые меры</label>
                                <select class="form-control custom-select d-block w-100"
                                        id="measures"
                                        name="measures">
                                    ${measure}
                                </select>
                            </div>
                            <div class="col-md-7 mb-3">
                                <label for="sendTo">Кому / в отношении кого</label>
                                <input class="form-control" id="sendTo" name="sendTo"
                                       value="${srn.sendTo}">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="measuresDate">Дата принятия мер</label>
                                <input class="form-control" id="measuresDate" type="date" name="measuresDate"
                                       value="${srn.measuresDate.toLocalDate()}">
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="result">Результат</label>
                                <select class="form-control custom-select d-block w-100" id="result" name="result">
                                    ${result}
                                </select>
                            </div>
                        </div>
                        <hr class="mb-5">
                        <div class="row">
                            <div class="col-auto btn-group-lg">
                                <input class="btn btn-primary" type="submit" value="Сохранить"
                                       formaction="${pageContext.request.contextPath}/manager/add"/>
                                <input class="btn btn-primary" type="button"
                                       onclick="location.href='${pageContext.request.contextPath}/manager'"
                                       value="Новый"/>
                                <input class="btn btn-primary" type="submit"
                                       formaction="${pageContext.request.contextPath}/manager/fix"
                                       value="Выполнено"/>
                                <input class="btn btn-primary" type="button"
                                       onclick="location.href='${pageContext.request.contextPath}/dashboard'"
                                       value="Назад"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-5 order-md-2 mb-4">
                    <div class="card">
                        <div class="card-header bg-info text-center text-light text-uppercase">
                            Файлы
                        </div>
                        <div class="card-body" style="overflow-y: scroll; max-height: 59vh">
                            ${data}
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header bg-info text-center text-light text-uppercase">
                            Журнал изменений
                        </div>
                        <div class="card-body" style="overflow-y: scroll; max-height: 95px">
                            <table class="table-sm">
                                <%
                                    ArrayList<Journal> journalAll = (ArrayList<Journal>) request.getAttribute("journal");
                                    if (journalAll != null) {
                                        for (Journal journal : journalAll) {
                                            out.println("<tr><td>" + journal.getEntryDate()
                                                    + "</td><td>" + journal.getSrn().getUsername()
                                                    + "</td></tr>");
                                        }
                                    }
                                %>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </main>
    </div>
</div>
<script>
    document.getElementById('department').value = ${user.department.id};
    let depCode = ${user.department.code};
    document.getElementById('department').disabled = (depCode !== 1140000);
</script>
<jsp:include page="../template/_footer.jsp"/>
</body>
</html>
