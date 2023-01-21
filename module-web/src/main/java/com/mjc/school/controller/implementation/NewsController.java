package com.mjc.school.controller.implementation;

import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.factory.ServiceFactory;

import java.util.List;

public class NewsController {
    private final Service<NewsDtoRequest, NewsDtoResponse> newsService;

    public NewsController() {
        newsService = ServiceFactory.getInstance().getNewsService();
    }

    public NewsDtoResponse create(NewsDtoRequest request) {
        return newsService.create(request);
    }

    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    public NewsDtoResponse update(NewsDtoRequest request) {
        return newsService.update(request);
    }

    public Boolean delete(Long id) {
        return newsService.delete(id);
    }
}
