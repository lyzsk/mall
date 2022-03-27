package cn.sichu.graduatemall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sichu.graduatemall.mbg.model.UmsPermission;

/**
 * 后台用户与角色管理自定义Dao
 * 
 * @author sichu
 * @date 2022/03/26
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
