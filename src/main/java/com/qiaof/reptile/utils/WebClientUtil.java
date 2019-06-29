package com.qiaof.reptile.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author huangqf
 * @date: 2019/2/22 17:00
 * @description:获取htmlunit的初始化信息
 */
public class WebClientUtil {

    public static WebClient getWebClient() {
        // 选择浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // //设置cookie是否可用
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        // 3 启动客户端重定向
        webClient.getOptions().setRedirectEnabled(true);
        // 4 js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 5 设置超时
        webClient.getOptions().setTimeout(10000);
        // 允许绕过SSL认证
        webClient.getOptions().setUseInsecureSSL(true);
        //允许启动注册组件
        webClient.getOptions().setActiveXNative(true);
        // 很重要，设置支持AJAX
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        return webClient;
    }

}
