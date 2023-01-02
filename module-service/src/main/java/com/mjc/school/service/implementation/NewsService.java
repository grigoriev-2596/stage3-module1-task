package com.mjc.school.service.implementation;

import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.exceptions.ServiceException;
import com.mjc.school.service.model.NewsDtoRequest;
import com.mjc.school.service.model.NewsDtoResponse;
import com.mjc.school.service.utils.NewsMapper;
import com.mjc.school.service.validation.NewsValidator;
import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.repository.model.NewsModel;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

public class NewsService implements Service<NewsDtoRequest, NewsDtoResponse> {
    private final Repository<NewsModel> repository = RepositoryFactory.getInstance().getNewsRepository();
    private final NewsMapper mapper = Mappers.getMapper(NewsMapper.class);
    private final NewsValidator validator = NewsValidator.getInstance();

    @Override
    public NewsDtoResponse create(NewsDtoRequest request) {
        validator.validateNewsRequest(request);
        NewsModel model = mapper.DTORequestToModel(request);
        LocalDateTime creationDate = LocalDateTime.now();
        model.setCreationDate(creationDate);
        model.setLastUpdateDate(creationDate);
        model = repository.create(model);
        return mapper.modelToDTOResponse(model);
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return mapper.listOfModelsToListOfDTOResponse(repository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        validator.validateNewsId(id);
        NewsModel model = repository.readById(id);
        if (model == null) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return mapper.modelToDTOResponse(model);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest request) {
        validator.validateNewsRequest(request);
        validator.validateNewsId(request.getId());
        NewsModel requestModel = mapper.DTORequestToModel(request);
        requestModel.setLastUpdateDate(LocalDateTime.now());
        NewsModel updateModel = repository.update(requestModel);
        if (updateModel == null) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), request.getId()));
        }
        return mapper.modelToDTOResponse(updateModel);
    }

    @Override
    public boolean delete(Long id) {
        validator.validateNewsId(id);
        if (!repository.delete(id)) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return true;
    }
}
