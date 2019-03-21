package com.epam.lowcostsales.db;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

    private DataSource dataSource;

    public DatabaseHandler(DataSource dataSource) {
        this.dataSource = dataSource;
        createDB();
        fillTestDataInDB();
    }

    public void createDB() {
        try (Connection dbConnection = dataSource.getConnection();
        		Statement statement = dbConnection.createStatement()) {
            
			executeDBScripts("/DB/create_database_tables.sql", statement);

        } catch (SQLException e) {
            //TODO write log
            throw new RuntimeException(e);
        }
    }

    public void fillTestDataInDB() {
        try (Connection dbConnection = dataSource.getConnection();
        		Statement statement = dbConnection.createStatement()) {

        	executeDBScripts("/DB/fill_in_test_data_in_database.sql", statement);

        } catch (SQLException e) {
            //TODO write log
            throw new RuntimeException(e);
        }
    }
    
    private void executeDBScripts(String SQLScriptFilePath, Statement statement) {
        try (InputStream resourceStream = getClass().getResourceAsStream(SQLScriptFilePath);
        		BufferedReader in = new BufferedReader(new InputStreamReader(resourceStream))) {
            String str;
            while ((str = in.readLine()) != null) {
                statement.addBatch(str);
            }
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            //TODO write log
            throw new RuntimeException(e);
        }
    }

    public void dropAllTables() {
        try (Connection dbConnection = dataSource.getConnection();
        		Statement statement = dbConnection.createStatement()) {

            executeDBScripts("/DB/drop_all_tables.sql", statement);

        } catch (SQLException e) {
            //TODO write log
            throw new RuntimeException(e);
        }
    }
}
