package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.discord

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.extensions.Extension
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class DiscordConfiguration(
  val discordBotExtensions: List<Extension>,
  @Value("\${discord.bot-token}") val discordBotToken: String
) {

  @EventListener(ApplicationStartedEvent::class)
  fun someDiscordPlayground() {
    runBlocking {
      val bot = ExtensibleBot(discordBotToken) {
        chatCommands {
          enabled = true
        }
        extensions {
          discordBotExtensions.forEach { add { it }}
        }
      }
      bot.start()
    }
  }
}
