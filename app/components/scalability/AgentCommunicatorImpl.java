/*
 * Copyright (c) 2014-2015 University of Ulm
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package components.scalability;

import de.uniulm.omi.cloudiator.visor.client.ClientBuilder;
import de.uniulm.omi.cloudiator.visor.client.ClientController;
import de.uniulm.omi.cloudiator.visor.client.entities.*;
import models.MonitorInstance;
import models.RawMonitor;
import models.generic.ExternalReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Frank on 01.09.2015.
 */
public class AgentCommunicatorImpl implements AgentCommunicator {
    private final String protocol;
    private final String ip;
    private final int port;
    private final ClientController<Monitor> controller;

    public AgentCommunicatorImpl(String protocol, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.protocol = protocol;

        //get the controller for the cloud entity
        this.controller =
            ClientBuilder.getNew()
                // the base url
                .url(protocol + "://" + ip + ":" + port)
                    // the entity to get the controller for.
                .build(Monitor.class);

    }

    @Override public void addSensorMonitor(String idMonitorInstance, String className, String metricName, long interval,
                          TimeUnit unit, Map<String, String> configs)
    {
        return;
     }


    @Override public void removeSensorMonitor(String className, String metricName, long interval, TimeUnit unit)
    {
        return;
    }


    @Override public void removeSensorMonitorForComponent(String className, String metricName, long interval,
                                         TimeUnit unit, String componentId)    {
        return;
    }


    @Override public void removeSensorMonitor(SensorMonitor monitor)    {
        return;
    }


    @Override public void addSensorMonitorForComponent(String idMonitorInstance, String className, String metricName,
                                      long interval, TimeUnit unit, String componentId, Map<String, String> configs)    {
        return;
    }


    @Override public List<SensorMonitor> getSensorMonitorWithSameValues(String className, String metricName,
                                                       String componentName)
    {
        return null;
    }


    @Override public void updateSensorMonitor(MonitorInstance mi)    {
        return;
    }


    @Override public boolean hasSameContext(Monitor mon, String contextKey, String contextValue)    {
        return false;
    }


    @Override public SensorMonitor copyValueFromMonitorInstance(SensorMonitor m, MonitorInstance mi)    {
        return null;
    }


    @Override public int getPort()    {
        return 1024;
    }


    @Override public String getIp()
    {
        return "1.2.3.4";
    }

    @Override public String getProtocol()
    {
        return "tcp";
    }

}
