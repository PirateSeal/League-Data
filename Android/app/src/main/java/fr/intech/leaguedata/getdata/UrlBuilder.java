package fr.intech.leaguedata.getdata;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlBuilder {


    public String[] buildUrlName(String userName) {
        try {
            String user = URLEncoder.encode(String.valueOf(userName), "UTF-8").replace("+", "%20");
            String url = "http://tcousin.com:2525/user/";
            String[] urls = new String[]{
                    url + user, url + user + "/queue/RANKED_SOLO_5x5"
            };
            return urls;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
