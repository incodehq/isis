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

import java.util.List;
import org.apache.isis.applib.annotation.Collection;
import org.apache.isis.applib.annotation.CollectionLayout;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.annotation.RenderType;

@DomainObject(nature = Nature.VIEW_MODEL)
public class ToDoAppDashboard {

    //region > identification in the UI
    public String title() {
        return "Dashboard";
    }
    //endregion

//    //region > memento (property)
//    private String memento = "Dashboard";
//
//    @Property(hidden = Where.EVERYWHERE)
//    @MemberOrder(sequence = "1")
//    public String getMemento() {
//        return memento;
//    }
//
//    public void setMemento(final String memento) {
//        this.memento = memento;
//    }
//    //endregion

    //region > getAnalysisByCategory (collection)
    @CollectionLayout(
            named="By Category",
            render = RenderType.EAGERLY
    )
    @Collection(
            editing = Editing.DISABLED
    )
    public List<ToDoItemsByCategoryViewModel> getAnalysisByCategory() {
        return toDoItemAnalysis.toDoItemsByCategory();
    }
    //endregion

    //region > getAnalysisByDateRange (collection)
    @CollectionLayout(
            named="By Date Range",
            render = RenderType.EAGERLY
    )
    @Collection(
            editing = Editing.DISABLED
    )
    public List<ToDoItemsByDateRangeViewModel> getAnalysisByDateRange() {
        return toDoItemAnalysis.toDoItemsByDateRange();
    }
    //endregion

    //region > injected services
    @javax.inject.Inject
    private ToDoItemAnalysis toDoItemAnalysis;
    //endregion

}
