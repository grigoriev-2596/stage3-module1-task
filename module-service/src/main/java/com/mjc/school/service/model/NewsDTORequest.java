package com.mjc.school.service.model;

public class NewsDTORequest {
    private final Long id;
    private final String title;
    private final String content;
    private final Long authorId;

    public NewsDTORequest(Long id, String title, String content, Long authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
