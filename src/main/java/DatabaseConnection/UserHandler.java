package DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;



public class UserHandler {

	DBConnection dbConnection;
	
	public List<User> showUsers() {
	    ArrayList<User> userList = new ArrayList<User>();
	    Connection connect = null;
	    PreparedStatement state = null;
	    ResultSet resSet = null;
	    dbConnection = new DBConnection();

	    try {
	        connect = dbConnection.getConnection();
	        String query = "SELECT id, first_name, last_name, phone_nr, email, address, city, " +
	                       "sex, date_of_birth, password, created_at FROM user";
	        state = connect.prepareStatement(query);
	        resSet = state.executeQuery();

	        while (resSet.next()) {
	            User user = new User(resSet.getInt("id"));

	            // Use the setParameter method to dynamically set attributes
	            user.setFirstName(resSet.getString("first_name"));
	            user.setLastName(resSet.getString("last_name"));
	            user.setPhoneNr(resSet.getString("phone_nr"));
	            user.setEmail(resSet.getString("email"));
	            user.setAddress(resSet.getString("address"));
	            user.setCity(resSet.getString("city"));
	            user.setSex(resSet.getString("sex").charAt(0));
	            user.setDateOfBirth(resSet.getDate("date_of_birth"));
	            user.setPassword(resSet.getString("password"));
	            user.setCreatedAt(resSet.getTimestamp("created_at"));

	            userList.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbConnection.closeResources(connect, state, resSet);
	    }
	    return userList;
	}
	
	public void deleteUser(User deletedUser) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();
		
		try {
			connect = dbConnection.getConnection();
			String deleteQuery = "DELETE FROM user WHERE ID=" + deletedUser.id;
			state = connect.prepareStatement(deleteQuery);
			//executeUpdate in loc de executeQuery
			int rowsAffected = state.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("User with ID " + deletedUser.id + " deleted successfully.");
	        } else {
	            System.out.println("No user found with ID " + deletedUser.id + ". Deletion failed.");
	        }
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}
	
	public void updateUser(User updatedUser) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();
		
		try {
			connect = dbConnection.getConnection();
			String updateQuery = "UPDATE user SET " +
			        "first_name=?, " +
			        "last_name=?, " +
			        "phone_nr=?, " +
			        "email=?, " +
			        "address=?, " +
			        "city=?, " +
			        "sex=?, " +
			        "date_of_birth=?, " +
			        "password=?, " +
			        "created_at=? " +
			        "WHERE id=?";
			 state = connect.prepareStatement(updateQuery);
			 
			 	state.setString(1, updatedUser.getFirstName());
	            state.setString(2, updatedUser.getLastName());
	            state.setString(3, updatedUser.getPhoneNr());
	            state.setString(4, updatedUser.getEmail());
	            state.setString(5, updatedUser.getAddress());
		        state.setString(6, updatedUser.getCity());
		        state.setString(7, String.valueOf(updatedUser.getSex()));
		        if (updatedUser.getDateOfBirth() != null) {
			        state.setDate(8, new java.sql.Date(updatedUser.getDateOfBirth().getTime()));
			    } else {
			        state.setNull(8, Types.DATE);
			    }
		        state.setString(9, updatedUser.getPassword());
		        if (updatedUser.getCreatedAt() != null) {
			        state.setTimestamp(10, new java.sql.Timestamp(updatedUser.getCreatedAt().getTime()));
			    } else {
			        state.setNull(10, Types.TIMESTAMP);
			    }

		        state.setInt(11, updatedUser.getId());
		        
		        int rowsAffected = state.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("User updated successfully.");
		        } else {
		            System.out.println("Failed to update the user. User with ID " + updatedUser.getId() + " not found.");
		        }

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}
	
	public void insertUser(User newUser) throws UserExistsException {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();
		
		if(userExists(newUser.getEmail())) {
			throw new UserExistsException("User is already in the database");
		}
		try {
			 connect = dbConnection.getConnection();


			  String insertQuery = "INSERT INTO user (first_name, last_name, phone_nr, "
		                + "email, address, city, sex, date_of_birth, password, created_at) "
		                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		        state = connect.prepareStatement(insertQuery);

		        state.setString(1, newUser.getFirstName());
		        state.setString(2, newUser.getLastName());
		        state.setString(3, newUser.getPhoneNr());
		        state.setString(4, newUser.getEmail());
		        state.setString(5, newUser.getAddress());
		        state.setString(6, newUser.getCity());
		        state.setString(7, String.valueOf(newUser.getSex()));
		        if (newUser.getDateOfBirth() != null) {
		            state.setDate(8, new java.sql.Date(newUser.getDateOfBirth().getTime()));
		        } else {
		            state.setNull(8, Types.DATE);
		        }
		        state.setString(9, newUser.getPassword());
		        if (newUser.getCreatedAt() != null) {
		            state.setTimestamp(10, new java.sql.Timestamp(newUser.getCreatedAt().getTime()));
		        } else {
		            state.setNull(10, Types.TIMESTAMP);
		        }

		        
		        int rowsAffected = state.executeUpdate();
		        
		        if (rowsAffected > 0) {
		            System.out.println("User inserted successfully.");
		        } else {
		            System.out.println("Failed to insert the user.");
		        }

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}
	
	public User getUserById(int userId) {
	    Connection connect = null;
	    PreparedStatement state = null;
	    ResultSet resSet = null;
	    dbConnection = new DBConnection();

	    try {
	        connect = dbConnection.getConnection();
	        String query = "SELECT id, first_name, last_name, phone_nr, email, address, city, " +
                    "sex, date_of_birth,password, created_at FROM user WHERE id=?";
	        state = connect.prepareStatement(query);
	        state.setInt(1, userId);
	        resSet = state.executeQuery();

	        if (resSet.next()) {
	            User user = new User(resSet.getInt("id"));

	            // Use the setParameter method 
	            user.setFirstName(resSet.getString("first_name"));
	            user.setLastName(resSet.getString("last_name"));
	            user.setPhoneNr(resSet.getString("phone_nr"));
	            user.setEmail(resSet.getString("email"));
	            user.setAddress(resSet.getString("address"));
	            user.setCity(resSet.getString("city"));
	            user.setSex(resSet.getString("sex").charAt(0));
	            user.setDateOfBirth(resSet.getDate("date_of_birth"));
	            user.setPassword(resSet.getString("password"));
	            user.setCreatedAt(resSet.getTimestamp("created_at"));

	            return user;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbConnection.closeResources(connect, state, resSet);
	    }
	    return null;
	}
	
	public User loginUser(String email, String password) {
	    User user = null;
	    Connection connect = null;
	    PreparedStatement state = null;
	    ResultSet resSet = null;
	    dbConnection = new DBConnection();

	    try {
	        connect = dbConnection.getConnection();
	        String query = "SELECT id, first_name, last_name, phone_nr, email, address, city, " +
	                "sex, date_of_birth,password, created_at FROM user WHERE email=? AND password=?";
	        state = connect.prepareStatement(query);

	        state.setString(1, email);
	        state.setString(2, password);

	        resSet = state.executeQuery();
	        while (resSet.next()) {
	            user = new User(); 

	            user.setId(resSet.getInt("id"));
	            user.setFirstName(resSet.getString("first_name"));
	            user.setLastName(resSet.getString("last_name"));
	            user.setPhoneNr(resSet.getString("phone_nr"));
	            user.setEmail(resSet.getString("email"));
	            user.setAddress(resSet.getString("address"));
	            user.setCity(resSet.getString("city"));
	            user.setSex(resSet.getString("sex").charAt(0));
	            user.setDateOfBirth(resSet.getDate("date_of_birth"));
	            user.setPassword(resSet.getString("password"));
	            user.setCreatedAt(resSet.getTimestamp("created_at"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbConnection.closeResources(connect, state, resSet);
	    }

	    if (user == null) {
	        System.out.println("Nu s-a gasit niciun user");
	    }

	    return user;
	}

	
	
	
	public Boolean userExists(String email) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();
		
		try {
			connect = dbConnection.getConnection();
			String query = "SELECT COUNT(*) FROM user WHERE email = ? ";
			state = connect.prepareStatement(query);
			
			state.setString(1, email);
			resSet = state.executeQuery();
			if(resSet.next()) {
				int count = resSet.getInt(1);
				return count >0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
		return false;
	}
	
	public static class UserExistsException extends Exception {
		public UserExistsException(String message) {
			super(message);
		}
	}
	
	public List<Integer> getUsersPets(int userId) {
		List<Integer> petIdsList = new ArrayList<>();
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();
		
		try {
			connect = dbConnection.getConnection();
			String query ="SELECT id_pet FROM ownership WHERE id_user = ?";
			state = connect.prepareStatement(query);
			state.setInt(1, userId);
			resSet = state.executeQuery();
			
			while(resSet.next()) {
				int petId = resSet.getInt("id_pet");
				petIdsList.add(petId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
		
		return petIdsList;
	}
	
	

}
