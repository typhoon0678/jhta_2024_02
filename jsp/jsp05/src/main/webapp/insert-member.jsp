<%@ page import="com.typhoon0678.jsp05.util.CookieManager" %><%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 5/13/24
  Time: 9:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery-3.7.1.min.js"></script>
    <style>
        html,
        body {
            height: 100%;
        }

        .form-signin {
            max-width: 330px;
            padding: 1rem;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="text"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

    </style>
</head>
<body class="bg-body-tertiary">
<%@ include file="include/header.jsp" %>

<main class="form-signin w-100 pt-5 m-auto">
    <form action="insert-member-process.jsp" method="post">
        <h1 class="h3 mb-3 fw-normal">Sign Up</h1>

        <div class="form-floating">
            <input type="text" class="form-control" name="userID" id="floatingInput" placeholder="Write ID">
            <label for="floatingInput">User ID</label>
            <button type="button" id="btn-duplicate" class="btn btn-secondary">Check Duplicate</button>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" name="userPW" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="userName" id="floatingUsername" placeholder="Write Username">
            <label for="floatingUsername">UserName</label>
        </div>

        <div class="form-floating">
            <input type="date" class="form-control" name="userBirth" id="floatingBirth">
            <label for="floatingBirth">Birth</label>
        </div>

        <button class="btn btn-primary w-100 py-2 mt-4" id="btn-signup" type="submit">Sign Up</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
</main>
<script>
    $("#btn-duplicate").on("click", function() {
        $.ajax({
            url: "idCheck.jsp",
            data: {
                userID: $("#floatingInput").val()
            },
            method: "POST",
            success: function (data) {
                if (data.count > 0) {
                    alert("Duplicated ID, Please ReWrite ID");
                    $("#floatingInput").val("");
                    $("#floatingInput").focus();
                } else {
                    const used = confirm("Available ID, Want to use?");
                    if (used) {
                        $("#floatingInput").attr("readonly", true);
                        $("#floatingPassword").focus();
                    } else {
                        $("#floatingInput").val("");
                        $("#floatingInput").focus();
                    }
                }
            }
        });
    });
</script>
</body>
</html>