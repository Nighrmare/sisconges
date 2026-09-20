package com.sisconges.sisconges.interceptor;

import com.sisconges.sisconges.config.AppSessionConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor
{
    @Autowired
    private AppSessionConfig appSessionConfig;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception
    {

        HttpSession session = request.getSession(false);

        if (session == null)
        {
            response.sendRedirect("/login");
            return false;
        }

        Object usuarioId =
                session.getAttribute("usuarioId");

        Object instanceId =
                session.getAttribute("appInstanceId");

        if (usuarioId == null ||
                instanceId == null ||
                !appSessionConfig.getInstanceId().equals(instanceId))
        {

            session.invalidate();

            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}