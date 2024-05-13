<%
    String userID = CookieManager.readCookie(request, "userID");
    String userName = CookieManager.readCookie(request, "userName");
%>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
            <h2>JSP05</h2>
        </a>
    </div>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/" class="nav-link px-2 link-secondary">Home</a></li>
        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<li><a href=\"info.jsp\" class=\"nav-link px-2\">Info</a></li>");
            } else {
                out.print("<li><a href=\"login.jsp\" class=\"nav-link px-2\">Info</a></li>");
            }
        %>
        <li><a href="../list-member.jsp" class="nav-link px-2">List Member</a></li>
        <li><a href="#" class="nav-link px-2">FAQs</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<a href=\"logout-member-process.jsp\" class=\"btn btn-outline-primary me-2\">LogOut</a>");
            } else {
                out.print("<a href=\"login.jsp\" class=\"btn btn-outline-primary me-2\">Login</a>");
            }
        %>

        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<a href=\"info.jsp\" class=\"btn btn-primary me-2\">Info</a>");
            } else {
                out.print("<a href=\"insert-member.jsp\" class=\"btn btn-primary\">Sign-up</a>");
            }
        %>

    </div>
</header>
