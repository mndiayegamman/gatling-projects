package com.gatling.tests

//import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
//import io.gatling.jdbc.Predef._

class JpetstoreSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://ns3035755.ip-37-187-143.eu")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:120.0) Gecko/20100101 Firefox/120.0")

	val scn = scenario("JpetstoreSimulation")
		.exec(http("Homepage")
			.get("/jpetstore"))
		.pause(1)
		.exec(http("SignUp")
			.get("/jpetstore/actions/Account.action;jsessionid=602D8FA6DCD556ECB0EE1075B69A848D?signonForm="))
		.pause(2)
		.exec(http("NewAccountForm")
			.get("/jpetstore/actions/Account.action?newAccountForm="))
		.pause(43)
		.exec(http("FillForm")
			.post("/jpetstore/actions/Account.action")
			.formParam("username", "titi")
			.formParam("password", "titi")
			.formParam("repeatedPassword", "titi")
			.formParam("account.firstName", "titi")
			.formParam("account.lastName", "titi")
			.formParam("account.email", "titi@toto.fr")
			.formParam("account.phone", "0478698574")
			.formParam("account.address1", "4 rue jean jacques rousseau")
			.formParam("account.address2", "")
			.formParam("account.city", "94200")
			.formParam("account.state", "ivry sur seine")
			.formParam("account.zip", "94200")
			.formParam("account.country", "france")
			.formParam("account.languagePreference", "english")
			.formParam("account.favouriteCategoryId", "DOGS")
			.formParam("newAccount", "Save Account Information")
			.formParam("_sourcePage", "5Ddoi-sUfVFWZtCNJzPAigYn1QIi4TSpKhea4iILyJMR2_tuGFW3xoJ6j-AR7b-zwXdTTuskvdUsO3lx6QVC-4-nWMZMgXYiPEuuhzFQVCI=")
			.formParam("__fp", "00bnwIDhd4XjrCuq5_c7WRsZ6kLyfoXiTaEcKI-WotD9X_bfPjoCgKvEFWyQKx1aH4zyUFopWLEGRfjO13jR1SK-WElZmxk_ZgteKbfxJHBXqDfcHLV0KiYXsdFe-OBnfDkLLZlGYti5brRlzmDpKJ4bFQWv1fI-AajEdVJzvxfRoz0SZ5o1zoBCpCFlwhMi"))
		.pause(1)
		.exec(http("ValidateForm")
			.get("/jpetstore/actions/Account.action?signonForm="))
		.pause(7)
		.exec(http("LogIn")
			.post("/jpetstore/actions/Account.action")
			.formParam("username", "titi")
			.formParam("password", "titi")
			.formParam("signon", "Login")
			.formParam("_sourcePage", "JC-RkmtvhkM-SVyl3WnqA00TePMYp4u1aTWiYPGyK3iIcC5UIXJU8WoCSsXkqfCGdgMnCf0Cf-TD-NVMjFdGzoCfjRc2X8oqqRaxrb5FXx8=")
			.formParam("__fp", "QjO-8qfJBJjkiEDzJgLCsXQbTahtt266i20tIsI69HBehv-sZkY06EUfFm-pCuuE"))
		.pause(1)
		.exec(http("SignOff")
			.get("/jpetstore/actions/Account.action?signoff=")
			.resources(http("request_7")
			.get("http://www.msftconnecttest.com/connecttest.txt"),
				http("request_8")
			.get("http://www.msftconnecttest.com/connecttest.txt")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}