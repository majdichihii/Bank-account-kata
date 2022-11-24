package com.kata.account.mapper;

import com.kata.account.model.Account;
import com.kata.account.model.PostAccountRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostAccountRequestBodyAccountMapper {
    Account PostAccountRequestToAccount(PostAccountRequestBody postAccountRequestBody);
}
