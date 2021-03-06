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

package components.job;

import cloud.colosseum.ColosseumComputeService;
import com.google.inject.Inject;
import models.Tenant;
import models.VirtualMachine;
import models.service.ModelService;

/**
 * Created by daniel on 14.10.15.
 */
public class DeleteVirtualMachineJob extends GenericJob<VirtualMachine> {

    @Inject public DeleteVirtualMachineJob(VirtualMachine virtualMachine,
        ModelService<VirtualMachine> modelService, ModelService<Tenant> tenantModelService,
        ColosseumComputeService colosseumComputeService, Tenant tenant) {
        super(virtualMachine, modelService, tenantModelService, colosseumComputeService, tenant);
    }

    @Override
    protected void doWork(VirtualMachine virtualMachine, ModelService<VirtualMachine> modelService,
        ColosseumComputeService computeService, Tenant tenant) throws JobException {

        computeService.deleteVirtualMachine(virtualMachine);
        modelService.delete(virtualMachine);

    }

    @Override public boolean canStart() {
        return true;
    }
}
