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
    private final Repository<NewsModel> newsRepository = RepositoryFactory.getInstance().getNewsRepository();
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);
    private final NewsValidator newsValidator = NewsValidator.getInstance();

    @Override
    public NewsDtoResponse create(NewsDtoRequest request) {
        newsValidator.validateNewsRequest(request);
        NewsModel model = newsMapper.DTORequestToModel(request);
        LocalDateTime creationDate = LocalDateTime.now();
        model.setCreationDate(creationDate);
        model.setLastUpdateDate(creationDate);
        model = newsRepository.create(model);
        return newsMapper.modelToDTOResponse(model);
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsMapper.listOfModelsToListOfDTOResponse(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        newsValidator.validateNewsId(id);
        NewsModel model = newsRepository.readById(id);
        if (model == null) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return newsMapper.modelToDTOResponse(model);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest request) {
        newsValidator.validateNewsRequest(request);
        newsValidator.validateNewsId(request.getId());
        NewsModel requestModel = newsMapper.DTORequestToModel(request);
        requestModel.setLastUpdateDate(LocalDateTime.now());
        NewsModel updateModel = newsRepository.update(requestModel);
        if (updateModel == null) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), request.getId()));
        }
        return newsMapper.modelToDTOResponse(updateModel);
    }

    @Override
    public Boolean delete(Long id) {
        newsValidator.validateNewsId(id);
        if (!newsRepository.delete(id)) {
            throw new ServiceException(String.format(ErrorCode.NEWS_NOT_EXIST.toString(), id));
        }
        return true;
    }
}
