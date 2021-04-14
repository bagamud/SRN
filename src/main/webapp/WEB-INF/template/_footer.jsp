<%@ page import="java.util.List" %>
<%@ page import="ru.ic.information_portal.entity.Users" %>
<%
    List<Users> users = (List<Users>) request.getAttribute("users");
    if (users != null) {
        for (Users user : users) {
            if (user.getUsername().equals(request.getUserPrincipal().getName()))
                out.print("<input type=\"text\" id=\"userId\" value=\"" + user.getId() + "\" hidden/>\n" +
                        "<input type=\"text\" id=\"userName\" value=\"" + user.getName() + "\" hidden/>"
                );
        }
    }
%>
<script>
    const userId = document.getElementById('userId').value;
    const userName = document.getElementById('userName').value;
    document.getElementById('principal').innerText = userName;
</script>
<script src="../webjars/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="../webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="../webjars/popper.js/1.16.0/popper.min.js"></script>