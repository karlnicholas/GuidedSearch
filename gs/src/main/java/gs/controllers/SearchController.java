package gs.controllers;

import gs.data.GSearchProducer;
import gs.util.URLBuilder;
import gsearch.viewmodel.ViewModel;

import java.io.*;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value={"/search", "/"})
public class SearchController {
	
	private static final Logger logger = Logger.getLogger(SearchController.class.getName());
	
	@Autowired
	private GSearchProducer gGearchPoducer;
	
	private URLBuilder urlBuilder = new URLBuilder();
	
    @RequestMapping(method={RequestMethod.GET})
	protected String doGet(
		@RequestParam(required=false) String path, 
		@RequestParam(required=false) String term, 
		@RequestParam(required=false) boolean frag, 
		Model model
	) {

    	ViewModel viewModel;
		try {
			viewModel = gGearchPoducer.getGSearch().handleRequest(path, term, frag);
		} catch (IOException e) {			
			throw new RuntimeException(e);
		}		
		
//		viewModel.setTerm(StringEscapeUtils.escapeHtml4( viewModel.getTerm() ));
		setAdvancedSearchFields(model, term);
    	model.addAttribute("viewModel", viewModel );
	
		// process the requests with in the MODEL
		logger.fine("1: State = " + viewModel.getState() );
		logger.fine("1: Path = " + viewModel.getPath() + ": Term = " + viewModel.getTerm() );

		return "/search";
	}
    
    @RequestMapping(method={RequestMethod.POST})
	protected String doPost(
		@RequestParam(required=false) String path, 
		@RequestParam(required=false) String term, 
		@RequestParam(required=false) boolean frag, 
		@RequestParam(required=false) String ntm, 
		@RequestParam(required=false) String cl, 
		@RequestParam(required=false) String to, 
		@RequestParam(required=false) String inAll, 
		@RequestParam(required=false) String inNot, 
		@RequestParam(required=false) String inAny, 
		@RequestParam(required=false) String inExact 
	) throws Exception {
    	
    	// navbar toggle fragments
		if ( to != null ) frag = !frag;
		
		// navbar copy of the (possibly new) term;
//		if ( !ntm.isEmpty() ) term = ntm;
//		term = ntm;

		// navbar clear term and fragments
		String bTerm = null;
		if ( 
			cl == null && (
			!inAll.isEmpty()
			|| !inNot.isEmpty()
			|| !inAny.isEmpty()
			|| !inExact.isEmpty()
			)
		) {
			StringBuilder sb = new StringBuilder();
			if ( !inAll.isEmpty() ) {
				sb.append(appendOp(inAll, '+'));
			}
			if ( !inNot.isEmpty() ) {
				sb.append(appendOp(inNot,'-'));
			}
			if ( !inAny.isEmpty() ) {
				sb.append(inAny + " ");
			}
			if ( !inExact.isEmpty() ) {
				sb.append("\"" + inExact + "\"");
			}
			bTerm = sb.toString().trim();
		}
		if ( cl != null ) {
			term = null;
			frag = false;
		} else if ( bTerm != null && ( term == null || !bTerm.equals(term) && ntm.equals(term)) ) {
			term = bTerm;
		} else if ( !ntm.equals(term) ) {
			term = ntm;
		}
				
		return "redirect:search" + urlBuilder.UrlArgs(path, term, frag);
    	
    }
    
    private String appendOp(String val, char op) {
    	val = val.trim();
    	if ( val.isEmpty()) return "";
    	String[] terms = val.trim().split(" ");
    	StringBuilder sb = new StringBuilder();
    	for ( String term: terms ) {
    		sb.append(op+term+" ");
    	}
    	return sb.toString();
    }
    
    private void setAdvancedSearchFields(Model model, String term) {
    	if ( term == null || term.isEmpty() ) return;
    	try {
	    	String[] terms = term.split(" ");
	    	String all = new String();
	    	String not = new String();
	    	String any = new String();
	    	String exact = new String();
	    	boolean ex = false;
	    	for(String t: terms) {
	    		if ( !ex && t.startsWith("+")) all=all.concat(t.substring(1) + " ");
	    		else if ( !ex && t.startsWith("-")) not=not.concat(t.substring(1) + " " );
	    		else if ( !ex && (t.startsWith("\"") && t.trim().endsWith("\"")) ) {
	    			exact=exact.concat(t.substring(1, t.length()-1) + " ");
	    		}
	    		else if ( !ex && t.startsWith("\"")) {
	    			exact=exact.concat(t.substring(1) + " ");
	    			ex = true;
	    		}
	    		else if ( ex && !t.endsWith("\"") ) {
	    			exact=exact.concat(t) + " ";
	    		}
	    		else if ( ex && t.endsWith("\"")) {
	    			exact=exact.concat(t.substring(0, t.length()-1)) + " ";
	    			ex = false;
	    		}
	    		else any = any.concat(t) + " ";
	    	}
	    	if ( !all.isEmpty() ) model.addAttribute("inAll", all.trim());
	    	if ( !not.isEmpty() ) model.addAttribute("inNot", not.trim());
	    	if ( !any.isEmpty() ) model.addAttribute("inAny", any.trim());
	    	if ( !exact.isEmpty() ) model.addAttribute("inExact", exact.trim());
    	} catch ( Throwable t) {
    		// silent exception
    		logger.warning("Exception:" + t.getMessage());
    	}
    }

}
