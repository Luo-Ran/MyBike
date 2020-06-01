package com.xmut.dao;

import com.xmut.pojo.Site;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.security.SignatureException;
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

    /**
     * 根据siteName查询站点信息
     * @param siteName
     * @return
     */
    List<Site> querySiteByName(String siteName);

    /**
     * 根据经纬度查询站点信息
     * @param site
     * @return
     */
    Site querySiteByLocation(Site site);

    /**
     * 新增站点
     * @param site
     * @return
     */
    int saveSiteInfo(Site site);

    /**
     * 删除站点
     * @param site
     * @return
     */
    int deleteSiteInfo(Site site);
}
