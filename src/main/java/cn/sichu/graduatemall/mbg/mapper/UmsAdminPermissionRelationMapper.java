package cn.sichu.graduatemall.mbg.mapper;

import cn.sichu.graduatemall.mbg.model.UmsAdminPermissionRelation;
import cn.sichu.graduatemall.mbg.model.UmsAdminPermissionRelationExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminPermissionRelationMapper {
    long countByExample(UmsAdminPermissionRelationExample example);

    int deleteByExample(UmsAdminPermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermissionRelation row);

    int insertSelective(UmsAdminPermissionRelation row);

    List<UmsAdminPermissionRelation> selectByExample(UmsAdminPermissionRelationExample example);

    UmsAdminPermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UmsAdminPermissionRelation row, @Param("example") UmsAdminPermissionRelationExample example);

    int updateByExample(@Param("row") UmsAdminPermissionRelation row, @Param("example") UmsAdminPermissionRelationExample example);

    int updateByPrimaryKeySelective(UmsAdminPermissionRelation row);

    int updateByPrimaryKey(UmsAdminPermissionRelation row);
}