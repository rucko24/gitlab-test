package com.example.testgitlab.gitlabexceptions;

public class GitlabRepositoryException extends RuntimeException {

    public GitlabRepositoryException(String message) {
        super(message);
    }
}
