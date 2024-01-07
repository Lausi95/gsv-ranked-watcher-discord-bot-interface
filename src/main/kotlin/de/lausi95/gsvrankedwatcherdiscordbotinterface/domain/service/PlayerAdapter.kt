package de.lausi95.gsvrankedwatcherdiscordbotinterface.domain.service

interface PlayerAdapter {

  fun addPlayer(summonerName: String)

  fun removePlayer(summonerName: String)
}
