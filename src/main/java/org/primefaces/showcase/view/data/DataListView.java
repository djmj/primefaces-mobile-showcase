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
package org.primefaces.showcase.view.data;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@Named
@ViewScoped
public class DataListView implements Serializable 
{
    
    private List<Car> cars1;
    
    private List<Car> cars2;
    
    private List<Car> cars3;
    
    private Car selectedCar;
    
    @Inject
    @ManagedProperty("#{carService}")
    private CarService service;
    
    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        cars2 = service.createCars(5);
        cars3 = service.createCars(50);
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public List<Car> getCars2() {
        return cars2;
    }

    public List<Car> getCars3() {
        return cars3;
    }    

    public void setService(CarService service) {
        this.service = service;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
}
