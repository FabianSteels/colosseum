/*
 * Copyright (c) 2014-2015 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package models;

import com.google.common.collect.ImmutableMap;
import models.generic.Model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import java.util.Map;

/**
 * Created by daniel on 06.10.15.
 */
@Entity public class TemplateOptions extends Model {

    @ElementCollection @MapKeyColumn(name = "tagName") @Column(name = "tagValue")
    private Map<String, String> tags;

    /**
     * Empty constructor for hibernate.
     */
    protected TemplateOptions(){

    }

    public Map<String, String> tags() {
        return ImmutableMap.copyOf(tags);
    }

    public void addTag(String tagName, String tagValue) {
        this.tags.put(tagName, tagValue);
    }
}
