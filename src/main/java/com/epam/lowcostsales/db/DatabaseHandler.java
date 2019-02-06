package com.epam.lowcostsales.db;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            DatabaseHandler.executeDBScripts("src/main/resources/DB/create_database_tables.sql", statement);

            statement.close();
        } catch (SQLException e) {
            //TO DO write log
            throw new RuntimeException(e);
        }
    }

    public void fillTestDataInDB() {

        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            DatabaseHandler.executeDBScripts("src/main/resources/DB/fill_in_test_data_in_database.sql", statement);

            statement.close();
        } catch (SQLException e) {
            //TO DO write log
            throw new RuntimeException(e);
        }
    }

    private static void executeDBScripts(String SQLScriptFilePath, Statement statement) {
        try (BufferedReader in = new BufferedReader(new FileReader(SQLScriptFilePath))) {
            String str;
            while ((str = in.readLine()) != null) {
                statement.addBatch(str);
            }
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            //TO DO write log
            throw new RuntimeException(e);
        }
    }

    public void dropAllTables() {
        try (Connection dbConnection = dataSource.getConnection()) {

            Statement statement = dbConnection.createStatement();
            DatabaseHandler.executeDBScripts("src/main/resources/DB/drop_all_tables.sql", statement);

            statement.close();
        } catch (SQLException e) {
            //TO DO write log
            throw new RuntimeException(e);
        }
    }
}
