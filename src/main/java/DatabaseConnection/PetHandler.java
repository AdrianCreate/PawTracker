package DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.google.zxing.WriterException;

public class PetHandler {

	DBConnection dbConnection;

	public List<Pet> handlePets() {
		ArrayList<Pet> petList = new ArrayList<Pet>();
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String query = "SELECT id, name, sex, type, breed, colour, date_of_birth, distinctive_signs, "
					+ "photo_url, qr_code, medication, created_at FROM pet";
			state = connect.prepareStatement(query);
			resSet = state.executeQuery();

			while (resSet.next()) {
				Pet pet = new Pet(resSet.getInt("id"));

				// Use the setParameter method to dynamically set attributes
				pet.setName(resSet.getString("name"));
				pet.setSex(resSet.getString("sex").charAt(0));
				pet.setType(resSet.getString("type"));
				pet.setBreed(resSet.getString("breed"));
				pet.setColour(resSet.getString("colour"));
				pet.setDateOfBirth(resSet.getDate("date_of_birth"));
				pet.setDistinctiveSigns(resSet.getString("distinctive_signs"));
				pet.setPhotoUrl(resSet.getString("photo_url"));
				pet.setQrCode(resSet.getString("qr_code"));
				pet.setMedication(resSet.getString("medication"));
				pet.setIsLost(resSet.getInt("is_lost"));
				pet.setCreatedAt(resSet.getTimestamp("created_at"));

				petList.add(pet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
		return petList;
	}

	public void deletePet(Pet pet) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String deleteQuery = "DELETE FROM pet WHERE ID=" + pet.id;
			state = connect.prepareStatement(deleteQuery);
			// executeUpdate in loc de executeQuery
			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Pet with ID " + pet.id + " deleted successfully.");
			} else {
				System.out.println("No pet found with ID " + pet.id + ". Deletion failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}

	public void updatePet(Pet updatedPet) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String updateQuery = "UPDATE pet SET " + "name=?, " + "sex=?, " + "type=?, " + "breed=?, " + "colour=?, "
					+ "date_of_birth=?, " + "distinctive_signs=?, " + "photo_url=?, " + "medication=?, " + "qr_code=?, "
					+ "is_lost=?, " + "created_at=? " + "WHERE id=?";

			state = connect.prepareStatement(updateQuery);

			state.setString(1, updatedPet.getName());
			state.setString(2, String.valueOf(updatedPet.getSex()));
			state.setString(3, updatedPet.getType());
			state.setString(4, updatedPet.getBreed());
			state.setString(5, updatedPet.getColour());
			if (updatedPet.getDateOfBirth() != null) {
				state.setDate(6, new java.sql.Date(updatedPet.getDateOfBirth().getTime()));
			} else {
				state.setNull(6, Types.DATE);
			}
			state.setString(7, updatedPet.getDistinctiveSigns());
			state.setString(8, updatedPet.getPhotoUrl());
			state.setString(9, updatedPet.getMedication());
			state.setString(10, updatedPet.getQrCode());
			state.setInt(11, updatedPet.getIsLost());
			if (updatedPet.getCreatedAt() != null) {
				state.setTimestamp(12, new java.sql.Timestamp(updatedPet.getCreatedAt().getTime()));
			} else {
				state.setNull(12, Types.TIMESTAMP);
			}

			state.setInt(13, updatedPet.getId());

			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Pet updated successfully.");
			} else {
				System.out.println("Failed to update the pet. Pet with ID " + updatedPet.getId() + " not found.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}

	public int insertPet(Pet newPet) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet generatedKeys = null;
		dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();

			String insertQuery = "INSERT INTO pet (name, sex, type, breed, colour, date_of_birth, "
					+ "distinctive_signs, photo_url, medication, qr_code, is_lost, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			state = connect.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			state.setString(1, newPet.getName());
			state.setString(2, String.valueOf(newPet.getSex()));
			state.setString(3, newPet.getType());
			state.setString(4, newPet.getBreed());
			state.setString(5, newPet.getColour());
			if (newPet.getDateOfBirth() != null) {
				state.setDate(6, new java.sql.Date(newPet.getDateOfBirth().getTime()));
			} else {
				state.setNull(6, Types.DATE);
			}
			state.setString(7, newPet.getDistinctiveSigns());
			state.setString(8, newPet.getPhotoUrl());
			state.setString(9, newPet.getMedication());
			state.setString(10, newPet.getQrCode());
			state.setInt(11, newPet.getIsLost());
			if (newPet.getCreatedAt() != null) {
				state.setTimestamp(12, new java.sql.Timestamp(newPet.getCreatedAt().getTime()));
			} else {
				state.setNull(12, Types.TIMESTAMP);
			}

			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Pet inserted successfully.");
			} else {
				System.out.println("Failed to insert the pet.");
			}

			generatedKeys = state.getGeneratedKeys();
			if (generatedKeys.next()) {
				int lastInsertedId = generatedKeys.getInt(1);
				return lastInsertedId;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, generatedKeys);
		}
		return -1;
	}

	public int insertPetWithQRCode(Pet newPet, int petId, User owner) throws WriterException, IOException {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet generatedKeys = null;
		dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();

			String insertQuery = "INSERT INTO pet (name, sex, type, breed, colour, date_of_birth, "
					+ "distinctive_signs, photo_url, medication, qr_code, is_lost, created_at, owner_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			state = connect.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			state.setString(1, newPet.getName());
			state.setString(2, String.valueOf(newPet.getSex()));
			state.setString(3, newPet.getType());
			state.setString(4, newPet.getBreed());
			state.setString(5, newPet.getColour());
			if (newPet.getDateOfBirth() != null) {
				state.setDate(6, new java.sql.Date(newPet.getDateOfBirth().getTime()));
			} else {
				state.setNull(6, Types.DATE);
			}
			state.setString(7, newPet.getDistinctiveSigns());
			state.setString(8, newPet.getPhotoUrl());
			state.setString(9, newPet.getMedication());

			// Generate and save QR code with information about pet and owner
			String qrCodeData = "Pet ID: " + petId + "\nName: " + newPet.getName() + "\nOwner: " + owner.getFirstName()
					+ " " + owner.getLastName() + "\nEmail: " + owner.getEmail() + "\nPhone: " + owner.getPhoneNr();
			QRCodeGenerator.generateQRcode(qrCodeData, "UTF-8", null, 200, 200, petId);

			state.setString(10, newPet.getQrCode());
			state.setInt(11, newPet.getIsLost());
			if (newPet.getCreatedAt() != null) {
				state.setTimestamp(12, new java.sql.Timestamp(newPet.getCreatedAt().getTime()));
			} else {
				state.setNull(12, Types.TIMESTAMP);
			}

			state.setInt(13, petId);

			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Pet inserted successfully.");
			} else {
				System.out.println("Failed to insert the pet.");
			}

			generatedKeys = state.getGeneratedKeys();
			if (generatedKeys.next()) {
				int lastInsertedId = generatedKeys.getInt(1);
				return lastInsertedId;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, generatedKeys);
		}
		return -1;
	}

	public Pet getPetById(int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		dbConnection = new DBConnection();

//		try {
//			connect = dbConnection.getConnection();
//			String query = "SELECT id, name, sex, type, breed, colour, date_of_birth, distinctive_signs, "
//					+ "photo_url, qr_code, medication, created_at FROM pet WHERE id=?";
//			state = connect.prepareStatement(query);
//			state.setInt(1, petId);
//			resSet = state.executeQuery();
//
//			if (resSet.next()) {
//				Pet pet = new Pet(resSet.getInt("id"));
//
//				// Use the setParameter method to dynamically set attributes
//				pet.setName(resSet.getString("name"));
//				pet.setSex(resSet.getString("sex").charAt(0));
//				pet.setType(resSet.getString("type"));
//				pet.setBreed(resSet.getString("breed"));
//				pet.setColour(resSet.getString("colour"));
//				pet.setDateOfBirth(resSet.getDate("date_of_birth"));
//				pet.setDistinctiveSigns(resSet.getString("distinctive_signs"));
//				pet.setPhotoUrl(resSet.getString("photo_url"));
//				pet.setQrCode(resSet.getString("qr_code"));
//				pet.setMedication(resSet.getString("medication"));
//				pet.setCreatedAt(resSet.getTimestamp("created_at"));
//
//				return pet;
//			}
//		} 
		try {
			connect = dbConnection.getConnection();
			String query = "SELECT id, name, sex, type, breed, colour, date_of_birth, distinctive_signs, \"\r\n"
					+ "					+ \"photo_url, qr_code, medication, is_lost, created_at FROM pet WHERE id=?";
			state = connect.prepareStatement(query);
			state.setInt(1, petId);
			resSet = state.executeQuery();

			if (resSet.next()) {
				Pet pet = new Pet(resSet.getInt("id"));

				pet.setName(resSet.getString("name"));
				pet.setSex(resSet.getString("sex").charAt(0));
				pet.setType(resSet.getString("type"));
				pet.setBreed(resSet.getString("breed"));
				pet.setColour(resSet.getString("colour"));
				pet.setDateOfBirth(resSet.getDate("date_of_birth"));
				pet.setDistinctiveSigns(resSet.getString("distinctive_signs"));
				pet.setPhotoUrl(resSet.getString("photo_url"));
				pet.setQrCode(resSet.getString("qr_code"));
				pet.setMedication(resSet.getString("medication"));
				pet.setCreatedAt(resSet.getTimestamp("created_at"));
				pet.setIsLost(resSet.getInt("is_lost"));
				return pet;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
		return null;
	}

	public int getLastInsertedPetId() {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String query = "SELECT LAST_INSERT_ID() AS last_id";
			state = connect.prepareStatement(query);
			resSet = state.executeQuery();

			if (resSet.next()) {
				int lastInsertedId = resSet.getInt("last_id");
				return lastInsertedId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
		return -1;
	}

	public void createOwnership(int userId, int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String query = "INSERT INTO ownership (id_user, id_pet) VALUES (?, ?)";
			state = connect.prepareStatement(query);
			state.setInt(1, userId);
			state.setInt(2, petId);

			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Ownership created successfully.");
			} else {
				System.out.println("Failed to create ownership.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}

	public void removePetOwnership(int userId, int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String query = "DELETE FROM ownership WHERE id_user =? AND id_pet =?";
			state = connect.prepareStatement(query);
			state.setInt(1, userId);
			state.setInt(2, petId);

			int rowsAffected = state.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Ownership removed succesfully");
			} else {
				System.out.println("Failed to remove ownership");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, null);
		}
	}

	public void losePet(int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resultSet = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();

			// ia adresa userului prin ownershipul petului
			String userQuery = "SELECT u.address, u.city FROM user u " + "JOIN ownership o ON u.id = o.id_user "
					+ "WHERE o.id_pet = ?";
			try (PreparedStatement userStatement = connect.prepareStatement(userQuery)) {
				userStatement.setInt(1, petId);
				resultSet = userStatement.executeQuery();

				if (resultSet.next()) {
					String userAddress = resultSet.getString("address");
					String userCity = resultSet.getString("city");

					String updatePetQuery = "UPDATE pet SET is_lost = 1 WHERE id = ?";
					try (PreparedStatement updatePetStatement = connect.prepareStatement(updatePetQuery)) {
						updatePetStatement.setInt(1, petId);
						updatePetStatement.executeUpdate();
					}

					String losePetQuery = "INSERT INTO member_alert_lost (pet_id, address_lost, city_lost, case_solved) "
							+ "VALUES (?, ?, ?, 0)";
					try (PreparedStatement losePetStatement = connect.prepareStatement(losePetQuery)) {
						losePetStatement.setInt(1, petId);
						losePetStatement.setString(2, userAddress);
						losePetStatement.setString(3, userCity);
						losePetStatement.executeUpdate();
					}

					System.out.println("Pet marked as lost successfully.");

				} else {
					System.out.println("User information not found for the given pet.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resultSet);
		}
	}

	public void findPet(int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();

			// Update the member_alert_lost table
			String findPetQuery = "UPDATE member_alert_lost SET case_solved = 1, date_solved = ? WHERE pet_id = ? ORDER BY id DESC LIMIT 1";
			try (PreparedStatement findPetStatement = connect.prepareStatement(findPetQuery)) {
				// Set the current timestamp for date_solved
				Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
				findPetStatement.setTimestamp(1, currentTimestamp);
				findPetStatement.setInt(2, petId);
				int rowsAffected = findPetStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Pet marked as found successfully.");

					String updatePetQuery = "UPDATE pet SET is_lost = 0 WHERE id = ?";
					try (PreparedStatement updatePetStatement = connect.prepareStatement(updatePetQuery)) {
						updatePetStatement.setInt(1, petId);
						updatePetStatement.executeUpdate();
					}

					System.out.println("is_lost value in the pet table updated to 0.");
				} else {
					System.out.println("Pet not found in the member_alert_lost table.");

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			dbConnection.closeResources(connect, state, null);
		}
	}

//	public boolean isPetLost(int petId) {
//		Connection connect = null;
//		PreparedStatement state = null;
//		ResultSet resSet = null;
//		DBConnection dbConnection = new DBConnection();
//
//		try {
//			connect = dbConnection.getConnection();
//
//			// ia adresa userului prin ownershipul petului
//			String query = "SELECT COUNT(*) AS count FROM member_alert_lost WHERE pet_id = ? AND case_solved = 0";
//			state = connect.prepareStatement(query);
//			state.setInt(1, petId);
//			resSet = state.executeQuery();
//
//			if (resSet.next()) {
//				int count = resSet.getInt("count");
//				return count > 0;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbConnection.closeResources(connect, state, resSet);
//		}
//
//		return false;
//	}
}