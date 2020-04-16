package com.imooc.properties;

public class BrowserProperties {
    private String loginPage = "/imooc-signIn.html";
    /**
     * 记住我保存一小时
     */
    private int rememerberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public int getRememerberMeSeconds() {
        return rememerberMeSeconds;
    }

    public void setRememerberMeSeconds(int rememerberMeSeconds) {
        this.rememerberMeSeconds = rememerberMeSeconds;
    }
}
