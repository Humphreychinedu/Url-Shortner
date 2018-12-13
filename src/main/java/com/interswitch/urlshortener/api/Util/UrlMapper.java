package api.Util;

import api.model.Url;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UrlMapper implements RowMapper<Url> {

    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setLongUrl(rs.getString("longUrl"));
        url.setShortUrl(rs.getString("shortUrl"));
        return url;
    }
}
