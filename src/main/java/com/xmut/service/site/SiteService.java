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
}
