package com.example.testgitlab.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GitlabRepositoryRequest
 */
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-19T23:31:53.753466418+02:00[Europe/Madrid]")
public class GitlabRepositoryRequest {

  private String name;

  private String description;

  private String path;

  private String initializeWithReadme;

  private String visibility;

  private String defaultBranch;

  public GitlabRepositoryRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GitlabRepositoryRequest(String name, String path) {
    this.name = name;
    this.path = path;
  }

  public GitlabRepositoryRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "test", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GitlabRepositoryRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", example = "Example of project creation using gitlab api with java.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public GitlabRepositoryRequest path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Get path
   * @return path
  */
  @NotNull 
  @Schema(name = "path", example = "basic_project", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("path")
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public GitlabRepositoryRequest initializeWithReadme(String initializeWithReadme) {
    this.initializeWithReadme = initializeWithReadme;
    return this;
  }

  /**
   * Get initializeWithReadme
   * @return initializeWithReadme
  */
  
  @Schema(name = "initialize_with_readme", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("initialize_with_readme")
  public String getInitializeWithReadme() {
    return initializeWithReadme;
  }

  public void setInitializeWithReadme(String initializeWithReadme) {
    this.initializeWithReadme = initializeWithReadme;
  }

  public GitlabRepositoryRequest visibility(String visibility) {
    this.visibility = visibility;
    return this;
  }

  /**
   * Get visibility
   * @return visibility
  */
  
  @Schema(name = "visibility", example = "public", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("visibility")
  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public GitlabRepositoryRequest defaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
    return this;
  }

  /**
   * Get defaultBranch
   * @return defaultBranch
  */
  
  @Schema(name = "default_branch", example = "master", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("default_branch")
  public String getDefaultBranch() {
    return defaultBranch;
  }

  public void setDefaultBranch(String defaultBranch) {
    this.defaultBranch = defaultBranch;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GitlabRepositoryRequest gitlabRepositoryRequest = (GitlabRepositoryRequest) o;
    return Objects.equals(this.name, gitlabRepositoryRequest.name) &&
        Objects.equals(this.description, gitlabRepositoryRequest.description) &&
        Objects.equals(this.path, gitlabRepositoryRequest.path) &&
        Objects.equals(this.initializeWithReadme, gitlabRepositoryRequest.initializeWithReadme) &&
        Objects.equals(this.visibility, gitlabRepositoryRequest.visibility) &&
        Objects.equals(this.defaultBranch, gitlabRepositoryRequest.defaultBranch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, path, initializeWithReadme, visibility, defaultBranch);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GitlabRepositoryRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    initializeWithReadme: ").append(toIndentedString(initializeWithReadme)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    defaultBranch: ").append(toIndentedString(defaultBranch)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

