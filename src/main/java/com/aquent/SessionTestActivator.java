package com.aquent;

import org.apache.felix.http.api.ExtHttpService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.dotmarketing.filters.CMSFilter;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

public class SessionTestActivator extends GenericBundleActivator {
	
	private ServiceTracker<ExtHttpService, ExtHttpService> tracker;
	
	private SetSessionServlet s;
	private GetSessionServlet g;

	@Override
	public void start(BundleContext ctx) throws Exception {
		// Initializing services
    	initializeServices( ctx );

    	// Start the Servlets
    	registerServlets( ctx );
		
	}
	
	private void registerServlets( BundleContext ctx) {
		tracker = new ServiceTracker<ExtHttpService, ExtHttpService>(ctx, ExtHttpService.class, null) {
			@Override public ExtHttpService addingService(ServiceReference<ExtHttpService> reference) {
				ExtHttpService extHttpService = super.addingService(reference);
				
				s = new SetSessionServlet();
				g = new GetSessionServlet();
				
				try {

					extHttpService.registerServlet("/TestSessionSet", s, null, null);
					extHttpService.registerServlet("/TestSessionGet", g, null, null);
					
				} catch (Exception e) {
					throw new RuntimeException("Failed to register servlets", e);
				}
				
		    	CMSFilter.addExclude("/app/TestSessionSet");
		    	CMSFilter.addExclude("/app/TestSessionGet");
		    	
		    	Logger.info(this, "Registered servlets");
				
				return extHttpService;
			}
			@Override public void removedService(ServiceReference<ExtHttpService> reference, ExtHttpService extHttpService) {
				
				CMSFilter.removeExclude("/app/TestSessionSet");
				CMSFilter.removeExclude("/app/TestSessionGet");
				
				extHttpService.unregisterServlet(s);
				extHttpService.unregisterServlet(g);
				
				super.removedService(reference, extHttpService);
			}
		};
		tracker.open();
	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		// Stop the Servlets
    	tracker.close();
    	
    	// Unregister all the bundle services
        unregisterServices( ctx );
	}

}
