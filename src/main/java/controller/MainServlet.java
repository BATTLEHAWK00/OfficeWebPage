package controller;

import utils.database.DBConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet()
public class MainServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("���ݿ����ӳ�ʼ��...");
        DBConnector.Init();
        System.out.println("���ݿ����ӳ�ʼ�����");
    }
}
