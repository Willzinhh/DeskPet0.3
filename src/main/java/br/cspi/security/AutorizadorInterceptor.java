//package br.cspi.security;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//@Component
//public class AutorizadorInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws Exception {
//        String url = request.getRequestURI();
//
//        if (url.equals("/") || url.equals("/login")  || url.equals("/Cadastro")) {
//            return true;
//        }
//
//        if (url.startsWith("/static/css/")) {
//            return true;
//        }
//
//        if (request.getSession().getAttribute("usuarioId") == null) {
//            response.sendRedirect("/login");
//            return false;
//        }
//
//        return true;
//    }
//}
