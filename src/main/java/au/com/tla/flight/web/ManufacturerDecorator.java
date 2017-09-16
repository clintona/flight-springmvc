package au.com.tla.flight.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import au.com.tla.flight.model.Manufacturer;

public class ManufacturerDecorator extends TableDecorator {
	
	public String getLink() {
        Manufacturer row = (Manufacturer)getCurrentRowObject();
        return buildLink(getContextPath() + "/brands/", row);
    }

	private String getContextPath() {
		PageContext ctx = getPageContext();
		HttpServletRequest req = (HttpServletRequest)ctx.getRequest();
		return req.getContextPath();
	}
	
	private String buildLink(String contextPath, Manufacturer row) {
		StringBuilder buf = new StringBuilder("<a href=\"");
		buf.append(contextPath).append(row.getName());
		buf.append("\">").append(row.getName());
		buf.append("</a>"); // "\</a\>";
		return buf.toString();
	}
}
