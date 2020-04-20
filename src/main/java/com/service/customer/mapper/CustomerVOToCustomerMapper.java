package com.service.customer.mapper;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerVOToCustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer map(CustomerVO customer);
}
