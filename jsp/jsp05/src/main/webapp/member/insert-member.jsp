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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/bootstrap.bundle.min.js"></script>
    <script src="../js/jquery-3.7.1.min.js"></script>
    <style>
        html,
        body {
            height: 100%;
        }

        .form-signin {
            max-width: 480px;
            padding: 1rem;
        }

        .form-floating {
            margin-top: 16px;
            margin-bottom: 16px;
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
<%@ include file="../include/header.jsp" %>

<main class="form-signin w-100 pt-5 m-auto">
    <form action="${pageContext.request.contextPath}/member/insert-member-process.jsp" method="post">
        <h1 class="h3 mb-3 fw-normal">Sign Up</h1>

        <div class="form-floating">
            <input type="text" class="form-control" name="userID" id="floatingInput" placeholder="Write ID" check-id="">
            <label for="floatingInput">User ID</label>
            <button type="button" id="btn-duplicate" class="btn btn-secondary">Check Duplicate</button>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" name="userPW" id="floatingPassword" placeholder="Password">
            <label for="floatingPassword">Password</label>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" name="userPW2" id="floatingPassword2"
                   placeholder="Check Password">
            <label for="floatingPassword2">Check Password</label>
            <div class="password-correct text-success"></div>
            <div class="password-incorrect text-warning"></div>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="userName" id="floatingUsername" placeholder="Write Username">
            <label for="floatingUsername">UserName</label>
        </div>

        <div class="form-floating">
            <input type="email" class="form-control" name="userEmail" id="floatingEmail" placeholder="Write Email">
            <label for="floatingEmail">Email</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="userPostCode" id="floatingPostCode" placeholder="Post Code"
                   readonly>
            <label for="floatingPostCode">POST CODE</label>
            <button type="button" id="btn-postcode" class="btn btn-secondary">Find POST CODE</button>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="userAddress" id="floatingAddress" placeholder="Address"
                   readonly>
            <label for="floatingAddress">ADDRESS</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" name="userDetailAddress" id="floatingDetailAddress"
                   placeholder="Detail Address">
            <label for="floatingDetailAddress">DETAIL ADDRESS</label>
        </div>

        <div class="form-floating">
            <input type="date" class="form-control" name="userBirth" id="floatingBirth">
            <label for="floatingBirth">Birth</label>
        </div>

        <button class="btn btn-primary w-100 py-2 mt-4" id="btn-signup" type="submit">Sign Up</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
    </form>
</main>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
    const idInput = $("#floatingInput");
    const pwInput = $("#floatingPassword");
    const pw2Input = $("#floatingPassword2");

    pw2Input.on("keyup", function () {
        if (pwInput.val() === pw2Input.val()) {
            $(".password-correct").text("Correct");
            $(".password-incorrect").text("");
        } else {
            $(".password-correct").text("");
            $(".password-incorrect").text("Incorrect");
        }
    });

    $("#btn-signup").on("click", function () {
        if (idInput.attr("check-id") !== "checked") {
            alert("Check ID Duplicate");
            idInput.focus();
            return false;

        } else if (idInput.val().trim() === "") {
            alert("ID required");
            idInput.focus();
            return false;

        } else if (pwInput.val().trim() === "") {
            alert("Password required");
            pwInput.focus();
            return false;

        } else if (pwInput.val() !== pw2Input.val()) {
            alert("Check Password");
            pwInput.focus();
            return false;

        } else if ($("#floatingUsername").val().trim() === "") {
            alert("Username required");
            $("#floatingUsername").focus();
            return false;

        } else if ($("#floatingEmail").val().trim() === "") {
            alert("Email required");
            $("#floatingEmail").focus();
            return false;

        }
    });


    $("#btn-duplicate").on("click", function () {
        $.ajax({
            url: "idCheck.jsp",
            data: {
                userID: idInput.val()
            },
            method: "POST",
            success: function (data) {
                if (data.count > 0) {
                    alert("Duplicated ID, Please ReWrite ID");
                    idInput.val("");
                    idInput.focus();
                } else {
                    const used = confirm("Available ID, Want to use?");
                    if (used) {
                        idInput.attr("readonly", true);
                        idInput.attr("check-id", "checked");
                        pwInput.focus();
                    } else {
                        idInput.val("");
                        idInput.focus();
                    }
                }
            }
        });
    });


    $("#btn-postcode").on("click", makePostcode);

    function makePostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }

                    addr += extraAddr;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#floatingPostCode").val(data.zonecode);
                $("#floatingAddress").val(addr);
                // 커서를 상세주소 필드로 이동한다.
                $("#floatingDetailAddress").focus();
            }
        }).open();
    }

</script>
</body>
</html>