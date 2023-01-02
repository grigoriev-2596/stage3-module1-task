package com.mjc.school.controller.implementation;

import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.controller.interfaces.Controller;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.model.NewsDTORequest;
import com.mjc.school.service.model.NewsDTOResponse;

import java.util.List;

public class NewsController implements Controller<NewsDTORequest, NewsDTOResponse> {
    Service<NewsDTORequest, NewsDTOResponse> service = ServiceFactory.getInstance().getNewsService();

    @Override
    public NewsDTOResponse create(NewsDTORequest request) {
        return service.create(request);
    }

    @Override
    public List<NewsDTOResponse> getAll() {
        return service.getAll();
    }

    @Override
    public NewsDTOResponse getById(long id) {
        return service.getById(id);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest request) {
        return service.update(request);
    }

    @Override
    public boolean delete(long id) {
        return service.delete(id);
    }
}
