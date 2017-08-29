package unittest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import webservice.HttpRequest;

public class MaxOTPApiTest {

	@Rule
    public ErrorCollector collector = new ErrorCollector();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = JUnitCore.runClasses(MaxOTPApiTest.class);
		System.out.println(result.getFailures().size());
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	}
	
	@Test
	public void testLowerBoundaryCase() throws Exception
	{
		HttpRequest request=new HttpRequest();
		request.setCONTENT_TYPE("application/json;charset=UTF-8");
		String url="api/quotes";
	    String baseUrl="http://cardev.policybazaar.com:8080/";
	    String requstBody="{\"age\":54,\"gender\":1,\"tobacco\":0,\"policyTerm\":21,\"totalCover\":5000000,\"cityId\":551,\"spouseTobacco\":0,\"spouseAge\":null,\"childAge\":-1,\"isSpouseWorking\":0,\"monthlyIncome\":0,\"dob\":\"14-07-1963\",\"monthlyIncomeYears\":20,\"interestRate\":10,\"refId\":\"OTMzMDQz\",\"stateId\":35,\"payOptionType\":\"LimitedPay\",\"ppt\":10,\"utmSource\":null,\"name\":\"sdfsfyh\",\"mobileNo\":\"9779877777\"}";
	    String response=request.sendPost(baseUrl+url, requstBody);
		 
		 JSONParser parser=new JSONParser();
		 JSONArray jsonArray=null;
		// obj=(JSONObject)parser.parse(str);
		 jsonArray=(JSONArray) parser.parse(response);
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 JSONObject obj=(JSONObject)jsonArray.get(i);
			 int planId= Integer.parseInt(obj.get("Plan_Id").toString());
			 if(planId == 776)
			 {
				 
			 }
		 }
		 
		 System.out.println(response);
		 
		 
		 
	}

	public void testQuotesAndProductApi() throws Exception
	{
		HttpRequest request=new HttpRequest();
		request.setCONTENT_TYPE("application/json;charset=UTF-8");
		String url="api/quotes";
	    String baseUrl="http://cardev.policybazaar.com:8080/";
	    String requstBody="{\"age\":54,\"gender\":1,\"tobacco\":0,\"policyTerm\":21,\"totalCover\":5000000,\"cityId\":551,\"spouseTobacco\":0,\"spouseAge\":null,\"childAge\":-1,\"isSpouseWorking\":0,\"monthlyIncome\":0,\"dob\":\"14-07-1963\",\"monthlyIncomeYears\":20,\"interestRate\":10,\"refId\":\"OTMzMDQz\",\"stateId\":35,\"payOptionType\":\"LimitedPay\",\"ppt\":10,\"utmSource\":null,\"name\":\"sdfsfyh\",\"mobileNo\":\"9779877777\"}";
	    String response=request.sendPost(baseUrl+url, requstBody);
		 
		 JSONParser parser=new JSONParser();
		 JSONArray jsonArray=null;
		// obj=(JSONObject)parser.parse(str);
		 jsonArray=(JSONArray) parser.parse(response);
		 for(int i=0;i<jsonArray.size();i++)
		 {
			 JSONObject obj=(JSONObject)jsonArray.get(i);
			 int planId= Integer.parseInt(obj.get("Plan_Id").toString());
			 if(planId == 776)
			 {
				 
			 }
		 }
		 
		 System.out.println(response);
		 
		 
		 
	}

	
	
}
