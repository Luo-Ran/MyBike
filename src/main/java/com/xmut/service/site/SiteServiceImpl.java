package com.xmut.service.site;

import com.xmut.dao.SiteDao;
import com.xmut.pojo.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteDao siteDao;

    @Override
    public List<Site> queryAllSiteInfo() {
        return siteDao.queryAllSiteInfo();
    }

    @Override
    public Site querySiteBySiteId(Long siteId) {
        return siteDao.querySiteBySiteId(siteId);
    }

    @Override
    public int saveSiteInfo(Site site) {
        return siteDao.saveSiteInfo(site);
    }

    @Override
    public int deleteSiteInfo(Site site) {
        return siteDao.deleteSiteInfo(site);
    }
}
