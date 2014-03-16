package scaldi.akka.example

import akka.actor.{PoisonPill, ActorRef, Actor}
import scaldi.akka.AkkaInjectable
import scaldi.Injector
import java.util.UUID
import scala.math.BigDecimal.RoundingMode

class OrderProcessor(implicit inj: Injector) extends Actor with AkkaInjectable {
  import Messages._

  val priceCalculator = injectActorRef [PriceCalculator]

  def receive = idle

  val idle: Receive = {
    case orderInfo @ ProcessOrder(user: User, itemId: Long, netAmount: Int) =>
      println(s"Processing order for user $user.")

      priceCalculator ! CalculatePrice(netAmount)

      context become workingHard(orderInfo, sender)
  }

  def workingHard(orderInfo: ProcessOrder, reportTo: ActorRef): Receive = {
    case CancelProcessing =>
      reportTo ! OrderProcessingFailed("Cancelled")
      self ! PoisonPill
    case GrossPriceCalculated(_, grossPrice) =>
      println("Processing order.....")

      reportTo ! OrderProcessed(UUID.randomUUID().toString, grossPrice)
      self ! PoisonPill
  }
}

class PriceCalculator extends Actor {
  import Messages._

  def receive = {
    case CalculatePrice(netAmount) =>
      val grossCent = (netAmount * BigDecimal("1.19")).setScale(0, RoundingMode.HALF_UP).toIntExact
      sender ! GrossPriceCalculated(netAmount, grossCent)
  }
}