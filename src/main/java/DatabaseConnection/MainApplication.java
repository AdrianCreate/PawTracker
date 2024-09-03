package DatabaseConnection;

import java.io.IOException;

import com.google.zxing.WriterException;

public class MainApplication {

	public static void main(String[] args) throws WriterException, IOException {
		// Connect to the database using the DatabaseConnector class
		PetHandler petHandler = new PetHandler();
		UserHandler userHandler = new UserHandler();
		// Pet deletePet = new Pet(2989421);
		// petHandler.deletePet(anotherPet);
		// String parametruPg = "Rex' OR '1'='1";
//        List<Pet> petList = petHandler.handlePets();
//        
//        for(Iterator<Pet> iterator = petList.iterator(); iterator.hasNext();) {
//        	System.out.println(iterator.next());
//        }

//        List<User> userList = userHandler.showUsers();
//        for(Iterator<User> iterator = userList.iterator(); iterator.hasNext();) {
//        	System.out.println(iterator.next());
//        }
//        Pet updatedPet = new Pet(anotherPet);
//        updatedPet.setParameter("breed", "Siamese");
//        petHandler.updatePet(updatedPet);

		System.out.println("");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("");

		// petHandler.insertPet(anotherPet);
		// System.out.println(userHandler.getUserById(13));
		// userHandler.insertUser(anotherUser);
//        User updatedUser = new User(anotherUser);
//        anotherUser.setFirstName("yanis");
//        anotherUser.setAddress("Ireg");
//        userHandler.updateUser(anotherUser);
		// userHandler.deleteUser(anotherUser);
//        User noUser = new User();
//        User no2User = new User();
//        userHandler.insertUser(noUser);

		// System.out.println(petHandler.insertPet(anotherPet));

		QRCodeGenerator.generateQrContactInfo(28, 33);
		// petHandler.losePet(33);
//		Pet sandel = petHandler.getPetById(33);
//		System.out.println(sandel.getIsLost());
		// petHandler.findPet(33);
	}
}
