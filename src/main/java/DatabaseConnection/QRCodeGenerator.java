package DatabaseConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	public static void generateQRcode(String data, String charset, Map<EncodeHintType, ErrorCorrectionLevel> map, int h,
			int w, int petId) throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, w, h);

		// Convert BitMatrix to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
		byte[] byteArray = baos.toByteArray();

		// Save to the database
		saveToDatabase(byteArray, petId);
	}

	public static void saveToDatabase(byte[] data, int petId) {
		Connection connect = null;
		PreparedStatement state = null;
		ResultSet resSet = null;
		DBConnection dbConnection = new DBConnection();

		try {
			connect = dbConnection.getConnection();
			String sql = "UPDATE pet SET qr_code = ? WHERE id = ?";

			try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
				String base64Data = Base64.getEncoder().encodeToString(data);

				preparedStatement.setString(1, base64Data);
				preparedStatement.setInt(2, petId);
				preparedStatement.executeUpdate();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConnection.closeResources(connect, state, resSet);
		}
	}

	public static void generateQrContactInfo(int petId, int userId) throws WriterException, IOException {
		PetHandler petHandler = new PetHandler();
		UserHandler userHandler = new UserHandler();
		Pet pet = petHandler.getPetById(petId);
		User owner = userHandler.getUserById(userId);
		String qrCodeData = "Pet id: " + petId + "\nPet name: " + pet.getName() + "\nOwner: " + owner.getFirstName()
				+ " " + owner.getLastName() + "\nPhone number: " + owner.getPhoneNr() + "\nEmail address: "
				+ owner.getEmail();
		String charset = "UTF-8";
		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeGenerator.generateQRcode(qrCodeData, charset, hashMap, 200, 200, petId);
	}

//	public static void main(String args[]) throws WriterException, IOException, NotFoundException {
//		PetHandler petHandler = new PetHandler();
//		Pet newPet = Pet(11);
//		String qrCodeData = "Pet ID: " + pet.getId() + "\nName: " + pet.getName();
//		String charset = "UTF-8";
//		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
//		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//
//		generateQRcode(str, charset, hashMap, 200, 200);
//		System.out.println("QR Code created and saved to the database successfully.");
//	}
}
