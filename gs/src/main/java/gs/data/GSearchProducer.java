package gs.data;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.annotation.*;

import org.springframework.stereotype.Component;

import code.CACodesFactory;
import codesparser.CodesInterface;
import gsearch.GSearch;

@Component
public class GSearchProducer {

	private GSearch gSearch;
	
	public GSearch getGSearch() {
		return gSearch;
	}
	
	@PostConstruct
	public void construct() {
		try {
			File index = new File(GSearchProducer.class.getResource("/index").getFile()); 
			File indexTaxo = new File(GSearchProducer.class.getResource("/indextaxo").getFile());

			ResourceBundle rb = ResourceBundle.getBundle("interfaces");
			String iface = rb.getString("codesFactory");
			
			Class<?> cls = Class.forName(iface);
			Method method = cls.getMethod("getInstance", new Class[0]);
			CACodesFactory codesFactory = (CACodesFactory) method.invoke(null, new Object[0]);
			CodesInterface codesInterface = codesFactory.getCodesInterface(true);
			
			gSearch = new GSearch(codesInterface, index, indexTaxo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PreDestroy
	public void destroy() {
		try {
			gSearch.destroy();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
}
