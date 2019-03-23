package hello;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class Controller {
	private AtomicLong count = new AtomicLong();
	private String message = "Hey, %s";
	private String Method = "%s method is called";
	
	 @PutMapping(path="/",consumes="application/json")
	 public String defaultRequest(@RequestBody String ReqBody){ 
		 JSONObject json = new JSONObject(ReqBody);
		 JSONObject json2 = json.getJSONObject("content");
	    return "This is a Request body returned by jsonObject that u sent"+json+" and this Request sending is somewhat "+json2.get("status");
	  }
	
	 @PatchMapping(path="/",consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	 public String mapperRequest(@RequestBody String ReqBody) throws JsonParseException, JsonMappingException, IOException{
		 ObjectMapper mapper;
		if(ReqBody.contains("</")) {
			mapper  = new XmlMapper();
		}else {
			 mapper = new ObjectMapper();
		}
		//mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ChecMultipleMethods model = mapper.readValue(ReqBody, ChecMultipleMethods.class);
		System.out.println(mapper.writeValueAsString(model));
	    return "This is a Request body that u sent"+model+" and this Request sending is somewhat "+model.content.status;
	  }
	 
	@RequestMapping(method=RequestMethod.GET,value="/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(count.incrementAndGet(),
                            String.format(message, name));
	}	
	
	@RequestMapping(method=RequestMethod.POST,path="/greeting/{user}",consumes="application/*",produces=MediaType.APPLICATION_XML_VALUE,headers="Content-Type=application/json",params="method=PUT")
	@ResponseBody
    public String postGreeting(@PathVariable("user") String name,@RequestParam("method") String method) {		
		Object Json = new Greeting(count.incrementAndGet(),
                String.format(message, name),String.format(Method, method));
        JSONObject json = new JSONObject(Json); 
        String xmlRes = XML.toString(json);
		return xmlRes;
	}
	
}