package com.zhiyu.shiro;

import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description:
 * @Author: sunzhiyu
 * @CreateDate: 2021/5/13 15:43
 */
public class RoleTest extends BaseTest {
    @Test
    public void testHasRole() {
        login("classpath:authorize/shiro-role.ini", "sun", "123");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        login("classpath:authorize/shiro-role.ini", "sun", "123");
        //断言拥有角色：role1
        subject().checkRole("role1");
        //断言拥有角色：role1 and role3 失败抛出异常
        subject().checkRoles("role1", "role3");
    }

}
