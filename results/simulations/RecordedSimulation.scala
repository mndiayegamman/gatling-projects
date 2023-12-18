package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://ns3035755.ip-37-187-143.eu")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0")

	val uri1 = "https://opensource-demo.orangehrmlive.com/web/index.php"
	val uri3 = "https://contile.services.mozilla.com/v1/tiles"

	val scn = scenario("RecordedSimulation")
		.exec(http("LoginPage")
			.get(uri1 + "/auth/login")
			.resources(http("request_1")
			.get(uri1 + "/core/i18n/messages")))
		.pause(17)
		.exec(http("HomePage")
			.get("/jpetstore/actions/Catalog.action"))
		.pause(10)
		.exec(http("request_3")
			.get(uri3))
		.pause(128)
		.exec(http("request_4")
			.get("/jpetstore/actions/Catalog.action"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}