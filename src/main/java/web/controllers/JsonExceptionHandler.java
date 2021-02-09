package web.controllers;

import bean.Response;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class JsonExceptionHandler implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
        if (e instanceof HttpRequestMethodNotSupportedException)
            httpServletResponse.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        else if(e instanceof HttpServerErrorException)
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        else
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        httpServletResponse.getWriter().write(new Response("Fail",e.getMessage()).toJsonStr());
        return mv;
    }
}