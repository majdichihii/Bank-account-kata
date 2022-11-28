package com.kata.account.mapper;

import com.kata.account.model.Account;
import com.kata.account.model.PostAccountRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostAccountRequestBodyAccountMapper {
    Account PostAccountRequestToAccount(PostAccountRequestBody postAccountRequestBody);
}
