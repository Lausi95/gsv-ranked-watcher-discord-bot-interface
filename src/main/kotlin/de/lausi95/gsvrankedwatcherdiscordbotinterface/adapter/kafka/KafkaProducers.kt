package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.kafka

import de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service.PlayerNotifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

internal data class PlayerAddedMessage(val summonerName: String)

internal data class PlayerDeletedMessage(val summonerName: String)

@Component
class KafkaProducers(val kafkaTemplate: KafkaTemplate<String, Any>) : PlayerNotifier {

  override fun notifyPlayerAdded(summonerName: String) {
    val message = PlayerAddedMessage(summonerName)
    kafkaTemplate.send("player_added", message)
  }

  override fun notifyPlayerRemoved(summonerName: String) {
    val message = PlayerDeletedMessage(summonerName)
    kafkaTemplate.send("player_deleted", message)
  }
}
