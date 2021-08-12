package com.delphi.parsers;


import com.delphi.Logger.LoggerInitialization;
import com.delphi.data.CD;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CDParser implements Parser<CD> {

    private static final Logger LOGGER = new LoggerInitialization().initialization();

    @Override
    public List<CD> parse(byte[] arr) {
        String fileString = new String(arr);
        List<CD> cds = new ArrayList<>();
        Pattern pattern = Pattern.compile("<CD>[\\s\\S]*?<\\/CD>");
        Matcher matcher = pattern.matcher(fileString);
        fillCDList(cds, matcher);
        LOGGER.config("Creating List<CD>");
        return cds;
    }

    private void fillCDList(List<CD> cds, Matcher matcher) {
        while (matcher.find()) {
            CD cd = new CD();
            cd.setTitle(getLineBetweenTags(matcher, "TITLE"));
            cd.setArtist(getLineBetweenTags(matcher, "ARTIST"));
            cd.setCountry(getLineBetweenTags(matcher, "COUNTRY"));
            cd.setCompany(getLineBetweenTags(matcher, "COMPANY"));
            cd.setPrice(Double.parseDouble(getLineBetweenTags(matcher, "PRICE")));
            cd.setYear(Integer.parseInt(getLineBetweenTags(matcher, "YEAR")));
            cds.add(cd);
        }
    }

    private String getLineBetweenTags(Matcher matcher, String word) {
        Matcher tagMatcher = Pattern.compile("<" + word + ">" + "(.+?)<\\/" + word + ">").matcher(matcher.group());
        tagMatcher.find();
        return tagMatcher.group(1);
    }

//    private Pattern[] getPatterns(String word) {
//        Pattern[] content = new Pattern[6];
//        for (int i = 0; i < content.length; i++) {
//            content[i] = Pattern.compile("<" + word + ">" + "(.+?)<\\/" + word + ">");
//        }
//        content[0] = Pattern.compile("<TITLE>(.+?)<\\/TITLE>");
//        content[1] = Pattern.compile("<ARTIST>(.+?)<\\/ARTIST>");
//        content[2] = Pattern.compile("<COUNTRY>(.+?)<\\/COUNTRY>");
//        content[3] = Pattern.compile("<COMPANY>(.+?)<\\/COMPANY>");
//        content[4] = Pattern.compile("<PRICE>(.+?)<\\/PRICE>");
//        content[5] = Pattern.compile("<YEAR>(.+?)<\\/YEAR>");
//        return content;
//    }

}
