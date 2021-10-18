package com.seyritey.psp.infrastructure

import io.micronaut.runtime.Micronaut

object Application {
	@JvmStatic
	fun main(args: Array<String>) {
		Micronaut.build()
			.packages("com.seyritey.psp")
			.mainClass(Application.javaClass)
			.start()
	}
}