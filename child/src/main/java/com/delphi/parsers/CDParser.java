package com.delphi.parsers;


import com.delphi.data.CD;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CDParser implements Parser<CD> {

    private static final Logger LOGGER;

    static {
        InputStream stream = CDParser.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER = Logger.getLogger(CDParser.class.getName());
    }

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
            cd.setTitle(getLineBetweenTags(matcher, 0));
            cd.setArtist(getLineBetweenTags(matcher, 1));
            cd.setCountry(getLineBetweenTags(matcher, 2));
            cd.setCompany(getLineBetweenTags(matcher, 3));
            cd.setPrice(Double.parseDouble(getLineBetweenTags(matcher, 4)));
            cd.setYear(Integer.parseInt(getLineBetweenTags(matcher, 5)));
            cds.add(cd);
        }
    }

    private String getLineBetweenTags(Matcher matcher, int count) {
        Matcher tagMatcher = getPatterns()[count].matcher(matcher.group());
        tagMatcher.find();
        return tagMatcher.group(1);
    }

    private Pattern[] getPatterns() {
        Pattern[] content = new Pattern[6];
        content[0] = Pattern.compile("<TITLE>(.+?)<\\/TITLE>");
        content[1] = Pattern.compile("<ARTIST>(.+?)<\\/ARTIST>");
        content[2] = Pattern.compile("<COUNTRY>(.+?)<\\/COUNTRY>");
        content[3] = Pattern.compile("<COMPANY>(.+?)<\\/COMPANY>");
        content[4] = Pattern.compile("<PRICE>(.+?)<\\/PRICE>");
        content[5] = Pattern.compile("<YEAR>(.+?)<\\/YEAR>");
        return content;
    }

}
