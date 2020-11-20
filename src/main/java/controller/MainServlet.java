package controller;

import utils.database.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet()
public class MainServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("数据库连接初始化...");
        DBConnector.Init();
        System.out.println("数据库连接初始化完毕");
    }
}
