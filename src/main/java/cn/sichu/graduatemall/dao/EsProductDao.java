package cn.sichu.graduatemall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.sichu.graduatemall.nosql.elasticsearch.document.EsProduct;

/**
 * 搜索系统中的商品管理自定义Dao
 * 
 * @author sichu
 * @date 2022/03/27
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
