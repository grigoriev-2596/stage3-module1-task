package com.mjc.school.controller.factory;

import com.mjc.school.controller.implementation.NewsController;

public class ControllerFactory {
    private static ControllerFactory INSTANCE;
    private final NewsController newsController = new NewsController();

    private ControllerFactory() {
    }

    public static ControllerFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ControllerFactory();
        }
        return INSTANCE;
    }

    public NewsController getNewsController() {
        return newsController;
    }
}
