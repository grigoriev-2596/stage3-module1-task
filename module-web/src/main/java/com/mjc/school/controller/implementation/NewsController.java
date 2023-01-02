package com.mjc.school.controller.implementation;

import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.model.NewsDtoRequest;
import com.mjc.school.service.model.NewsDtoResponse;

import java.util.List;

public class NewsController {
    private final Service<NewsDtoRequest, NewsDtoResponse> newsService = ServiceFactory.getInstance().getNewsService();

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
