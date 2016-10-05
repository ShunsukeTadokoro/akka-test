package com.example
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import akka.persistence._

class SamplePersistentActor extends PersistentActor {

  private var state = State()

  // 永続化したイベントやスナップショットから状態を復元するためのメソッド
  override def receiveRecover: Receive = {
    // Snapshotからの復元
    case SnapshotOffer(metadata: SnapshotMetadata, snapshot: State) =>
      val snapShotDatetime = new Timestamp(metadata.timestamp).toLocalDateTime
      val createdBeforeMinute = ChronoUnit.MINUTES.between(snapShotDatetime, LocalDateTime.now())
      println(s"snapshot was created $snapShotDatetime. $createdBeforeMinute min before.")
      if (createdBeforeMinute <= 5) {
        println("Recover from Snapshot.")
        state = snapshot
      } else {
        println("Can't recover. Snapshot is too old.")
      }

    case RecoveryCompleted => println("Recovery complete!!!!!!!!!!!!!!")
  }

  // Actorのreceiveにあたるもの
  override def receiveCommand: Receive = {
    case ev: AppendEvent => state = state.updated(ev)
    case Print => println(state)
    case SnapShot =>
      println(s"Create snapshot. ${LocalDateTime.now()}")
      saveSnapshot(state)
  }

  override def persistenceId: String = "sample-id"
}
