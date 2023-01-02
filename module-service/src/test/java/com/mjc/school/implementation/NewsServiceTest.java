package com.mjc.school.implementation;

import com.mjc.school.repository.factory.RepositoryFactory;
import com.mjc.school.service.factory.ServiceFactory;
import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.service.interfaces.Service;
import com.mjc.school.service.model.NewsDTORequest;
import com.mjc.school.service.model.NewsDTOResponse;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.utils.NewsMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class NewsServiceTest {
    private final Service<NewsDTORequest, NewsDTOResponse> service = ServiceFactory.getInstance().getNewsService();
    private final Repository<NewsModel> repository = RepositoryFactory.getInstance().getNewsRepository();
    private final NewsMapper mapper = Mappers.getMapper(NewsMapper.class);

    @Test
    void getById() {
        long id = 15;
        NewsDTOResponse serviceResponse = service.getById(id);
        assertEquals(id, serviceResponse.getId());
    }

    @Test
    void create() {
        NewsDTORequest request = new NewsDTORequest(null, "Test title", "Test content", (long) 14);
        NewsDTOResponse serviceResponse = service.create(request);
        List<NewsModel> news = repository.getAll();

        NewsModel modelFromRepo = news.get(news.size() - 1);
        NewsModel modelFromService = mapper.DTOResponseToModel(serviceResponse);

        assertNotNull(serviceResponse.getLastUpdateDate());
        assertNotNull(serviceResponse.getCreationDate());
        assertNotNull(serviceResponse.getCreationDate());

        assertEquals(modelFromService, modelFromRepo);

    }

    @Test
    void getAll() {
        List<NewsModel> serviceNews = mapper.listOfResponsesToListOfModel(service.getAll());
        List<NewsModel> repoNews = repository.getAll();

        assertEquals(repoNews, serviceNews);
    }

    @Test
    void update() {
        long id = 5, authorId = 7;
        String title = "Updated title", content = "Updated content";
        NewsDTORequest newsForUpdate = new NewsDTORequest(id, title, content, authorId);

        assertNotNull(service.update(newsForUpdate));
        NewsModel updatedNews = repository.getById(id);

        assertEquals(title, updatedNews.getTitle());
        assertEquals(content, updatedNews.getContent());
        assertEquals(authorId, updatedNews.getAuthorId());
        assertNotEquals(updatedNews.getLastUpdateDate(), updatedNews.getCreationDate());

    }

    @Test
    void delete() {
        long id = 7;
        assertTrue(service.delete(id));
        assertNull(repository.getById(id));
    }

}