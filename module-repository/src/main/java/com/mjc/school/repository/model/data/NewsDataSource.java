package com.mjc.school.repository.model.data;

import com.mjc.school.repository.utils.LinesReader;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsDataSource {
    private static final String NEWS_TITLE_FILE_NAME = "news.txt";
    private static final String NEWS_CONTENT_FILE_NAME = "content.txt";
    private static final String AUTHORS_FILE_NAME = "authors.txt";
    private static final int AMOUNT_OF_LINES_TO_READ = 20;
    private static long lastNewsId = 1;
    private final List<NewsModel> news = new ArrayList<>();
    private final List<AuthorModel> authors = new ArrayList<>();
    private static NewsDataSource INSTANCE;

    private NewsDataSource() {
        init();
    }

    public static NewsDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsDataSource();
        }
        return INSTANCE;
    }

    public List<NewsModel> getNews() {
        return news;
    }

    public long getLastNewsId() {
        return lastNewsId;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    private void init() {
        readAuthors();
        List<AuthorModel> shuffledAuthors = new ArrayList<>(this.authors);
        Collections.shuffle(shuffledAuthors);
        createNews(shuffledAuthors);
    }

    private void readAuthors() {
        List<String> authorsLines = LinesReader.readLines(AUTHORS_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            authors.add(new AuthorModel((long)i + 1, authorsLines.get(i)));
        }
    }

    private void createNews(List<AuthorModel> authors) {
        List<String> titles = LinesReader.readLines(NEWS_TITLE_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        List<String> contents = LinesReader.readLines(NEWS_CONTENT_FILE_NAME, AMOUNT_OF_LINES_TO_READ);
        LocalDateTime randomDate;
        for (int i = 0; i < AMOUNT_OF_LINES_TO_READ; i++) {
            randomDate = LinesReader.getRandomDate();
            news.add(new NewsModel(lastNewsId, titles.get(i), contents.get(i),
                    randomDate, randomDate, authors.get(i).getId()));
            lastNewsId++;
        }
    }


}
