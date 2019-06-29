package com.qiaof.reptile.htmlunit;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.qiaof.reptile.utils.WebClientUtil;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: huangqf
 * @date: 2019/6/29 09:39
 */
public class HtmlUnitReptile {

    /**
     * 登录
     *
     * @throws Exception
     */
    public void login() throws Exception {

        WebClient webClient = WebClientUtil.getWebClient();
        // 获取页面信息
        HtmlPage page = webClient.getPage("https://gitee.com/login");
        // 获取用户名输入框
        HtmlInput userName = (HtmlInput) page.getElementById("user_login");
        // 1.直接将用户名输入到input
        userName.setValueAttribute("huangqf");
        // 获取密码框
        HtmlInput password = (HtmlInput) page.getElementById("user_password");
        password.setValueAttribute("1234");
        // 获取登录按钮dom
        HtmlInput commit = (HtmlInput) page.getElementsByName("commit").get(0);
        // 模拟提交操作
        HtmlPage homePage = commit.click();
        // 打印页面源代码
        System.out.println(homePage.asXml());
    }

    /**
     * 提取页面数据
     *
     * @throws IOException
     */
    public void getTitleName() throws IOException {

        WebClient webClient = WebClientUtil.getWebClient();
        // 根据java关键字检索
        HtmlPage javaPage = webClient.getPage("https://gitee.com/search?utf8=%E2%9C%93&q=java&type=");
        webClient.waitForBackgroundJavaScript(2000);
        System.out.println(javaPage.asXml());
        // 定位列表内容
        HtmlDivision division = (HtmlDivision) javaPage.getByXPath("/html/body/div[3]/div/div/div[1]/div[3]/div").get(0);
        System.out.println(division.asXml());
        // 获取到item列
        Iterable<DomElement> childElements = division.getChildElements();
        childElements.forEach(domElement -> {
            String b = domElement.getElementsByTagName("b").get(0).getTextContent();
            System.out.println(b);
        });
    }

    /**
     * 获取接口数据ajax
     */
    public void getIntefaceDate() throws IOException {
        WebClient webClient = WebClientUtil.getWebClient();
        WebRequest webRequest = new WebRequest(new URL("https://gitee.com/wizzer/NutzWk/project_radars"), HttpMethod.POST);
        webRequest.setCharset(Charset.forName("utf-8"));
        // 参数为json格式
        webRequest.setRequestBody("");
        // key-value参数形式
        List<NameValuePair>  pairList= new ArrayList<>();
        pairList.add(new NameValuePair("name","value"));
        webRequest.setRequestParameters(pairList);
        String contentJson = webClient.loadWebResponse(webRequest).getContentAsString();

    }

}

