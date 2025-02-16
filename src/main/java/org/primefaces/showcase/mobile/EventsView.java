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
package org.primefaces.showcase.mobile;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.mobile.event.SwipeEvent;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@Named(value="eventsView")
@ViewScoped
public class EventsView implements Serializable 
{
	private static final long serialVersionUID = 6973826516216171411L;

	private List<Car> cars;
    
    @Inject
    @ManagedProperty("#{carService}")
    private CarService service;

    @PostConstruct
    public void init() {
        cars = service.createCars(10);
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public void setService(CarService service) {
        this.service = service;
    }
    
    public void swipeCar(SwipeEvent event) {
        Car car = (Car) event.getData();
        cars.remove(car);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Swiped", "Removed: " + car.getId()));
    }
   
    
    public void tapCar(SelectEvent<Car> event) {
        FacesMessage msg = new FacesMessage("Car Selected", event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
