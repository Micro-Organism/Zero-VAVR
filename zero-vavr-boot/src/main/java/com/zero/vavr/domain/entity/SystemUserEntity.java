package com.zero.vavr.domain.entity;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SystemUserEntity {
        
    private String id;
    private String login;
    private String location;

}