package cn.sichu.graduatemall.service;

import java.util.List;

import cn.sichu.graduatemall.nosql.mongodb.document.MemberReadHistory;

/**
 * 会员浏览记录管理Service
 * 
 * @author sichu
 * @date 2022/03/27
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
