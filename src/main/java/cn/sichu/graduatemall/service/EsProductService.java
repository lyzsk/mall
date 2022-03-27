package cn.sichu.graduatemall.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.sichu.graduatemall.nosql.elasticsearch.document.EsProduct;

/**
 * 商品搜索管理Service
 * 
 * @author sichu
 * @date 2022/03/27
 */
public interface EsProductService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

}
