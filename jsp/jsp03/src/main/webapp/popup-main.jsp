<%@ page import="com.typhoon0678.jsp03.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/9/24
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cookieValue = CookieManager.readCookie(request, "oneDayCookie");
%>
<html>
<head>
    <title>Title</title>
    <style>
        #popup {
            padding: 10px;
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            position: fixed;
            left: 100px;
            top: 100px;
        }

        #popup h2 {
            font-size: 18px;
            margin: 0;
        }

        #popup .content {
            min-height: 150px;
            background-color: #ccc;
            margin-bottom: 10px;
        }
    </style>
    <script src="js/jquery-3.7.1.min.js"></script>
</head>
<body>
<h1>Popup Main</h1>
<button class="delete-cookie">Delete Cookie</button>
<% if (!cookieValue.equals("off")) { %>
<aside id="popup">
    <h2>POPUP</h2>
    <div class="content">
        content
    </div>
    <div class="btn-box">
        <label>
            <input type="checkbox" id="today-check" value="off">
            <span>Today not open this</span>
        </label>
        <button class="btn-close">Close</button>
    </div>
</aside>
<% } %>
<script>
    $("#popup .btn-close").on("click", function () {
        const todayCheck = $("#today-check");
        const isChecked = todayCheck.is(":checked");
        console.log(isChecked);
        $("#popup").hide();
        if (isChecked) {
            $.ajax({
                url: "popup-make.jsp",
                method: "GET",
                data: {
                    today: todayCheck.val()
                }
            });
        }
    });

    $(".delete-cookie").on("click", function() {
        $.ajax({
            url: "popup-live.jsp",
            method: "GET",
        });
    });
</script>
</body>
</html>
