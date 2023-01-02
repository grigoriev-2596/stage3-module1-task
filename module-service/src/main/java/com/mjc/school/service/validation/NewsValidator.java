package com.mjc.school.service.validation;

import com.mjc.school.service.exceptions.ErrorCode;
import com.mjc.school.service.model.NewsDTORequest;
import com.mjc.school.service.exceptions.ValidatorException;

public class NewsValidator {
    private static NewsValidator INSTANCE;
    private static final int TITLE_MIN_LENGTH = 5;
    private static final int TITLE_MAX_LENGTH = 30;
    private static final int CONTENT_MIN_LENGTH = 5;
    private static final int CONTENT_MAX_LENGTH = 255;
    private static final int MAX_AUTHOR_ID = 20;

    private NewsValidator() {
    }

    public static NewsValidator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsValidator();
        }
        return INSTANCE;
    }

    private void validateStringLength(String str, String fieldName, int min, int max) {
        if (str == null) {
            throw new ValidatorException(String.format(ErrorCode.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
        if (str.length() < min || str.length() > max) {
            throw new ValidatorException(String.format(ErrorCode.NEWS_STRING_LENGTH_IS_INVALID.toString(), fieldName, min, max));
        }
    }

    public void validateNewsId(Long id) {
        validateNullId(id, "news id");
        if (id < 1) {
            throw new ValidatorException(ErrorCode.NEWS_ID_IS_INVALID.toString());
        }
    }

    public void validateAuthorId(Long id) {
        validateNullId(id, "author id");
        if (id < 1 || id > MAX_AUTHOR_ID) {
            throw new ValidatorException(String.format(ErrorCode.AUTHOR_ID_IS_INVALID.toString(), MAX_AUTHOR_ID));
        }
    }

    private void validateNullId(Long id, String fieldName) {
        if (id == null) {
            throw new ValidatorException(String.format(ErrorCode.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
    }

    public void validateNewsRequest(NewsDTORequest request) {
        validateStringLength(request.getTitle(), "title", TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
        validateStringLength(request.getContent(), "content", CONTENT_MIN_LENGTH, CONTENT_MAX_LENGTH);
        validateAuthorId(request.getAuthorId());
    }
}
