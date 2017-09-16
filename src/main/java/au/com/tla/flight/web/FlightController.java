package au.com.tla.flight.web;


import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.tla.flight.model.Manufacturer;
import au.com.tla.flight.service.BrandManager;
import au.com.tla.flight.service.FlightManager;
import au.com.tla.flight.service.HibernateBrandManager;
import au.com.tla.flight.service.HibernateFlightManager;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());
    
    private FlightManager flightManager = new HibernateFlightManager();
    private BrandManager brandManager = new HibernateBrandManager();
    
    	
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Returning hello view");

        // Create the model Map for the View
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("now", new Date().toString());
        model.put("flights", this.flightManager.getFlights());
        
        logger.info("Fetching brands...");
        List<Manufacturer> brands = this.brandManager.getManufacturers();
        logger.info(brands + " brands");
        model.put("brands", brands);
        
        return new ModelAndView("hello", "model", model);
    }

    public void setFlightManager(FlightManager flightManager) {
    	this.flightManager = flightManager;
    }
}