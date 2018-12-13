package api.controller;

import api.Util.UrlShortner;
import api.dao.impl.UrlDaoImpl;
import api.dao.util.UrlDao;

import java.text.SimpleDateFormat;
import java.util.*;

import api.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UrlController {

    @Autowired
    UrlDaoImpl urlDao;

//    @RequestMapping(value = "/shorten", method = RequestMethod.POST)
    @PostMapping
    public Url shortenedUrl(@RequestBody Url url) {
        long id = new Date().getTime();
        String encodedId = UrlShortner.encode(id);
        urlDao.create(url.getLongUrl(), encodedId);

        return new Url(encodedId, url.getShortUrl());
    }

    @RequestMapping(value = "/expand/{urlId}", method = RequestMethod.GET)
    public String expandUrl(@PathVariable String urlId) {
        int count = urlDao.count(urlId);
        urlDao.update(urlId, count + 1);
        List<Url> urlList = urlDao.getUrl(urlId);
        if(urlList != null && urlList.size() > 0) {
            return urlList.get(0).getLongUrl();
        } else {
            return "Match Not Found for \'" + urlId + "\'";
        }
    }

    @RequestMapping(value = "/mostpopular", method = RequestMethod.GET)
    public List<Url> mostViewedUrl() {
        return urlDao.mostViewedUrls();
    }

    @RequestMapping(value = "/recentlyviewed", method =  RequestMethod.GET)
    public List<Map<String, Object>> latest() {
        return urlDao.recentlyViewedUrls();
    }

    @RequestMapping(value = "/shorturllist", method = RequestMethod.GET)
    public List<String> all() {
        return urlDao.listOfShortenedUrls();
    }

    @RequestMapping(value = "/shorturlcount", method = RequestMethod.GET)
    public int totalUrlshortened() {
        return urlDao.totalUrlShortened();
    }

    @RequestMapping(value = "/recentlyshortenurl", method = RequestMethod.GET)
    public List<Map<String, Object>>recentlyShortenedUrls() {
        return urlDao.recentlyShortenedUrl();
    }

    @RequestMapping(value = "/last5dayscount", method = RequestMethod.GET)
    public List<Map<String, Object>> chart() {
        List<Map<String, Object>> lm = urlDao.last5daysCount();
        Calendar c = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String,Object> daycountMap = null;
        String date = null;
        for(int i = 0; i < 5; i++) {
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -i);
            date = simpleDateFormat.format(c);
            daycountMap = new HashMap<String, Object>();
            daycountMap.put("DATE", c.getTime().getTime());
            daycountMap.put("COUNT", 0);
            result.add(daycountMap);
            for(Map<String, Object> map : lm) {
                if(((java.sql.Date) map.get("DATE")).toString().equals(date)) {
                    daycountMap.put("COUNT", map.get("COUNT"));
                    break;
                }
            }
        }
        return result;
    }

    @RequestMapping(value = "/listurl", method = RequestMethod.GET)
    public List<Map<String, Object>> urlList() {
        return urlDao.urlList();
    }
}
