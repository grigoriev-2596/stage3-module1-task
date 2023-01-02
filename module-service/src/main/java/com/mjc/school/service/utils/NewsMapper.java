package com.mjc.school.service.utils;

import com.mjc.school.service.model.NewsDTORequest;
import com.mjc.school.service.model.NewsDTOResponse;
import com.mjc.school.repository.model.NewsModel;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@org.mapstruct.Mapper
public interface NewsMapper {
    @Mappings({@Mapping(
            target = "creationDate",
            ignore = true
    ), @Mapping(
            target = "lastUpdateDate",
            ignore = true
    )})
    NewsModel DTORequestToModel(NewsDTORequest dto);

    NewsModel DTOResponseToModel(NewsDTOResponse dto);

    NewsDTOResponse modelToDTOResponse(NewsModel model);

    List<NewsDTOResponse> listOfModelsToListOfDTOResponse(List<NewsModel> modelList);

    List<NewsModel> listOfResponsesToListOfModel(List<NewsDTOResponse> responseList);
}
