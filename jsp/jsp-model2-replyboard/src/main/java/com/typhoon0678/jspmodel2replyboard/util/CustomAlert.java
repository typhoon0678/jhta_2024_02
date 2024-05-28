package com.typhoon0678.jspmodel2replyboard.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomAlert {

    public static void setAlert(HttpServletRequest req, HttpServletResponse resp, String message, String location) throws ServletException, IOException {

        req.setAttribute("alertMessage", message);
        req.setAttribute("alertLocation", location);

        req.getRequestDispatcher("/WEB-INF/alert.jsp").forward(req, resp);
    }

    public static void setErrorAlert(HttpServletRequest req, HttpServletResponse resp, String errorMessage, String jsp) throws ServletException, IOException {
        req.setAttribute("error", errorMessage);

        req.getRequestDispatcher("/WEB-INF" + jsp).forward(req, resp);
    }
}
