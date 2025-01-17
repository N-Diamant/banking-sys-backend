//package rw.ac.rca.spring_boot_template.exceptions;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import rw.ac.rca.spring_boot_template.dtos.responses.ErrorResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NotFoundException extends RuntimeException{
//    public NotFoundException(String message){
//        super(message);
//    }
//
//    public ResponseEntity<Response> getResponse(){
//        List<String> details = new ArrayList<>();
//        details.add(super.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
//        Response response = new Response();
//        Object ResponseType = null;
//        response.setSuspended(ResponseType.RESOURCE_NOT_FOUND);
//        response.setDateHeader(errorResponse);
//        return new ResponseEntity<Response>(response , HttpStatus.NOT_FOUND);
//    }
//}