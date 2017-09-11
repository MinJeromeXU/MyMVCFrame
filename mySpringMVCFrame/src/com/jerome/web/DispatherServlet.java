package com.jerome.web;

import com.jerome.bean.BeanContainer;
import com.jerome.bean.BeanFactory;
import com.jerome.bean.Handler;
import com.jerome.bean.ModelAndView;
import com.jerome.helper.ControllerHelper;
import com.jerome.utils.Propsutil;
import com.jerome.utils.StringUtil;

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
import java.util.Map;


@WebServlet(urlPatterns = "/",loadOnStartup =0)
public class DispatherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

        System.out.println("init");
        Loader.init();
        //加载jsp路径
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(Propsutil.getString("app.jsp_path")+"*");
        //加载资源默认路径
         ServletRegistration assetServlet = servletContext.getServletRegistration("default");
        assetServlet.addMapping(Propsutil.getString("app.asset_path")+"*");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
        String requestMethod = req.getMethod().toLowerCase();//get
        String url = req.getRequestURI();//请求的路径===/myframe_/home
        String contextPath = req.getContextPath();//容器的路径 /myframe
        String requestPath = null;//
        if(contextPath!=null){
            requestPath = url.substring(contextPath.length());//   /home
        }

        Handler handler = ControllerHelper.getHander(requestMethod,requestPath);

        if(handler!=null){

            Class<?>  controllerClass = handler.getControllerClass();
            Object controllerBean = BeanContainer.getBean(controllerClass.getName());
            Object result ;

            Method method = handler.getMethod();
            int paramNum = method.getParameterCount();
            if(paramNum==0){
                result = BeanFactory.invokeMethod(controllerBean,method);
            }else {
                result = BeanFactory.invokeMethod(controllerBean,method,req);

            }
            if(result  instanceof ModelAndView){
                jspview((ModelAndView) result,req,resp);
            }else {
                jdataView(result,resp);
            }
        }


    }

    public  static  void jspview(ModelAndView view,HttpServletRequest req,HttpServletResponse response) throws IOException, ServletException {

        String path = view.getPath();
        if(StringUtil.isNotEmpty(path)){
            if(path.startsWith("/")){
                response.sendRedirect(req.getContextPath()+path);

            }else {
                Map<String,Object> data = view.getData();
                for(Map.Entry<String,Object> entry: data.entrySet()){
                    req.setAttribute(entry.getKey(),entry.getValue());
                }
                req.getRequestDispatcher(Propsutil.getString("app.jsp_path")+path).forward(req,response);
            }
        }
    }

    public  static  void jdataView(Object str,HttpServletResponse resp) throws IOException {

        if(str!=null){
            resp.setContentType("text/plan");
            resp.setCharacterEncoding("utf-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(str.toString());
            printWriter.flush();
            printWriter.close();
        }

    }
}
