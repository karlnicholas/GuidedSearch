package gs.util;

import gsearch.viewmodel.ViewModel;

import java.net.*;

public class URLBuilder {
	/*
		@RequestParam(required=false) String ct, 
		@RequestParam(required=false) String cp, 
		@RequestParam(required=false) String nt, 
		@RequestParam(required=false) String np, 
		@RequestParam(required=false) boolean hs, 
		@RequestParam(required=false) String to, 
		@RequestParam(required=false) String ho, 
	 */
	public String newPathUrl(ViewModel viewModel, String newPath) throws Exception {
		return "search" + UrlArgs( newPath, viewModel.getTerm(), viewModel.isFragments() );
	}
	
	public String UrlArgs(String path, String term, boolean frag) throws Exception {
		StringBuilder sb = new StringBuilder();
		char firstArg = '?';
		if ( path != null ) {
			sb.append(firstArg + "path="+URLEncoder.encode(path, "UTF-8" ));
			firstArg = '&';
		}
		if ( term != null ) {
			sb.append(firstArg + "term="+URLEncoder.encode(term, "UTF-8" ));
			firstArg = '&';
		}
		if ( frag != false ) {
			sb.append(firstArg + "frag=true");
			firstArg = '&';
		}
		return sb.toString();
		
	}

	public String homeUrl(ViewModel viewModel) throws Exception {
		StringBuilder sb = new StringBuilder("search");
		char firstArg = '?';
		if (! viewModel.getTerm().isEmpty() ) {
			sb.append(firstArg + "term=" + URLEncoder.encode(viewModel.getTerm(), "UTF-8"));
			firstArg = '&';
		}
		if ( viewModel.isFragments() ) {
			sb.append( firstArg + "frag=true" );
			firstArg = '&';
		}
		return sb.toString();
	}
}
