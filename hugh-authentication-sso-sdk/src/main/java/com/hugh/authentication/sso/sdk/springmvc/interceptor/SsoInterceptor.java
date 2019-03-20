package com.hugh.authentication.sso.sdk.springmvc.interceptor;

import com.hugh.authentication.sso.sdk.domain.UserInfo;
import com.hugh.authentication.sso.sdk.utils.RSA;
import com.hugh.authentication.sso.sdk.utils.SSOHelper;
import com.hugh.authentication.sso.sdk.web.LoginContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.util.StringUtils.*;

public abstract class SsoInterceptor {

    private final static Log log = LogFactory.getLog(SsoInterceptor.class);

    /**
     * cookie 名
     */
    public static final String SSO_COOKIE_NAME = "sso.hugh.com";

    /**
     * seeion 名
     */
    private static final String SSO_SESSION_NAME = "session.sso.hugh.com";

    /**
     * SSO登录地址
     */
    private String loginUrl;

    /**
     * cookie的私钥解密key
     */
    protected String privateModule;

    /**
     * cookie的解密key
     */
    protected String privateExponent;

    /**
     * webapp的域名，用于表示是什么应用
     */
    private String appDomainName;

    /**
     * 需要排除的uri，如："/static,/hessian,/rest"
     */
    private String excludePath;

    /**
     * 保存需要排除的路径
     */
    private List<String> excludePathCache;

    /**
     * 获取cookie:sso.hugh.com,调用单点登录接口方法判断登录是否有效
     * @param request
     * @return
     */
    protected UserInfo getLoginUser(HttpServletRequest request) {
        UserInfo userInfo = null;

        String ticket_key = SSOHelper.getCookieValue(request, SSO_COOKIE_NAME);
        if (isEmpty(ticket_key)) {
            String url = appDomainName + request.getRequestURI();
            String remoteIP = SSOHelper.getRemoteIP(request);
            String userAgent = SSOHelper.getUserAgent(request);
            if(log.isDebugEnabled()) {
                log.debug("当前请求页面地址：" + url);
                log.debug("远程访问IP地址："+ remoteIP);
                log.debug("浏览器Agent："+ userAgent);
            }
            userInfo = verifyTicket(ticket_key, url, remoteIP, userAgent);
            if (userInfo == null) {
                log.error("单点登录ticket_key:" + ticket_key + "验证失败, url:[" +url +"]");
            }
        } else {
            if(log.isDebugEnabled()) {
                log.debug("cookie:sso.hugh.com is null，Redirect to sso server page login");
            }
            LoginContext.remove();
        }
        return userInfo;
    }

    /**
     * 解密cookie
     * @param ticket
     * @param url
     * @param clientIp
     * @param userAgent
     * @return
     */
    public UserInfo verifyTicket(String ticket, String url, String clientIp, String userAgent) {
        try {
            //ip_agent_
            String res = RSA.decryptString(privateModule, privateExponent, ticket);
            //TODO
        } catch (Exception e) {
            log.error("不是正确的ticket", e);
        }

        return null;
    }

    /**
     *
     * @return
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * 判断是否需要排除
     * @param uri
     * @return
     */
    public boolean isExclude(String uri) {
        //如果没有配置，则认为不需要排除拦截
        if (CollectionUtils.isEmpty(excludePathCache)) {
            return false;
        }
        String s = excludePathCache.stream().filter(v -> uri.startsWith(v)).findFirst().get();
        return !isEmpty(s);
    }

    /**
     * 判断是否需要排除
     * @param request
     * @return
     */
    public boolean isExclude(HttpServletRequest request) {
        return isExclude(request.getRequestURI());
    }
}
