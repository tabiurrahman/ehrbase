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
package org.ehrbase.api.service;

import org.ehrbase.api.dto.AqlQueryRequest;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryResultDto;

public interface AqlQueryService {

    /**
     * simple query where the full json expression contains both query (key = 'q') and optional
     * parameters (key = 'query-parameters')
     *
     * @param aqlQueryRequest to perform
     * @return aqlQueryResult
     */
    QueryResultDto query(AqlQueryRequest aqlQueryRequest);
}
