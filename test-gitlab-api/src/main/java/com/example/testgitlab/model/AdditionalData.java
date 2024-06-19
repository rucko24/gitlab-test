package com.example.testgitlab.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * More data available to understand or track the error
 */
@com.fasterxml.jackson.annotation.JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)

@Schema(name = "AdditionalData", description = "More data available to understand or track the error")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-19T23:31:53.753466418+02:00[Europe/Madrid]")
public class AdditionalData {

  private JsonNullable<@Size(max = 40000) Object> timestamp = JsonNullable.<Object>undefined();

  public AdditionalData timestamp(Object timestamp) {
    this.timestamp = JsonNullable.of(timestamp);
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  */
  @Size(max = 40000) 
  @Schema(name = "timestamp", example = "2019-04-22T09:54:18.471Z", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public JsonNullable<@Size(max = 40000) Object> getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(JsonNullable<Object> timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdditionalData additionalData = (AdditionalData) o;
    return equalsNullable(this.timestamp, additionalData.timestamp);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(timestamp));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdditionalData {\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

