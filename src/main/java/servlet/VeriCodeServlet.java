package servlet;

import bean.Response;
import utils.LoggerUtil;
import utils.VeriCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/vericode")
public class VeriCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("code") != null) {
            Response res = new Response();
            if (session.getAttribute("vericode") == null) {
                resp.setStatus(400);
                return;
            }
            String inputCode = req.getParameter("code").toLowerCase();
            if (session.getAttribute("vericode").equals(inputCode)) {
                res.SetMessage("OK");
                session.removeAttribute("vericode");
            } else {
                res.SetMessage("Fail");
                LoggerUtil.Log("验证码验证失败");
            }
            resp.getWriter().write(res.toJson());
            return;
        }
        resp.setContentType("image/png");
        String vericode = VeriCodeUtil.getVerificationCode(80, 5, resp.getOutputStream());
        if (session.getAttribute("vericode") != null) {
            session.removeAttribute("vericode");
        }
        session.setAttribute("vericode", vericode.toLowerCase());
        LoggerUtil.Log("验证码请求：" + vericode);
    }
}
