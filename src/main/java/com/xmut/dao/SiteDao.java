package com.xmut.dao;

import com.xmut.pojo.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SiteDao {
    /**
     * 站点信息查询
     * @return
     */
    List<Site> queryAllSiteInfo();

    /**
     * 根据siteId查询站点信息
     * @param siteId
     * @return
     */
    Site querySiteBySiteId(Long siteId);
}
