package cn.sichu.graduatemall.service;

import java.util.List;

import cn.sichu.graduatemall.mbg.model.PmsBrand;

/**
 * PmsBrandService
 * 
 * @author sichu
 * @date 2022/03/26
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
