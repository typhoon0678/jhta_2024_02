package com.typhoon0678.jspmodel2.contoller.index;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TestForm", value = "/TestForm.do")
public class TestForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("testForm 을 클라이언트가 호출요청을 보냈습니다.");

        req.setAttribute("name", "userName");
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/test.jsp");
        dispatcher.forward(req, resp);
    }
}
