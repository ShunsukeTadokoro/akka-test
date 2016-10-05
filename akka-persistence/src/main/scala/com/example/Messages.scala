package com.example

sealed trait Message
case object SnapShot extends Message
case object Print extends Message
case class AppendEvent(data: String) extends Message

case class State(events: Seq[String] = Seq.empty) {
  def updated(ev: AppendEvent) = copy(ev.data +: events)
  def state = toString
  override def toString = events.reverse.mkString(" :: ")
}