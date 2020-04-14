package com.imooc.imageCode;

import com.imooc.controller.ValidateImageCodeController;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码过滤器
 */
public class ValidataCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals(httpServletRequest.getRequestURI(), "/authentication/form") &&
            StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "POST")) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidataCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 验证session中存储的码
     * @param servletWebRequest
     */
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        // 获取session中的imageCode和request中的code进行比较
        ImageCode imageCodeSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateImageCodeController.SESSION_IMAGE_CODE_KEY);
        String imageCodeReq = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if (StringUtils.isEmpty(imageCodeReq)) {
            throw new ValidataCodeException("验证码不能为空");
        }
        if (imageCodeSession == null) {
            throw new ValidataCodeException("验证码不存在");
        }
        if (imageCodeSession.isExpire()) {
            throw new ValidataCodeException("验证码已过期");
        }
        if (!StringUtils.equals(imageCodeSession.getCode(), imageCodeReq)) {
            throw new ValidataCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateImageCodeController.SESSION_IMAGE_CODE_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
