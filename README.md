# java-ai-db-test
Java application to test codeless agent with Mongo and MySql DB

# Steps to run
1. Update connection string [applicationinsights.json](https://github.com/abhikt48/java-ai-db-test/blob/main/agent/applicationinsights.json)
2. Download 'applicationinsights-agent-3.5.1.jar' and keep in agent folder
3. Run [TestCodelessAgentWithMongoAndSql.java](https://github.com/abhikt48/java-ai-db-test/blob/main/src/main/java/com/abhi/test/ai/agent/latest/TestCodelessAgentWithMongoAndSql.java) with VM argument `-javaagent:"***\agent\applicationinsights-agent-3.5.1.jar" `
