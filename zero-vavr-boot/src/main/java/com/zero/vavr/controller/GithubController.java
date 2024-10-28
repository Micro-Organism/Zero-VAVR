package com.zero.vavr.controller;

import com.zero.vavr.domain.entity.SystemUserEntity;
import com.zero.vavr.service.GithubService;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/github")
public class GithubController {

    @Autowired
    private GithubService githubService;

    @GetMapping(path = "/{username}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> get(@Validated @PathVariable String username
    ) {
        Try<SystemUserEntity> githubUserProfile = githubService.findGithubUser(username);

        if (githubUserProfile.isFailure()) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(githubUserProfile.getCause().getMessage());
        }

        if (githubUserProfile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Response is empty");
        }

        if (githubUserProfile.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body(githubUserProfile.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("username is not valid");

    }

    @GetMapping(path = "/fail/{username}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<?> getFail(@Validated @PathVariable String username
    ) {
        Try<SystemUserEntity> githubUserProfile = githubService.findGithubUserAndFail(username);

        if (githubUserProfile.isFailure()) {
            System.out.println("Fail case");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(githubUserProfile.getCause().getMessage());
        }

        if (githubUserProfile.isEmpty()) {
            System.out.println("Empty case");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Response is empty");
        }

        if (githubUserProfile.isSuccess()) {
            System.out.println("Success case");
            return ResponseEntity.status(HttpStatus.OK).body(githubUserProfile.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("username is not valid");

    }

}