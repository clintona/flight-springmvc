package au.com.tla.flight.web;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import au.com.tla.flight.service.HibernateFlightManager;
import junit.framework.TestCase;

public class FlightControllerTest extends TestCase {

    public void testHandleRequestView() throws Exception{		
        FlightController controller = new FlightController();
        controller.setFlightManager(new HibernateFlightManager());
        ModelAndView modelAndView = controller.handleRequest(null, null);		
        assertEquals("hello", modelAndView.getViewName());
        
        assertNotNull(modelAndView.getModel());
        Map<?, ?> modelMap = (Map<?, ?>)modelAndView.getModel().get("model");
        String nowValue = (String)modelMap.get("now");
        assertNotNull(nowValue);
    }
}

