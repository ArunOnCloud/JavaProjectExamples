package unittest;

import static org.junit.Assert.assertEquals;

import org.hamcrest.CoreMatchers;
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

public class TestApi {

	@Rule
    public ErrorCollector collector = new ErrorCollector();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Result result = JUnitCore.runClasses(TestApi.class);
		System.out.println(result.getFailures().size());
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
	

	 @Test
	 public void apiTesting() throws Exception {
		 HttpRequest http = new HttpRequest();
		 String url="http://cardev.policybazaar.com:8080/api/productInfo/849/?enquiryId=OTI0NTUy&tabName=allPlans&isMulti=false&incomeTerm=20&increasingIncomePercentage=0&monthlyIncome=&oneTimePayout=&v=1500356277653";
		 String str=http.sendGet(url,"");
		 JSONObject obj=new JSONObject();
		 JSONParser parser=new JSONParser();
		 obj=(JSONObject)parser.parse(str);
		 JSONArray arr=(JSONArray) obj.get("supplierPlans");
		 //System.out.println("size:"+arr.size());
		 
		 for(int i=0;i<arr.size();i++)
		 {
			 JSONObject supplierPlan=(JSONObject) arr.get(i);
			 JSONArray planDetails=(JSONArray)supplierPlan.get("planDetails");
			 JSONObject planDetail=(JSONObject) planDetails.get(0);
			 int finalPremium=Integer.parseInt((planDetail.get("finalPremium")).toString());
			 int annualPremiumWGST=Integer.parseInt((planDetail.get("annualPremiumWGST")).toString());
			 int annualPremiumGST=Integer.parseInt((planDetail.get("annualPremiumGST")).toString());
			// System.out.println("finalPremium:"+finalPremium);
			// System.out.println("annualPremiumWGST:"+annualPremiumWGST);
			// System.out.println("annualPremiumGST:"+annualPremiumGST);
			 //assertEquals(finalPremium,(annualPremiumGST+annualPremiumWGST));
			 collector.checkThat("plan Id: "+planDetail.get("planId")+ " basePlanId:"+planDetail.get("basePlanId") ,finalPremium, CoreMatchers.equalTo((annualPremiumGST+annualPremiumWGST)));
			 //testAdd();
			 
		 }
		 
	     // assertEquals("Junit is working fine",str);
	   }

}
