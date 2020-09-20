package de.florianmarsch.spring.fussballmanager

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FussballmanagerApplication{

	var logger: Logger = LoggerFactory.getLogger(FussballmanagerApplication::class.java)

	init {





		println("    ______                __          ____   __  ___                                     ____            __ \n" +
				"   / ____/_  ____________/ /_  ____ _/ / /  /  |/  /___ _____  ____ _____ ____  _____   / __ \\___  _____/ /_\n" +
				"  / /_  / / / / ___/ ___/ __ \\/ __ `/ / /  / /|_/ / __ `/ __ \\/ __ `/ __ `/ _ \\/ ___/  / /_/ / _ \\/ ___/ __/\n" +
				" / __/ / /_/ (__  |__  ) /_/ / /_/ / / /  / /  / / /_/ / / / / /_/ / /_/ /  __/ /     / _, _/  __(__  ) /_  \n" +
				"/_/    \\__,_/____/____/_.___/\\__,_/_/_/  /_/  /_/\\__,_/_/ /_/\\__,_/\\__, /\\___/_/     /_/ |_|\\___/____/\\__/  \n" +
				"                                                                  /____/                                    ")

	}
}

fun main(args: Array<String>) {
	runApplication<FussballmanagerApplication>(*args)
}
