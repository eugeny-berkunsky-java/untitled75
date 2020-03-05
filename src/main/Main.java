package main;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
	    new Main().run();
    }

    private void run() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/univer", "student", "123");
            // CRUD
            //addStudent(connection, "Mykola", 74.0); // 1
            //showStudents(connection);               // 2
            //updateRating(connection, 3, 99.9);      // 3
            deleteStudent(connection, 4);          // 4
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent(Connection connection, int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from student where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    private void updateRating(Connection connection, int id, double newRating) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update student set rating = ? where id = ?");
        ps.setDouble(1, newRating);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    private void addStudent(Connection connection, String name, double rating) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into student (name, rating) values (?,?)");
        ps.setString(1, name);
        ps.setDouble(2, rating);
        ps.executeUpdate();
    }

    private void showStudents(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from student")) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double rating = resultSet.getDouble("rating");
                System.out.println(id + " " + name + " " + rating);
            }
        }
    }
}
