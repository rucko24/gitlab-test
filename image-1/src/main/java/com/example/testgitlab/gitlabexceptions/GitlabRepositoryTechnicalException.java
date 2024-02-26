package com.example.testgitlab.gitlabexceptions;

public class GitlabRepositoryTechnicalException extends RuntimeException {

    public GitlabRepositoryTechnicalException(String message) {
        super(message);
    }

}
