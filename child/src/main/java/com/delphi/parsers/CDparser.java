package com.delphi.parsers;


import com.delphi.readers.Reader;
import com.delphi.data.CD;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CDparser implements Parser<CD> {

    private final Reader reader;

    public CDparser(Reader reader) {
        this.reader = reader;
    }

    @Override
    public List<CD> parse() {
        String fileString = new String(reader.read());
        List<CD> cds = new ArrayList<>();
        Pattern pattern = Pattern.compile("<CD>[\\s\\S]*?<\\/CD>");
        Matcher matcher = pattern.matcher(fileString);
        createCD(cds, matcher);
        return cds;
    }

    private void createCD(List<CD> cds, Matcher matcher) {
        while (matcher.find()) {
            CD cd = new CD();
            cd.setTitle(getMatcher(matcher, 0).group(1));
            cd.setArtist(getMatcher(matcher, 1).group(1));
            cd.setCountry(getMatcher(matcher, 2).group(1));
            cd.setCompany(getMatcher(matcher, 3).group(1));
            cd.setPrice(Double.parseDouble(getMatcher(matcher, 4).group(1)));
            cd.setYear(Integer.parseInt(getMatcher(matcher, 5).group(1)));
            cds.add(cd);
        }
    }

    private Matcher getMatcher(Matcher matcher, int count) {
        Matcher tagMatcher;
        tagMatcher = getPatterns()[count].matcher(matcher.group());
        tagMatcher.find();
        return tagMatcher;
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
