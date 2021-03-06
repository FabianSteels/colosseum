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

package dtos.validation.validators;

import dtos.validation.AbstractValidator;
import dtos.validation.ValidationErrorMessage;
import dtos.validation.ValidationException;

import java.util.function.Predicate;

/**
 * Created by daniel on 18.09.15.
 */
public class PredicateValidator<T> extends AbstractValidator<T> {

    private final Predicate<? super T> predicate;

    public PredicateValidator(Predicate<? super T> predicate) {
        this.predicate = predicate;
    }

    @Override protected void validation(T t) throws ValidationException {

        if (!predicate.test(t)) {
            addError(ValidationErrorMessage.of("The expression did not match."));
        }
    }

}
