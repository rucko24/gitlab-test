package contracts

import org.apache.http.protocol.HTTP
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
    description("should return an id")
    request {
        method POST()
        url '/api/v1/gitlab-repository'
        headers {
            header HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON
        }
        body (""""
                "name": "test-5",
                "description": "Example of project creation using gitlab api with java.",
                "path": "basic_project-5",
                "initialize_with_readme": "true",
                "visibility": "public",
                "default_branch": "master"
                """
        )
    }
    response {
        status 200
        headers {
            header(
                    HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON
            )
        }
        body(
                id: value("55196040")
        )
    }
}