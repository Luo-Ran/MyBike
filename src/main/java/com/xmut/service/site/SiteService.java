package com.xmut.service.site;

import com.xmut.pojo.Site;

import java.util.List;

public interface SiteService {
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
}
