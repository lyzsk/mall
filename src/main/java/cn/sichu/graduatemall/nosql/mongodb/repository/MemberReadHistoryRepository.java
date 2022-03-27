package cn.sichu.graduatemall.nosql.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.sichu.graduatemall.nosql.mongodb.document.MemberReadHistory;

/**
 * 会员商品浏览历史Repository
 * 
 * @author sichu
 * @date 2022/03/27
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {
    /**
     * 根据会员id按时间倒序获取浏览记录
     * 
     * @param memberId
     *            会员id
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
