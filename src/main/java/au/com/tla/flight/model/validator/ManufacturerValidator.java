package au.com.tla.flight.model.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.tla.flight.model.Manufacturer;

public class ManufacturerValidator implements Validator {
	

	public static String PREFIX_HTTP = "http://";
	
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return Manufacturer.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        Manufacturer man = (Manufacturer) obj;
        if (man == null) {
            errors.rejectValue("manufacturer", "error.not-specified", null, "Value required.");
        } else {
            String name = man.getName();
            if (name == null || name.trim().isEmpty()) {
            	errors.rejectValue("name", null, null, "Non-empty value required.");
            }
            
            String website = man.getWebsite();
            if (website.trim().length() < 11) {
            	errors.rejectValue("website", null, null, "Must be at least 11 characters.");
            }
            if (!website.startsWith(PREFIX_HTTP)) {
            	errors.rejectValue("website", null, null, "Must start with " + PREFIX_HTTP);
            }
        }
    }


}
