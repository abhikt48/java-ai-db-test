package com.abhi.test.ai.agent.latest;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class TestCodelessAgentWithMongoAndSql {

	private static final String MONGO_CONNECTION_STRING = "mongodb://localhost:27017/?authSource=DB_NAME";
	private static final String MONGO_DB_NAME = "admin";
	private static final String MONGO_COLLECTION_NAME = "test-ai";

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aiagent";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "root";

	private static final Logger logger = LoggerFactory.getLogger(TestCodelessAgentWithMongoAndSql.class);

	public static void main(String[] args) throws Exception {

		TestCodelessAgentWithMongoAndSql test = new TestCodelessAgentWithMongoAndSql();

		test.countMongoDbDocuments();
		test.getDocumentFromSql();
	}

	public void countMongoDbDocuments() {
		logger.info("Start counting documents");

		MongoClient mongoClient = MongoClients.create(MONGO_CONNECTION_STRING);
		MongoCollection<Document> collection = mongoClient.getDatabase(MONGO_DB_NAME)
				.getCollection(MONGO_COLLECTION_NAME);

		long countDocuments = collection.countDocuments();

		logger.info("Finish counting documents, total documents '{}'", countDocuments);
	}

	public void getDocumentFromSql() throws Exception {
		{
			logger.info("Start getting documents from MySql");
			// JDBC variables for managing the connection and executing SQL queries
			java.sql.Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;

			try {
				// Register JDBC driver
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Open a connection
				connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

				// Create a statement object
				statement = connection.createStatement();

				// Define the SQL query to select data from the table
				String sql = "Select * from aiagent.orders";

				// Execute the SQL query and get the result set
				resultSet = statement.executeQuery(sql);

				logger.info("MySql - ResultSize: " + resultSet.getFetchSize());
				// Process the result set
				while (resultSet.next()) {
					// Retrieve data from each row
					String firstName = resultSet.getString("FirstName");
					String lastName = resultSet.getString("LastName");

					logger.info("MySql Datat - FirstName: " + firstName + ", Name: " + lastName);
					
					break;
				}

			} catch (ClassNotFoundException e) {
				logger.error("MySQL JDBC Driver not found!", e);
			} catch (SQLException e) {
				logger.error("Connection Failed! Check output console", e);
			} finally {
				// Close the connection, statement, and result set in the finally block to
				// ensure they're always closed
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
