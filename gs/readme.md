### Guided Statute Search
A web based application that allows for browsing and searching the California Statutory Legal Codes.
  * Apache Lucene as the search engine. 
  * Java Servlet's with Apache Tomcat Servlet engine.
  * Twitter Bootstrap for Mobile First CSS and HTML presentation.
  * Heirarchy implemented using Facets instead of a database. 

Statutory legal codes are document hierarchy. For example Evidence Code § 800 (Expert Witnesses) is under 
Division 7, Chapter 1, Article 1 of the Evidence Code. 

This search application differs somewhat from other search applications. Instead of being presented only with a
list of search results, you are presented with the number of results found. You can use the counts to 
guide you to the most likely place to find your document in the hierarchy. You can also get a list of search results. 
Using a combination of following the "counts" and showing the list of results at each level gives the best results. 


Having the counts shown at each level of a hierarchy allows for more intuitive navigation of the search results. 
Following the number of search results leads a user to areas in the documents where more terms are found, and by navigating 
the hierarchy a user becomes more aware of where in a set of documentation things can be found and how the documentation is 
organized. This concept extends very well to any set of documentation.
