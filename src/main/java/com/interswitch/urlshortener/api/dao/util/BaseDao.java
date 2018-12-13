package com.interswitch.urlshortener.api.dao.util;

import com.interswitch.urlshortener.api.model.Url;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    public void create(String longUrl, String shortUrl);

    public int count(String shortUrl);

    public int totalUrlShortened();

    public List<Url> getUrl(String shortUrl);

    public List<Map<String, Object>> urlList();

    public List<String> listOfShortenedUrls();

    public void update(String shortUrl, int count);

    public List<Url> mostViewedUrls();

    public List<Map<String, Object>> recentlyShortenedUrl();

    public List<Map<String,Object>> recentlyViewedUrls();

    public List<Map<String, Object>> last5daysCount();
}
