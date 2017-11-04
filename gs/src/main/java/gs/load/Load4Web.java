package gs.load;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import code.CACodesFactory;
import codesparser.CodesInterface;
import codesparser.LoadInterface;

/**
 * Servlet implementation class Load4Web
 */
public class Load4Web { 
	
	public static void main(String... args) throws Exception {

		ResourceBundle rb = ResourceBundle.getBundle("interfaces");
		String iface = rb.getString("loadinterface");

		// For gscalifornia
		File codesDir = new File("c:/users/karln/code");

		// For gsvirginia
//		File codesDir = new File("c:/users/karl/vacode.json.zip");

		LoadInterface loader = (LoadInterface) Class.forName(iface).newInstance();
		
//		LoadInterface cloader = new CALoad();
		
//		File xmlcodes = new File("c:/users/karl/op/GuidedSearch/gs/src/main/resources/xmlcodes");
		File index = new File("c:/users/karln/op/GuidedSearch/gs/src/main/resources/index/");
		File indextaxo = new File("c:/users/karln/op/GuidedSearch/gs/src/main/resources/indextaxo/");
		
//		loader.createXMLCodes(codesDir, xmlcodes );
		loader.loadCode(codesDir, index, indextaxo);
	}
    
}
