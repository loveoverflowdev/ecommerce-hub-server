package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

object DatabaseFactory {
    private var database: Database? = null

    val databaseShared: Database
        get() = database ?: throw SQLException("Unable to establish a connection to the database")

    fun init() {
        initDB()
        transaction {
            addLogger(StdOutSqlLogger)
        }
    }

    private fun initDB() {
        val jdbcUrl = System.getenv("JDBC_URL") ?: throw IllegalStateException("Missing JDBC_URL in environment variables")
        val config = HikariConfig()
        config.jdbcUrl = jdbcUrl

        // Other HikariCP configuration options
        config.maximumPoolSize = 10
        config.connectionTimeout = 300
        config.idleTimeout = 600000
        config.driverClassName = "com.mysql.cj.jdbc.Driver"

        val dataSource = HikariDataSource(config)
        database = Database.connect(dataSource)
    }
}
