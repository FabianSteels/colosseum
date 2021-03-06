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

package cloud.sync.solutions;

import cloud.resources.ImageInLocation;
import cloud.sync.Problem;
import cloud.sync.Solution;
import cloud.sync.SolutionException;
import cloud.sync.problems.ImageProblems;
import com.google.inject.Inject;
import models.CloudCredential;
import models.Image;
import models.service.ImageModelService;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by daniel on 07.05.15.
 */
public class ConnectImageToCredential implements Solution {

    private final ImageModelService imageModelService;

    @Inject public ConnectImageToCredential(ImageModelService imageModelService) {

        this.imageModelService = imageModelService;
    }

    @Override public boolean isSolutionFor(Problem problem) {
        return problem instanceof ImageProblems.ImageMissesCredential;
    }

    @Override public void applyTo(Problem problem) throws SolutionException {
        checkArgument(isSolutionFor(problem));
        ImageInLocation imageInLocation =
            ((ImageProblems.ImageMissesCredential) problem).getImageInLocation();

        Image modelImage = imageModelService.getByRemoteId(imageInLocation.id());
        CloudCredential cloudCredential = imageInLocation.credential();

        if (modelImage == null || cloudCredential == null) {
            throw new SolutionException();
        }

        modelImage.addCloudCredential(cloudCredential);
        imageModelService.save(modelImage);
    }



}
