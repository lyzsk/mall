package cn.sichu.graduatemall.service;

import cn.sichu.graduatemall.common.api.CommonResult;

/**
 * 会员管理Service
 * 
 * @author sichu
 * @date 2022/03/26
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
