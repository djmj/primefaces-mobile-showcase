/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.view.data.datatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.Car;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyCarDataModel extends LazyDataModel<Car> {
    
    private List<Car> datasource;
    
    public LazyCarDataModel(List<Car> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Car getRowData(String rowKey) {
        for(Car car : datasource) {
            if(car.getId().equals(rowKey))
                return car;
        }

        return null;
    }

    @Override
    public Object getRowKey(Car car) {
        return car.getId();
    }

    @Override
    public List<Car> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) 
    {
        List<Car> data = new ArrayList<Car>();

        //filter
        for(Car car : datasource) {
            boolean match = true;

            if (filterBy != null) {
                for (Iterator<FilterMeta> it = filterBy.values().iterator(); it.hasNext();) {
                    try {
                    	FilterMeta filterMeta = it.next();
                        String filterProperty = filterMeta.getFilterField();
                        Object filterValue = filterMeta.getFilterValue();
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));

                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(car);
            }
        }

        //sort
        if(sortBy != null) {
        	SortMeta sortMeta = sortBy.values().iterator().next();
            Collections.sort(data, new LazySorter(sortMeta.getSortField(), sortMeta.getSortOrder()));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}