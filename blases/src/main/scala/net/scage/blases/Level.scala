package net.scage.blases

import net.scage.support.Vec
import net.scage.blases.Blases._
import net.scage.ScageLib._
import net.scage.blases.Relatives._

trait Level {
  def load()

  def startCoord: Vec
  def finishCoord: Vec

  val myrect = displayList {
    drawRectCentered(Vec.zero, 40, 40, RED)
  }

  def drawStartFinish() {
    drawCircle(startCoord, rInt(20), RED)
    openglLocalTransform {
      openglMove(startCoord)
      openglScale(windowWidth/1024f)
      drawDisplayList(myrect)
    }
    print("Start", (startCoord - rVec(20, 40)), RED)

    drawCircle(finishCoord, rInt(30), GREEN)
    print("Finish", (finishCoord - rVec(25, 50)), GREEN)
  }

  def isWin: Boolean = {
    val winner_blases = tracer.tracesNearCoord(finishCoord, -1 to 1, condition = {
      blase => blase.location.dist(finishCoord) < 20
    })
    !winner_blases.isEmpty
  }
}
