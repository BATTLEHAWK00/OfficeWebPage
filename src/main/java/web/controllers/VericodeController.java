package web.controllers;

import bean.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import utils.LoggerUtil;
import utils.VeriCodeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class VericodeController {
    @RequestMapping("/vericode")
    @ResponseStatus
    public void getVericode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("vericode") != null) {
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
