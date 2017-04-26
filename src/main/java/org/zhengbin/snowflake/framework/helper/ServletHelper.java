package org.zhengbin.snowflake.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet 助手类
 * Created by zhengbinMac on 2017/4/8.
 */
public final class ServletHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServletHelper.class);

    private static final ThreadLocal<ServletHelper> SERVLET_HELPER_HOLDER = new ThreadLocal<ServletHelper>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * 初始化
     * @param request
     * @param response
     */
    public static void init(HttpServletRequest request, HttpServletResponse response) {
        SERVLET_HELPER_HOLDER.set(new ServletHelper(request, response));
    }

    /**
     * 销毁本地线程变量
     */
    public static void destroy() {
        SERVLET_HELPER_HOLDER.remove();
    }

    /**
     * 获取 响应
     * @return
     */
    public static HttpServletResponse getResponse() {
        return SERVLET_HELPER_HOLDER.get().response;
    }

    /**
     * 获取请求
     * @return
     */
    public static HttpServletRequest getRequest() {
        return SERVLET_HELPER_HOLDER.get().request;
    }

    /**
     * 获取 Session 对象
     * @return
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取上下文 ServletContext 对象
     * @return
     */
    public static ServletContext getServletContext() {
        return getRequest().getServletContext();
    }

    /*
        封装常用 Servlet API
     */

    /**
     * 将属性放入 Request 中
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name, Object value) {
        getRequest().setAttribute(name, value);
    }

    /**
     * 从 Request 中获取属性
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getRequestAttribute(String name) {
        return (T) getRequest().getAttribute(name);
    }

    /**
     * 从 Request 中移除属性
     * @param name
     */
    public static void remoteRequestAttribute(String name) {
        getRequest().removeAttribute(name);
    }

    /**
     * 发送重定向响应
     * @param location
     */
    public static void sendRedirect(String location) {
        try {
            getResponse().sendRedirect(location);
        } catch (IOException e) {
            LOGGER.error("redirect failure", e);
        }
    }

    /**
     * 将属性放入 Session 中
     * @param name
     * @param value
     */
    public static void setSessionAttribute(String name, Object value) {
        getSession().setAttribute(name, value);
    }

    /**
     * 从 Session 中获取属性
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getSessionAttribute(String name) {
        return (T) getSession().getAttribute(name);
    }

    /**
     * 从 Session 中移除属性
     * @param name
     */
    public static void removeSessionAttribute(String name) {
        getSession().removeAttribute(name);
    }

    /**
     * 使 Session 失效
     */
    public static void invalidateSession() {
        getRequest().getSession().invalidate();
    }
}
