package com.qiaof.reptile;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.qiaof.reptile.utils.WebClientUtil;

/**
 * @auther: huangqf
 * @date: 2019/6/29 09:39
 */
public class HtmlUnitReptile {
    private static Boolean login() throws Exception {

        final WebClient webClient = WebClientUtil.getWebClient();
       // 获取页面信息
        HtmlPage page = webClient.getPage("https://gitee.com/login");

        // 获取用户名输入框
        HtmlInput userName = (HtmlInput) page.getElementById("user_login");
        // 1.直接将用户名输入到input
        userName.setValueAttribute("huangqf");
        // 获取密码框
        HtmlInput password = (HtmlInput)  page.getElementById("user_password");
        password.setValueAttribute("1234");
        // 如果有验证码 需要输入验证码 第三方验证码市面上很多
        // 获取登录按钮dom
        HtmlButton commit = (HtmlButton) page.getByXPath("//*[@id=\"new_user\"]/div[2]/div/div/div[4]/input");

        // 模拟提交操作
        HtmlPage homePage = commit.click();
        // 等待js加载完成
        webClient.waitForBackgroundJavaScript(2000);

        // 根据java关键字检索
        HtmlPage javaPage = webClient.getPage("https://gitee.com/search?utf8=%E2%9C%93&q=java&type=");
        webClient.waitForBackgroundJavaScript(2000);
        // 定位列表内容
        HtmlDivision division = (HtmlDivision) javaPage.getByXPath("/html/body/div[3]/div/div/div[1]/div[3]/div").get(0);
        System.out.println(division.asXml());
        // 获取到item列
        Iterable<DomElement> childElements = division.getChildElements();
        childElements.forEach(domElement -> {
            String b = domElement.getElementsByTagName("b").get(0).getTextContent();
            System.out.println(b);
        });
        return true;
    }

    public static void main(String[] args) throws Exception {
        login();
    }


}

