package web.controllers;

import bean.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.LoggerUtil;
import utils.VeriCodeUtil;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/vericode")
public class VericodeController {

    @RequestMapping(value = "/check", produces = "application/json")
    public ResponseEntity<String> checkVericode(@RequestParam("code") String code, HttpSession session) {
        if (!code.isEmpty() && session.getAttribute("vericode") != null)
            if (code.toLowerCase().equals(session.getAttribute("vericode"))) {
                session.removeAttribute("vericode");
                return new ResponseEntity<>(Response.OK.toJsonStr(),HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(Response.Fail.toJsonStr(),HttpStatus.ACCEPTED);
            }
        return new ResponseEntity<>(Response.Fail.toJsonStr(),HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/get", produces = {"image/png"})
    public byte[] getVericode(HttpSession session) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        OutputStream outputStream = new BufferedOutputStream(bytes);
        String vericode = VeriCodeUtil.getVerificationCode(80, 5, outputStream);
        session.setAttribute("vericode", vericode.toLowerCase());
        LoggerUtil.Log("—È÷§¬Î«Î«Û£∫" + vericode);
        return bytes.toByteArray();
    }
}
