package com.singhankit.jdk.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ankit Singh
 */
class OpenJdkClient implements JdkClient {
    private static final String BASE_PAGE = "https://openjdk.org/";
    private static final String SPECIFIC_JDK_PAGE = BASE_PAGE + "projects/jdk/%s/";
    private static final String JDK_PAGE = BASE_PAGE + "projects/jdk/";

    @Override
    public List<String> getSchedules(String version) throws IOException {
        Elements blockquote = send(SPECIFIC_JDK_PAGE.formatted(version)).getElementById("main").selectXpath("blockquote");
        var schedules = new ArrayList<String>();
        for(Element element : blockquote) {
            Elements milestones = element.getElementsByClass("milestones");
            for(Element milestone : milestones) {
                Elements innerMl = milestone.getElementsByClass("milestone");
                for(Element innerEle : innerMl) {
                    schedules.add(innerEle.getElementsByClass("milestone").text());
                }
            }
        }
        return schedules;
    }

    @Override
    public List<String> getFeatures(String version) throws IOException {
        Elements elements = send(SPECIFIC_JDK_PAGE.formatted(version)).getElementById("main").selectXpath("blockquote");
        var features = new ArrayList<String>();
        for(Element element : elements) {
            Elements jeps = element.getElementsByClass("jeps");
            for(var jep : jeps) {
                Elements innerEle = jep.select("tr");
                for(int i = 1; i < innerEle.size(); i++) {
                    features.add(innerEle.get(i).text());
                }
            }
        }
        return features;
    }

    @Override
    public List<String> listAllJdk() throws IOException {
        return send(JDK_PAGE).getElementById("main").selectXpath("ul").getFirst().wholeText().lines().filter(l -> !l.isBlank()).toList();
    }

    @Override
    public String latestJdk() throws IOException {
        return send(JDK_PAGE).getElementById("main").selectXpath("ul").select("li").getFirst().wholeText();
    }

    private Document send(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
