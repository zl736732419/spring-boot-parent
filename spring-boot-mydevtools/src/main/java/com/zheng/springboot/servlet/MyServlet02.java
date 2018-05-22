package com.zheng.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需要使用@ServletComponentScan
 * @Author zhenglian
 * @Date 2018/5/17 13:46
 */
@WebServlet(urlPatterns = "/servlet02")
public class MyServlet02 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("servlet02");
        resp.getWriter().flush();
    }
}
