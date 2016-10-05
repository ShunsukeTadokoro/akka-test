package com.example

import akka.actor.ActorSystem
import akka.actor.Props

object ApplicationMain extends App {


  val system = ActorSystem("SamplePersistentActorSystem")
  val persistentActor = system.actorOf(Props[SamplePersistentActor], "my-example")

  persistentActor ! AppendEvent("hoge")
  persistentActor ! AppendEvent("fuga")
  persistentActor ! Print
  persistentActor ! AppendEvent("baa")
  persistentActor ! AppendEvent("piyo")
  persistentActor ! SnapShot

  Thread.sleep(1000)
  system.whenTerminated
  system.terminate()
}