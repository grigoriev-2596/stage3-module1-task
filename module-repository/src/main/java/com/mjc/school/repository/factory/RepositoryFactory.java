package com.mjc.school.repository.factory;

import com.mjc.school.repository.implementation.NewsRepository;

public class RepositoryFactory {
    private static RepositoryFactory INSTANCE;
    private final NewsRepository newsRepository = new NewsRepository();

    private RepositoryFactory() {
    }

    public static RepositoryFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryFactory();
        }
        return INSTANCE;
    }

    public NewsRepository getNewsRepository() {
        return newsRepository;
    }
}
