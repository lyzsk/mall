package cn.sichu.graduatemall.service;

import javax.servlet.http.HttpServletRequest;

import cn.sichu.graduatemall.dto.OssCallbackResult;
import cn.sichu.graduatemall.dto.OssPolicyResult;

/**
 * oss上传管理Service
 * 
 * @author sichu
 * @date 2022/03/29
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
