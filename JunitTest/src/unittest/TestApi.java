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
		 //String url="http://cardev.policybazaar.com:8080/api/productInfo/849/?enquiryId=OTI0NTUy&tabName=allPlans&isMulti=false&incomeTerm=20&increasingIncomePercentage=0&monthlyIncome=&oneTimePayout=&v=1500356277653";
		 //String url="http://cardev.policybazaar.com:8080/api/productInfo/854/?enquiryId=OTI1MDI2&tabName=allPlans&isMulti=false&isAlreadyExist=false&incomeTerm=20&increasingIncomePercentage=0&monthlyIncome=&oneTimePayout=&variantPlanId=0&v=1500381932220";
		 String url="http://cardev.policybazaar.com:8080/api/productInfo/852/?enquiryId=OTI1MDE5&tabName=onetimePlans&isMulti=false&incomeTerm=20&increasingIncomePercentage=0&monthlyIncome=&oneTimePayout=&variantPlanId=0&v=1500384062398";
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
			 double finalPremium=Double.parseDouble((planDetail.get("finalPremium")).toString());
			 double annualPremiumWGST=Double.parseDouble((planDetail.get("annualPremiumWGST")).toString());
			 double annualPremiumGST=Double.parseDouble((planDetail.get("annualPremiumGST")).toString());
			
			 collector.checkThat("annualPremium plan Id: "+planDetail.get("planId")+ " basePlanId:"+planDetail.get("basePlanId") ,finalPremium, CoreMatchers.equalTo((annualPremiumGST+annualPremiumWGST)));
			 
			 double halfYearlyTotalPremium=Double.parseDouble((planDetail.get("halfYearlyTotalPremium")).toString());
			 double halfYearlyPremiumWGST=Double.parseDouble((planDetail.get("halfYearlyPremiumWGST")).toString());
			 double halfYearlyPremiumGST=Double.parseDouble((planDetail.get("halfYearlyPremiumGST")).toString());
			
			 collector.checkThat("halfYearlyPremium plan Id: "+planDetail.get("planId")+ " basePlanId:"+planDetail.get("basePlanId") ,halfYearlyTotalPremium, CoreMatchers.equalTo((halfYearlyPremiumWGST+halfYearlyPremiumGST)));
			 
			 double monthlyTotalPremium=Double.parseDouble((planDetail.get("monthlyTotalPremium")).toString());
			 double monthlyPremiumWGST=Double.parseDouble((planDetail.get("monthlyPremiumWGST")).toString());
			 double monthlyPremiumGST=Double.parseDouble((planDetail.get("monthlyPremiumGST")).toString());
			
			 collector.checkThat("monthlyPremium plan Id: "+planDetail.get("planId")+ " basePlanId:"+planDetail.get("basePlanId") ,monthlyTotalPremium, CoreMatchers.equalTo((monthlyPremiumGST+monthlyPremiumWGST)));
			 
			 double quarterlyTotalPremium=Double.parseDouble((planDetail.get("quarterlyTotalPremium")).toString());
			 double quarterlyPremiumWGST=Double.parseDouble((planDetail.get("quarterlyPremiumWGST")).toString());
			 double quarterlyPremiumGST=Double.parseDouble((planDetail.get("quarterlyPremiumGST")).toString());
			
			 collector.checkThat("quarterlyPremium plan Id: "+planDetail.get("planId")+ " basePlanId:"+planDetail.get("basePlanId") ,quarterlyTotalPremium, CoreMatchers.equalTo((quarterlyPremiumGST+quarterlyPremiumWGST)));
			 
			 
			 //testAdd();
			 
		 }
		 
	     // assertEquals("Junit is working fine",str);
	   }

}
