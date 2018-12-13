package com.interswitch.urlshortener.api.dao.impl;

import com.interswitch.urlshortener.api.Util.UrlMapper;
import com.interswitch.urlshortener.api.dao.util.Constant;
import com.interswitch.urlshortener.api.dao.util.UrlDao;
import com.interswitch.urlshortener.api.model.Url;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public class UrlDaoImpl implements UrlDao {

    Logger LOGGER = Logger.getLogger(UrlDaoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(String longUrl, String shortUrl) {
        long time = System.currentTimeMillis();
        Timestamp createdDate = new java.sql.Timestamp(time);
        Timestamp modifiedDate =  new java.sql.Timestamp(time);
        int count = 1;
        jdbcTemplate.update(Constant.INSERT_QUERY, longUrl, shortUrl, createdDate, modifiedDate, count);
    }

    @Override
    public int count(String shortUrl) {
        int count = jdbcTemplate.queryForObject(Constant.ACCESS_COUNT_QUERY,
                new Object[] {shortUrl}, Integer.class);
        return count;
    }

    @Override
    public int totalUrlShortened() {
        int count = jdbcTemplate.queryForObject(Constant.TOTAL_URLS_COUNT_QUERY, new Object[] {}, Integer.class);
        return count;
    }

    @Override
    public List<Url> getUrl(String shortUrl) {
        LOGGER.info("Qeury:" + Constant.SELECT_FOR_GIVEN_URLS_QUERY + ":" + shortUrl );
        List<Url> urlList = jdbcTemplate.query(Constant.SELECT_FOR_GIVEN_URLS_QUERY + new Object[] {shortUrl}, new UrlMapper());
        return urlList;
    }

    @Override
    public List<Map<String, Object>> urlList() {
        List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constant.LIST_OF_SHORTEN_URLS);
        return urlList;
    }

    @Override
    public List<String> listOfShortenedUrls() {
        List<String> urlList = jdbcTemplate.query(Constant.LIST_OF_SHORTEN_URLS, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {

                return rs.getString(Constant.SHORTURL);
            }
        });
        return urlList;
    }

    @Override
    public void update(String shortUrl, int count) {
        long time = System.currentTimeMillis();
        Timestamp modifiedDate =  new java.sql.Timestamp(time);
        jdbcTemplate.update(Constant.UPDATE_COUNT_QUERY,count, shortUrl);
        jdbcTemplate.update(Constant.UPDATE_LAST_ACCESSED_QUERY, modifiedDate, shortUrl);
    }

    @Override
    public List<Url> mostViewedUrls() {
        List<Url> urlList = jdbcTemplate.query(Constant.MOST_VIEWED_URLS_QUERY, new RowMapper<Url>() {
            @Override
            public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
                Url url = new Url();
                url.setShortUrl(rs.getString(Constant.SHORTURL));
                url.setCount(rs.getInt(Constant.COUNT));
                return url;
            }
        });
        return urlList;
    }

    public List<Map<String, Object>> recentlyShortenedUrl(){
        List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constant.RECENTLY_SHORTEN_URLS_QUERY);
        return urlList;
    }

    @Override
    public List<Map<String, Object>> recentlyViewedUrls() {
        List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constant.RECENTLY_VIEWED_URLS_QUERY);
        return urlList;
    }

    @Override
    public List<Map<String, Object>> last5daysCount() {
        List<Map<String, Object>> urlList = jdbcTemplate.queryForList(Constant.COUNT_FOR_LAST5DAYS_QUERY);
        return urlList;
    }
}
