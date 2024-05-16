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
        <li><a href="/" class="nav-link px-2">Home</a></li>
        <li><a href="/member/list-member.jsp" class="nav-link px-2">List Member</a></li>
        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<li><a href=\"/board/list.jsp\" class=\"nav-link px-2\">Board</a></li>");
            }
        %>
        <li><a href="#" class="nav-link px-2">About</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<a href=\"/member/logout-member-process.jsp\" class=\"btn btn-outline-primary me-2\">LogOut</a>");
            } else {
                out.print("<a href=\"/member/login.jsp\" class=\"btn btn-outline-primary me-2\">Login</a>");
            }
        %>

        <%
            if (!userID.isEmpty() && !userName.isEmpty()) {
                out.print("<a href=\"/member/info.jsp\" class=\"btn btn-primary me-2\">Info</a>");
            } else {
                out.print("<a href=\"/member/insert-member.jsp\" class=\"btn btn-primary\">Sign-up</a>");
            }
        %>

    </div>
</header>
