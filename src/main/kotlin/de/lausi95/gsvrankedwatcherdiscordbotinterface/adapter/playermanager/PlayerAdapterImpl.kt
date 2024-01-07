package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.playermanager

import de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service.PlayerAdapter
import org.springframework.web.client.RestTemplate

data class AddPlayerRequest(
  val summonerName: String
)

internal class PlayerAdapterImpl(private val restTemplate: RestTemplate): PlayerAdapter {

  override fun addPlayer(summonerName: String) {
    val request = AddPlayerRequest(summonerName)
    restTemplate.postForEntity("/players", request, Any::class.java)
  }

  override fun removePlayer(summonerName: String) {
    restTemplate.delete("/players/$summonerName")
  }
}
