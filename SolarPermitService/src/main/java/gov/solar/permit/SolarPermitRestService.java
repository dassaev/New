package gov.solar.permit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class SolarPermitRestService {
	private static Map<String, Application> map = new HashMap<String, Application>();

	@GET
	@Path("/electric/{applicationId}")
	@Produces("application/json")
	public ServiceResponse getElectricApprovalStatus(@PathParam("applicationId") String applicationId) {
		ServiceResponse response = new ServiceResponse();
		
		if(!map.containsKey(applicationId)) {
			System.out.println("+++++++++++ Web Service: record not found:" + applicationId);
			return response;			
		}
		
		Application application = map.get(applicationId);
		
		if(application.getMockedElectricTry() == 0) {
			application.setElectricPermitStatus(application.getMockedElectricPermitResult());
		} else {
			application.setMockedElectricTry(application.getMockedElectricTry() - 1);
		}
		
		response.setApplicationId(application.getApplicationId());
		response.setElectricPermitStatus(application.getElectricPermitStatus());
		response.setStructuralPermitStatus(application.getStructuralPermitStatus());
		
		System.out.println("+++++++++++ Web Service: processed application electric permit approval status check:" + applicationId + ":" + application.getElectricPermitStatus());

		return response;

	}

	@GET
	@Path("/structural/{applicationId}")
	@Produces("application/json")
	public ServiceResponse getStructuralApprovalStatus(@PathParam("applicationId") String applicationId) {
		ServiceResponse response = new ServiceResponse();
		
		if(!map.containsKey(applicationId)) {
			System.out.println("+++++++++++ Web Service: record not found:" + applicationId);
			return response;			
		}
		
		Application application = map.get(applicationId);
		
		if(application.getMockedStructuralTry() == 0) {
			application.setStructuralPermitStatus(application.getMockedStructuralPermitResult());
		} else {
			application.setMockedStructuralTry(application.getMockedStructuralTry() - 1);
		}
		
		response.setApplicationId(application.getApplicationId());
		response.setElectricPermitStatus(application.getElectricPermitStatus());
		response.setStructuralPermitStatus(application.getStructuralPermitStatus());
		
		System.out.println("+++++++++++ Web Service: processed application structural permit approval status check:" + applicationId + ":" + application.getStructuralPermitStatus());

		return response;

	}
	
//	@GET
//	@Path("/{applicationId}")
//	@Produces("application/json")
//	public ServiceResponse getApprovalStatus(@PathParam("applicationId") String applicationId) {
//		ServiceResponse response = new ServiceResponse();
//		
//		if(!map.containsKey(applicationId)) {
//			System.out.println("+++++++++++ Web Service: record not found:" + applicationId);
//			return response;			
//		}
//		
//		Application application = map.get(applicationId);
//		
//		if(application.getMockedElectricTry() == 0) {
//			application.setElectricPermitStatus(application.getMockedElectricPermitResult());
//		} else {
//			application.setMockedElectricTry(application.getMockedElectricTry() - 1);
//		}
//		
//		if(application.getMockedStructuralTry() == 0) {
//			application.setStructuralPermitStatus(application.getMockedStructuralPermitResult());
//		} else {
//			application.setMockedStructuralTry(application.getMockedStructuralTry() - 1);
//		}
//		
//		response.setApplicationId(application.getApplicationId());
//		response.setElectricPermitStatus(application.getElectricPermitStatus());
//		response.setStructuralPermitStatus(application.getStructuralPermitStatus());
//		
//		System.out.println("+++++++++++ Web Service: getting application electric permit approval status:" + applicationId + ":" + application.getElectricPermitStatus());
//		System.out.println("+++++++++++ Web Service: getting application structural permit approval status:" + applicationId + ":" + application.getStructuralPermitStatus());
//
//		return response;
//
//	}
	
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public ServiceResponse addApplication(Application application) {
		application.setElectricPermitStatus("IN_PROGRESS");
		application.setStructuralPermitStatus("IN_PROGRESS");
		application.setMockedElectricTry(getRandom());
		application.setMockedStructuralTry(getRandom());
		map.put(application.getApplicationId(), application);
		ServiceResponse response = new ServiceResponse();
		response.setApplicationId(application.getApplicationId());
		response.setElectricPermitStatus(application.getElectricPermitStatus());
		response.setStructuralPermitStatus(application.getStructuralPermitStatus());
		System.out.println("+++++++++++ Web Service: processed new application put request:\n" + application);
		return response;

	}

	@DELETE
	@Path("/{applicationId}")
	@Produces("application/json")
	public ServiceResponse removeApplication(@PathParam("applicationId") String applicationId) {
		map.remove(applicationId);
		ServiceResponse response = new ServiceResponse();
		System.out.println("+++++++++++ Web Service: processed application delete request:" + applicationId);
		return response;

	}
	
	private static int getRandom() {
		int[] array = {1,2};
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
}
