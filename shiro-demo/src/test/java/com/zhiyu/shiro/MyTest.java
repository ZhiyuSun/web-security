package com.zhiyu.shiro;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @Description:
 * @Author: sunzhiyu
 * @CreateDate: 2021/5/13 16:26
 */
public class MyTest {

    @Test
    public void testAuthentication() {
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

        //为 Realm 添加一个账户
        simpleAccountRealm.addAccount("sunzhiyu", "123456");

        // 构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 为 SecurityManager 设置 Realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 将 SecurityManager 放入 SecurityUtils 这个工具类中
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 获取一个 Subject
        Subject subject = SecurityUtils.getSubject();

        // 创建一个账号密码, 在 web 应用中一般为表单上填写并传入后台.
        UsernamePasswordToken token = new UsernamePasswordToken("sunzhiyu", "123456");

        // 进行登陆操作
        subject.login(token);

        // 验证是否为登陆状态
        System.out.println("是否登陆: " + subject.isAuthenticated());
    }


    @Test
    public void testAuthorize() {
        // 创建一个 Realm
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

        //为 Realm 添加一个账户, 并赋予 admin 角色
        simpleAccountRealm.addAccount("sunzhiyu", "123456", "admin");

        // 构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 为 SecurityManager 设置 Realm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        // 将 SecurityManager 放入 SecurityUtils 这个工具类中
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // 获取一个 Subject
        Subject subject = SecurityUtils.getSubject();

        // 创建一个账号密码
        UsernamePasswordToken token = new UsernamePasswordToken("sunzhiyu", "123456");

        // 进行登陆操作
        subject.login(token);

        // 验证是否为登陆状态
        System.out.println("是否登陆: " + subject.isAuthenticated());

        // 验证是否具备某个角色
        System.out.println("是否具备admin角色: " + subject.hasRole("admin"));

    }
}
