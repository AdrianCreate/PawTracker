package DatabaseConnection;

import java.sql.Timestamp;
import java.util.Date;

public class Pet {
	int id;
	String name = "", type = "", breed = "", colour = "";
	char sex = 0;
	Date date_of_birth;
	String distinctive_signs = "", photo_url = "", medication = "", qr_code = "";
	Timestamp created_at;
	int is_lost;;

	public Pet(Pet pet) {
		// TODO Auto-generated constructor stub
		this.id = pet.id;
		this.name = pet.name;
		this.sex = pet.sex;
		this.type = pet.type;
		this.breed = pet.breed;
		this.colour = pet.colour;
		this.date_of_birth = pet.date_of_birth;
		this.distinctive_signs = pet.distinctive_signs;
		this.photo_url = pet.photo_url;
		this.medication = pet.medication;
		this.qr_code = pet.qr_code;
		this.created_at = pet.created_at;
		this.is_lost = pet.is_lost;
	}

	public Pet(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	public Pet() {

	}

	public Pet(String name, char sex, String type, String breed, String colour, Date date_of_birth,
			String distinctive_signs, String medication) {
		this.name = name;
		this.type = type;
		this.breed = breed;
		this.colour = colour;
		this.sex = sex;
		this.date_of_birth = date_of_birth;
		this.distinctive_signs = distinctive_signs;
		this.medication = medication;

	}

	public String toString() {
		return "Pet: id=" + id + ", name=" + name + ", sex= " + sex + ", type=" + type + ", breed=" + breed
				+ ", colour=" + colour + ", date_of_birth=" + date_of_birth + ", distinctive_signs=" + distinctive_signs
				+ ", photo_url=" + photo_url + ", medication" + medication + ", qr_code=" + qr_code + ", created_at="
				+ created_at;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getBreed() {
		return breed;
	}

	public String getColour() {
		return colour;
	}

	public char getSex() {
		return sex;
	}

	public Date getDateOfBirth() {
		return date_of_birth;
	}

	public String getDistinctiveSigns() {
		return distinctive_signs;
	}

	public String getPhotoUrl() {
		return photo_url;
	}

	public String getMedication() {
		return medication;
	}

	public String getQrCode() {
		return qr_code;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public int getIsLost() {
		return is_lost;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setDateOfBirth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public void setDistinctiveSigns(String distinctive_signs) {
		this.distinctive_signs = distinctive_signs;
	}

	public void setPhotoUrl(String photo_url) {
		this.photo_url = photo_url;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public void setQrCode(String qr_code) {
		this.qr_code = qr_code;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

	public void setIsLost(int is_lost) {
		this.is_lost = is_lost;
	}

}
