package com.typhoon0678.jsp05.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ScriptWriter {

    public static void alert(HttpServletResponse response, String message, String href) {
        try {
            PrintWriter out = response.getWriter();

            out.print("<script>");
            out.print("alert('" + message + "');");
            out.print("location.href='" + href + "'");
            out.print("</script>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
