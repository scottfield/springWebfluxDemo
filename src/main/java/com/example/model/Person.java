package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("user")
public class Person {
    @Id
    private Integer id;
    @Column("name")
    private String username;
    private String password;
}
