package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.discord

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.user
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
private class SummonerSummonerExtension(@Value("\${discord.guild-id}") val discordGuildId: Long): Extension() {

  override val name = "summoner-summoner"

  override suspend fun setup() {
    publicSlashCommand(::SummonArgs) {
      name = "summon"
      description = "summons someone"
      guild(discordGuildId)

      action {
        val target = arguments.target
        respond {
          content = "༼ つ ◕_◕ ༽つ ${target.mention}"
        }
      }
    }
  }

  inner class SummonArgs: Arguments() {
    val target by user {
      name = "target"
      description = "Person you want to summon"
    }
  }
}
