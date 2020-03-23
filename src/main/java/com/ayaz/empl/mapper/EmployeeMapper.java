package com.ayaz.empl.mapper;


import com.ayaz.empl.dto.EmployeeDto;
import com.ayaz.empl.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surName", target = "surName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")})
    EmployeeDto toEmployeeDto(Employee employee);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surName", target = "surName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")})
    Employee toEmployee(EmployeeDto employeeDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "surName", target = "surName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")})
    void  copyToEmployee(EmployeeDto employeeDto, @MappingTarget Employee employee);

}
