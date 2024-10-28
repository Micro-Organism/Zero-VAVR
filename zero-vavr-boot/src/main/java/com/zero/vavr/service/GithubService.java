/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zero.vavr.service;

import com.zero.vavr.domain.entity.SystemUserEntity;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubService {

    @Autowired
    private RestTemplate restTemplate;

    public Try<SystemUserEntity> findGithubUser(String username) {
        return Try.of(() -> restTemplate.getForObject("https://api.github.com/users/{username}", SystemUserEntity.class, username));

    }

    public Try<SystemUserEntity> findGithubUserAndFail(String username) {
        return Try.of(() -> restTemplate.getForObject("https://api.twitter.com/users/fail/{username}", SystemUserEntity.class, username));
    }

}