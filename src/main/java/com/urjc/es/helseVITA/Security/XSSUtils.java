/*package com.urjc.es.helseVITA.Security;


import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;

public class XSSUtils {

    public static String stripXSS(String value) {
        if (value == null) {
            return null;
        }

        return Jsoup.clean(value, Whitelist.none());
    }

}
*/