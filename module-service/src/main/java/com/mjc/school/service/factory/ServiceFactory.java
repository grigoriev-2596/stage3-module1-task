package com.mjc.school.service.factory;

import com.mjc.school.service.implementation.NewsService;

public class ServiceFactory {
    private static ServiceFactory INSTANCE;
    private final NewsService newsService = new NewsService();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFactory();
        }
        return INSTANCE;
    }

    public NewsService getNewsService() {
        return newsService;
    }
}
