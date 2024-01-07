package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.playermanager

import de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service.PlayerAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
private class PlayerManagerConfiguration {

  @Bean
  fun playerAdapter(@Value("\${player-manager.host}") playerManagerHost: String): PlayerAdapter {
    val restTemplate = RestTemplateBuilder().rootUri(playerManagerHost).build()
    return PlayerAdapterImpl(restTemplate)
  }
}
