package com.mjc.school.controller.implementation;

import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.model.NewsDtoRequest;
import com.mjc.school.service.model.NewsDtoResponse;

import java.util.List;

public class NewsController implements Controller<NewsDtoRequest, NewsDtoResponse> {
    Service<NewsDtoRequest, NewsDtoResponse> newsService = ServiceFactory.getInstance().getNewsService();

    @Override
    public NewsDtoResponse create(NewsDtoRequest request) {
        return newsService.create(request);
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest request) {
        return newsService.update(request);
    }

    @Override
    public Boolean delete(Long id) {
        return newsService.delete(id);
    }
}
