package de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service

interface PlayerNotifier {

  fun notifyPlayerAdded(summonerName: String)

  fun notifyPlayerRemoved(summonerName: String)
}
