package com.gatling.tests.API;

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class APITest1 extends Simulation {

  //protocol
  val httpProtocol = http
    .baseUrl("https://reqres.in/api/users")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/118.0")

  //scenario
  val scn = scenario("GET Api Request Demo")
    .exec(
      http("Get Single User")
        .get("/2")
        .check(
          status.is(200),
          jsonPath("$.data.first_name").is("Janet")
        )
    )
    .pause(1)

  //setup
  setUp(
    scn.inject(atOnceUsers(10))
      .protocols(httpProtocol)
  )

}
