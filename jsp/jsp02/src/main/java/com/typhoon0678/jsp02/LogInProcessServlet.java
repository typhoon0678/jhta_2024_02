package com.typhoon0678.jsp02;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login-process")
public class LogInProcessServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userID");
        String password = req.getParameter("userPW");

        PrintWriter out = resp.getWriter();

        resp.setContentType("text/html;charset=utf-8");

        if (id != null && password != null && id.equals("user") && password.equals("1234")) {
            RequestDispatcher rd = req.getRequestDispatcher("/hello.jsp");
            req.setAttribute("userID", id);

            rd.forward(req, resp);
        } else {
            out.println("<script>");
            out.println("alert(\"아이디와 비밀번호를 확인해주세요.\");");
            out.println("history.back();");
            out.println("</script>");
        }
    }
}
