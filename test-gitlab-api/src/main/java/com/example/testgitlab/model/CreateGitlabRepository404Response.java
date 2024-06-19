package com.example.testgitlab.model;

import java.net.URI;
import java.util.Objects;
import com.example.testgitlab.model.AdditionalData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateGitlabRepository404Response
 */
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@JsonTypeName("createGitlabRepository_404_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-19T23:31:53.753466418+02:00[Europe/Madrid]")
public class CreateGitlabRepository404Response {

  private Integer code;

  private String title;

  private String detail;

  private AdditionalData additionalData;

  public CreateGitlabRepository404Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateGitlabRepository404Response(Integer code, String title, String detail) {
    this.code = code;
    this.title = title;
    this.detail = detail;
  }

  public CreateGitlabRepository404Response code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * A number that represents a type of errors, grouping those of similar characteristics
   * @return code
  */
  @NotNull 
  @Schema(name = "code", example = "404", description = "A number that represents a type of errors, grouping those of similar characteristics", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public CreateGitlabRepository404Response title(String title) {
    this.title = title;
    return this;
  }

  /**
   * resource-not-found
   * @return title
  */
  @NotNull @Size(max = 2000) 
  @Schema(name = "title", example = "Not found", description = "resource-not-found", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreateGitlabRepository404Response detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * A human friendly description of the error in english
   * @return detail
  */
  @NotNull @Size(max = 400) 
  @Schema(name = "detail", example = "The resource can't be found", description = "A human friendly description of the error in english", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public CreateGitlabRepository404Response additionalData(AdditionalData additionalData) {
    this.additionalData = additionalData;
    return this;
  }

  /**
   * Get additionalData
   * @return additionalData
  */
  @Valid 
  @Schema(name = "additionalData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalData")
  public AdditionalData getAdditionalData() {
    return additionalData;
  }

  public void setAdditionalData(AdditionalData additionalData) {
    this.additionalData = additionalData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateGitlabRepository404Response createGitlabRepository404Response = (CreateGitlabRepository404Response) o;
    return Objects.equals(this.code, createGitlabRepository404Response.code) &&
        Objects.equals(this.title, createGitlabRepository404Response.title) &&
        Objects.equals(this.detail, createGitlabRepository404Response.detail) &&
        Objects.equals(this.additionalData, createGitlabRepository404Response.additionalData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, detail, additionalData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateGitlabRepository404Response {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    additionalData: ").append(toIndentedString(additionalData)).append("\n");
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

