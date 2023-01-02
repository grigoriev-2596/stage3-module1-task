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
    public List<NewsDtoResponse> getAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse getById(long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest request) {
        return newsService.update(request);
    }

    @Override
    public boolean delete(long id) {
        return newsService.delete(id);
    }
}
