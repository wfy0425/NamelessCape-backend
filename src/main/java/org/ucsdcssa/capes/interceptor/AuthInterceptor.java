//package org.ucsdcssa.capes.interceptor;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Objects;
//
//public class AuthInterceptor implements HandlerInterceptor {
//
//    @Value("${token.value}")
//    String fileToken = null;//输入JSON文件路径
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().print("Empty Token");
//            return false;
//        }
//
//        if(fileToken==null||fileToken.length()==0|| !fileToken.equals(token)){
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.getWriter().print("Wrong Token");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
