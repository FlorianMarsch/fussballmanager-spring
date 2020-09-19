package de.florianmarsch.spring.fussballmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FussballmanagerApplication{
	init {
	    println("Hello")
	}
}

fun main(args: Array<String>) {
	runApplication<FussballmanagerApplication>(*args)
}
