
package au.com.tla.flight.web;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.com.tla.flight.model.Manufacturer;
import au.com.tla.flight.model.validator.ManufacturerValidator;
import au.com.tla.flight.service.BrandManager;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

	private static String DUP_KEY_SQL_STATE = "23505";
	public static String MODEL_BRANDS_LIST = "brands";
	public static String MODEL_BRAND = "manufacturer";
	public static String VIEW_BRANDS = "brandedit";
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private BrandManager manager;

    @Autowired
    public BrandController(BrandManager manager) {
    	logger.info("BrandController: " + manager);
        this.manager = manager;
    }
    
    
    /**
     * For every request for this controller, this will 
     * fetch/create a Manufacturer instance for the form.
     */
    @ModelAttribute
    public Manufacturer newRequest(@RequestParam(required=false) String id) {
        return (id != null ? manager.getById(id) : new Manufacturer());
    }

    /**
     * Fetch a List of Manufacturers for display.
     * 
     * <p>Expected HTTP GET and request '/brands'.</p>
     */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getBrands() {
    	List<Manufacturer> brands = manager.getManufacturers();
    	ModelAndView mav = new ModelAndView(VIEW_BRANDS);
    	
    	logger.info("BrandController.getBrands: brands=" + brands.size());
    	mav.addObject(MODEL_BRANDS_LIST, brands);
    	return mav;
    }
    
    
    /**
     * <p>Saves a new Manufacturer. 
     * The Manufacturer object is auto created using form values.</p>
     * 
     * <p>Expected HTTP POST and request '/brands'.</p>
     */
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView add(Manufacturer newBrand, BindingResult result, Model model) {
    	// validate data
    	new ManufacturerValidator().validate(newBrand, result);
    	if (!result.hasErrors()) {
	    	try {
	    		manager.createManufacturer(newBrand);
	    		model.addAttribute(new Manufacturer()); // clear model attribute
	    	} catch (JDBCException e1) {
	    		logger.error(e1);
	    		result.rejectValue("name", null, getErrorMessage(e1, newBrand));
	    	} catch (Exception e2) {
	    		logger.error(e2);
	    		result.rejectValue("name", null, getErrorMessage(e2, newBrand));
	    	}
    	}
        return getBrands();
    }

    
    /**
     * <p>Updates an existing Manufacturer. 
     * The Manufacturer object is auto created using form values.</p>
     * 
     * <p>Expected HTTP POST and request '/brands/update'.</p>
     */
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ModelAndView update(Manufacturer brand, BindingResult result, Model model) {
    	// validate data
    	new ManufacturerValidator().validate(brand, result);
    	if (!result.hasErrors()) {
	    	try {
	    		manager.save(brand);
	    		model.addAttribute(new Manufacturer()); // clear model attribute
	    	} catch (JDBCException e1) {
	    		logger.error(e1);
	    		result.rejectValue("name", null, getErrorMessage(e1, brand));
	    	} catch (Exception e2) {
	    		logger.error(e2);
	    		result.rejectValue("name", null, getErrorMessage(e2, brand));
	    	}
    	}
        return getBrands();
    }
    
    
    /**
     * Checks for a specific JDBC 'duplicate key' Exception, 
     * else returns a standard error message.
     * @param e
     * @param brand
     * @return
     */
    /*package*/ String getErrorMessage(Exception e, Manufacturer brand) {
    	String errMsg = "Cannot save: " + e.getMessage();
    	if (e instanceof JDBCException) {
			if (((JDBCException)e).getSQLState().contains(DUP_KEY_SQL_STATE)) {
				errMsg = brand.getName() + " already exists";
			}
    	}
    	return errMsg;
    }
    
    /**
     * <p>Deletes a brand.</p>
     * 
     * <p>Expected HTTP POST and request '/brands/delete'.</p>
     */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ModelAndView delete(Manufacturer brand, Model model) {
    	logger.info("Delete " + brand);
        manager.deleteManufacturer(brand);
        model.addAttribute(new Manufacturer()); // clear model attribute
        return getBrands();
    }

    
    /**
     * <p>Selects an existing Manufacturer for editing.</p>
     * 
     * <p>Expected HTTP POST and request '/brands/{name}'.</p>
     */
    @RequestMapping(value="/{name}", method=RequestMethod.GET)
    public String select(@PathVariable String name, Model model) {
    	String returnPath = VIEW_BRANDS;
    	
    	// fetch the list of brands for display
    	List<Manufacturer> brands = manager.getManufacturers();
        model.addAttribute(MODEL_BRANDS_LIST, brands);
        
    	// load the selected brand for display
    	Manufacturer brand = manager.getById(name);
    	// TODO: add error handling if not found
    	model.addAttribute(MODEL_BRAND, brand);
    	
    	return returnPath;
    }
}
	                