/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package app;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.HomePage;

@DomainService
@Hidden
public class ToDoAppDashboardService  {

    //region > identification in the UI
    // //////////////////////////////////////

    private static final String ID = "dashboard";

    public String getId() {
        return ID;
    }

    public String iconName() {
        return ID;
    }
    //endregion

    //region > lookup (action)
    // //////////////////////////////////////
    @ActionSemantics(Of.SAFE)
    @HomePage
    public ToDoAppDashboard lookup() {
        return container.newViewModelInstance(ToDoAppDashboard.class, ID);
    }

    //endregion

    //region > injected services
    // //////////////////////////////////////

    @javax.inject.Inject
    private DomainObjectContainer container;

    //endregion

}
