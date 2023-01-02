package com.mjc.school.implementation;

import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.model.NewsDtoRequest;
import com.mjc.school.service.model.NewsDtoResponse;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.utils.NewsMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class NewsServiceTest {
    private final Service<NewsDtoRequest, NewsDtoResponse> newsService = ServiceFactory.getInstance().getNewsService();
    private final Repository<NewsModel> newsRepository = RepositoryFactory.getInstance().getNewsRepository();
    private final NewsMapper newsMapper = Mappers.getMapper(NewsMapper.class);

    @Test
    void getById() {
        long id = 15;
        NewsDtoResponse serviceResponse = newsService.readById(id);
        assertEquals(id, serviceResponse.getId());
    }

    @Test
    void create() {
        NewsDtoRequest request = new NewsDtoRequest(null, "Test title", "Test content", (long) 14);
        NewsDtoResponse serviceResponse = newsService.create(request);
        List<NewsModel> news = newsRepository.readAll();

        NewsModel modelFromRepo = news.get(news.size() - 1);
        NewsModel modelFromService = newsMapper.DTOResponseToModel(serviceResponse);

        assertNotNull(serviceResponse.getLastUpdateDate());
        assertNotNull(serviceResponse.getCreationDate());
        assertNotNull(serviceResponse.getCreationDate());

        assertEquals(modelFromService, modelFromRepo);

    }

    @Test
    void getAll() {
        List<NewsModel> serviceNews = newsMapper.listOfResponsesToListOfModel(newsService.readAll());
        List<NewsModel> repoNews = newsRepository.readAll();

        assertEquals(repoNews, serviceNews);
    }

    @Test
    void update() {
        long id = 5, authorId = 7;
        String title = "Updated title", content = "Updated content";
        NewsDtoRequest newsForUpdate = new NewsDtoRequest(id, title, content, authorId);

        assertNotNull(newsService.update(newsForUpdate));
        NewsModel updatedNews = newsRepository.readById(id);

        assertEquals(title, updatedNews.getTitle());
        assertEquals(content, updatedNews.getContent());
        assertEquals(authorId, updatedNews.getAuthorId());
        assertNotEquals(updatedNews.getLastUpdateDate(), updatedNews.getCreationDate());

    }

    @Test
    void delete() {
        long id = 7;
        assertTrue(newsService.delete(id));
        assertNull(newsRepository.readById(id));
    }

}