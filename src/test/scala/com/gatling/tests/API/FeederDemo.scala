package com.gatling.tests.API;

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class FeederDemo extends Simulation {

  //protocol//
  private[API] val httpProtocol: Nothing = http.baseUrl("https://computer-database.gatling.io/")

  //scenario//
  val feeder = csv("data/data1.csv").circular

  val scn = scenario("Feeders Demo ")
    .repeat(3) {
      .feed(feeder)
        .exec { session =>
          println("Name: " + session("name").as[String])
          println("Job: " + session("job").as[String])
          session
        }
        .pause(1)
    }

  //setup//
  setUp(scn.inject(atOnceUsers(1)))

}
