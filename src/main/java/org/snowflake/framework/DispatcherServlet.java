package org.snowflake.framework;

import org.snowflake.framework.bean.Data;
import org.snowflake.framework.bean.Handler;
import org.snowflake.framework.bean.Param;
import org.snowflake.framework.bean.View;
import org.snowflake.framework.helper.BeanHelper;
import org.snowflake.framework.helper.ConfigHelper;
import org.snowflake.framework.helper.ControllerHelper;
import org.snowflake.framework.helper.ServletHelper;
import org.snowflake.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/3/28.
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        // 初始化相关 Helper 类
        HelperLoader.init();
        // 获取 ServletContext 对象（用于注册 Servlet ）
        ServletContext servletContext = servletConfig.getServletContext();
        // 注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        // 注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.init(request, response);
        try{
            // 获取请求方法，如"get"或"post"
            String requestMethod = request.getMethod().toLowerCase();
            // 获取请求路径，如"/customer"
            String requestPath = request.getPathInfo();
            // 根据请求的方法和路径，获取 Action 处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
                // 获取 Controller 类及其 Bean 实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);
                // 创建请求参数对象，获取所有的请求参数
                Map<String, Object> paramMap = new HashMap<String, Object>();
                Enumeration<String> paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    String paramValue = request.getParameter(paramName);
                    paramMap.put(paramName, paramValue);
                }
                String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
                if (StringUtil.isNotEmpty(body)) {
                    String[] params = StringUtil.splitString(body, "&");
                    if (ArrayUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            String[] array = StringUtil.splitString(param, "=");
                            if (ArrayUtil.isNotEmpty(array) && array.length==2) {
                                String paramName = array[0];
                                String paramValue = array[1];
                                paramMap.put(paramName, paramValue);
                            }
                        }
                    }
                }
                // 包装请求参数
                Param param = new Param(paramMap);
                // 调用 Action 方法
                Object result;
                // 获取请求的方法
                Method actionMethod = handler.getActionMethod();
                // 获取请求的方法的参数列表
                Class<?>[] parameterTypes = actionMethod.getParameterTypes();
                // 优化 Action 参数，参数可以为空
                if (parameterTypes.length == 0) {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                }
                // 处理 Action 方法返回值
                if (result instanceof View) {
                    // 返回 JSP 页面
                    View view = (View) result;
                    String path = view.getPath();
                    if (StringUtil.isNotEmpty(path)) {
                        if (path.startsWith("/")) {
                            response.sendRedirect(request.getContextPath() + path);
                        } else {
                            Map<String, Object> model = view.getModel();
                            for (Map.Entry<String, Object>entry : model.entrySet()) {
                                request.setAttribute(entry.getKey(), entry.getValue());
                            }
                            request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
                        }
                    } else if (request instanceof Data) {
                        // 返回 JSON 函数
                        Data data = (Data) result;
                        Object model = data.getModel();
                        if (model != null) {
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            PrintWriter writer = response.getWriter();
                            String json = JsonUtil.toJson(model);
                            writer.write(json);
                            writer.flush();
                            writer.close();
                        }
                    }
                }
            }
        }finally {
            ServletHelper.destroy();
        }
    }
}
