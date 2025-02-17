/*
 * Copyright (c) 2024 vitasystems GmbH.
 *
 * This file is part of project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.rest.openehr.specification;

import com.nedap.archie.rm.ehr.EhrStatus;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ehrbase.openehr.sdk.response.dto.EhrResponseData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "EHR")
@SuppressWarnings("java:S107")
public interface EhrApiSpecification {

    @Operation(
            summary = "Create EHR",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description =
                                "Created, is returned when the EHR has been successfully created. \n"
                                        + "The new EHR resource is returned in the body when the request's <code>Prefer</code>  header value is <code>return=representation</code> , otherwise only headers are returned."),
                @ApiResponse(
                        responseCode = "400",
                        description =
                                "Bad Request, is returned when the request URL or body (if provided) could not be parsed or has invalid content.",
                        content = @Content(schema = @Schema(hidden = true))),
                @ApiResponse(
                        responseCode = "409",
                        description =
                                "Conflict, Unable to create a new EHR due to a conflict with an already existing EHR with the same subject id, namespace pair, whenever EHR_STATUS is supplied.",
                        content = @Content(schema = @Schema(hidden = true)))
            },
            externalDocs =
                    @ExternalDocumentation(
                            url =
                                    "https://specifications.openehr.org/releases/ITS-REST/latest/ehr.html#tag/EHR/operation/ehr_create"))
    ResponseEntity<EhrResponseData> createEhr(
            String openehrVersion,
            String openehrAuditDetails,
            String prefer,
            @Parameter(hidden = true, allowEmptyValue = true) EhrStatus ehrStatus);

    @Operation(
            summary = "Create EHR with id",
            responses = {
                @ApiResponse(
                        responseCode = "201",
                        description =
                                "Created, is returned when the EHR has been successfully created. The new EHR resource is returned in the body when the request's <code>Prefer</code>  header value is <code>return=representation</code> , otherwise only headers are returned."),
                @ApiResponse(
                        responseCode = "400",
                        description =
                                "Bad Request, is returned when the request URL or body (if provided) could not be parsed or has invalid content.",
                        content = @Content(schema = @Schema(hidden = true))),
                @ApiResponse(
                        responseCode = "409",
                        description =
                                "Conflict, Unable to create a new EHR due to a conflict with an already existing EHR with the same subject id, namespace pair, whenever EHR_STATUS is supplied.",
                        content = @Content(schema = @Schema(hidden = true)))
            },
            requestBody =
                    @RequestBody(
                            content = {
                                @Content(
                                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                                        array = @ArraySchema(schema = @Schema(implementation = EhrResponseData.class)),
                                        examples =
                                                @ExampleObject(
                                                        """
                                        {
                                            "archetype_node_id": "openEHR-EHR-EHR_STATUS.generic.v1",
                                            "name": {
                                                "value": "EHR status"
                                            },
                                            "uid": {
                                                "_type": "OBJECT_VERSION_ID",
                                                "value": "9e3eb79b-1caa-4ab9-8cd4-d374b7c42bb4::local.ehrbase.org::1"
                                            },
                                            "subject": {
                                                "_type": "PARTY_SELF"
                                            },
                                            "is_queryable": true,
                                            "is_modifiable": true
                                        }
                                        """))
                            }),
            externalDocs =
                    @ExternalDocumentation(
                            url =
                                    "https://specifications.openehr.org/releases/ITS-REST/1.0.3/ehr.html#tag/EHR/operation/ehr_get_by_id"))
    ResponseEntity<EhrResponseData> createEhrWithId(
            String openehrVersion, String openehrAuditDetails, String prefer, String ehrIdString, EhrStatus ehrStatus);

    @Operation(
            summary = "Get EHR by id",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK, is returned when the requested EHR resource is successfully retrieved."),
                @ApiResponse(
                        responseCode = "404",
                        description = "Not Found, is returned when an EHR with <code>ehr_id</code>  does not exist.",
                        content = @Content(schema = @Schema(hidden = true)))
            },
            externalDocs =
                    @ExternalDocumentation(
                            url =
                                    "https://specifications.openehr.org/releases/ITS-REST/latest/ehr.html#tag/EHR/operation/ehr_get_by_id"))
    ResponseEntity<EhrResponseData> getEhrById(String ehrIdString);

    @Operation(
            summary = "Get EHR summary by subject id and namespace",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "OK, is returned when the requested EHR resource is successfully retrieved."),
                @ApiResponse(
                        responseCode = "404",
                        description =
                                "Not Found, is returned when an EHR with supplied subject parameters does not exist.",
                        content = @Content(schema = @Schema(hidden = true)))
            },
            externalDocs =
                    @ExternalDocumentation(
                            url =
                                    "https://specifications.openehr.org/releases/ITS-REST/latest/ehr.html#tag/EHR/operation/ehr_get_by_subject"))
    ResponseEntity<EhrResponseData> getEhrBySubject(String subjectId, String subjectNamespace);
}
