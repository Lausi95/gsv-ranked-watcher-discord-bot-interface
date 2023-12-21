package de.lausi95.gsvrankedwatcherdiscordbotinterface.adapter.discord

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.string
import com.kotlindiscord.kord.extensions.commands.converters.impl.user
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond
import de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service.PlayerNotifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PlayerManagerExtension(
  val playerNotifier: PlayerNotifier,
  @Value("\${discord.guild-id}") val discordGuildId: Long
) : Extension() {

  override val name = "player-manager"

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

    publicSlashCommand(::AddPlayerArgs) {
      name = "add_player"
      description = "Adds a player to the ranked watcher"
      guild(discordGuildId)

      action {
        val summonerName = arguments.summonerName
        playerNotifier.notifyPlayerAdded(summonerName)

        respond {
          content = "Summoner '$summonerName' as requested to be added to the ranked watcher"
        }
      }
    }

    publicSlashCommand(::RemovePlayerArgs) {
      name = "remove_player"
      description = "Removes a player from the ranked watcher"
      guild(discordGuildId)

      action {
        val summonerName = arguments.summonerName
        playerNotifier.notifyPlayerRemoved(summonerName)

        respond {
          content = "Summoner '$summonerName' as requested to be removed from the ranked watcher"
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

  inner class AddPlayerArgs: Arguments() {
    val summonerName by string {
      name = "summoner_name"
      description = "Name of the summoner to track"
    }
  }

  inner class RemovePlayerArgs: Arguments() {
    val summonerName by string {
      name = "summoner_name"
      description = "Name of the summoner to track"
    }
  }
}
