package com.mjc.school.repository.implementation;

import com.mjc.school.repository.interfaces.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.data.NewsDataSource;

import java.util.List;
import java.util.Objects;

public class NewsRepository implements Repository<NewsModel> {
    private final NewsDataSource dataSource = NewsDataSource.getInstance();

    @Override
    public NewsModel create(NewsModel newsModel) {
        newsModel.setId(dataSource.getLastNewsId());
        dataSource.getNews().add(newsModel);
        return newsModel;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews();
    }

    @Override
    public NewsModel readById(Long id) {
        return dataSource.getNews().stream()
                .filter(news -> Objects.equals(news.getId(), id))
                .findAny()
                .orElse(null);
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        NewsModel modelToUpdate = readById(newsModel.getId());
        if (modelToUpdate == null) {
            return null;
        }
        modelToUpdate.setAuthorId(newsModel.getAuthorId());
        modelToUpdate.setContent(newsModel.getContent());
        modelToUpdate.setTitle(newsModel.getTitle());
        modelToUpdate.setLastUpdateDate(newsModel.getLastUpdateDate());
        return modelToUpdate;
    }

    @Override
    public Boolean delete(Long id) {
        NewsModel toDelete = readById(id);
        if (toDelete == null) return false;
        dataSource.getNews().remove(toDelete);
        return true;
    }
}
