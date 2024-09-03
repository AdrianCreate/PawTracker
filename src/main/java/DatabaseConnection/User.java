package DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

public class User {
	int id;
	String first_name = "", last_name = "", phone_nr= "", email = "", address = "";
	Date date_of_birth;
	String city = "", password = "";
	char sex =0;
	Timestamp created_at;
	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public String getPhoneNr() {
		return phone_nr;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public Date getDateOfBirth() {
		return date_of_birth;
	}

	public String getCity() {
		return city;
	}

	public String getPassword() {
		return password;
	}

	public char getSex() {
		return sex;
	}

	public Timestamp getCreatedAt() {
		return created_at;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public void setPhoneNr(String phone_nr) {
		this.phone_nr = phone_nr;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDateOfBirth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}

	public User(User user) {
		// TODO Auto-generated constructor stub
		this.id = user.id;
		this.first_name = user.first_name;
		this.last_name = user.last_name;
		this.phone_nr = user.phone_nr;
		this.email = user.email;
		this.address = user.address;
		this.city = user.city;
		this.sex = user.sex;
		this.date_of_birth = user.date_of_birth;
		this.password = user.password;
		this.created_at = user.created_at;
	}

	public User(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	public User(String first_name, String last_name, String password, String address, String city, String email, String phone_nr, char sex, Date date_of_birth) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.address = address;
		this.city = city;
		this.email = email;
		this.phone_nr = phone_nr;
		this.sex = sex;
		this.date_of_birth = date_of_birth;
	}
	
	public User() {
		
	}

	
	  @Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", phone_nr=" + phone_nr
				+ ", email=" + email + ", address=" + address + ", date_of_birth=" + date_of_birth + ", city=" + city
				+ ", password=" + password + ", sex=" + sex + ", created_at=" + created_at + "]";
	}
	
}


	
//	public int getId() {
//		return id;
//	}
//	public void setId(PreparedStatement state, int id) throws SQLException {
//		state.setInt(12, id);
//	}
//	public String getFirstName() {
//		return name;
//	}
//	public void setName(PreparedStatement state, String name) throws SQLException {
//        state.setString(1, name);
//    }
//	public String getSex() {
//		return sex;
//	}
//	public void setSex(PreparedStatement state, String sex) throws SQLException {
//        state.setString(2, sex);
//    }
//	public String getType() {
//		return type;
//	}
//	public void setType(PreparedStatement state, String type) throws SQLException {
//        state.setString(3, type);
//    }
//	public String getBreed() {
//		return email;
//	}
//	public void setBreed(PreparedStatement state, String breed) throws SQLException {
//        state.setString(4, breed);
//    }
//	public String getColour() {
//		return address;
//	}
//	public void setColour(PreparedStatement state, String colour) throws SQLException {
//        state.setString(5, colour);
//    }
//	public Date getDateOfBirth() {
//		return date_of_birth;
//	}
//	public void setDateOfBirth(PreparedStatement state, Date dateOfBirth) throws SQLException {
//	    if (dateOfBirth != null) {
//	        state.setDate(6, new java.sql.Date(dateOfBirth.getTime()));
//	    } else {
//	        state.setNull(6, Types.DATE);
//	    }
//	}
//	public String getDistinctiveSigns() {
//		return distinctive_signs;
//	}
//	public void setDistinctiveSigns(PreparedStatement state, String distinctiveSigns) throws SQLException {
//	    state.setString(7, distinctiveSigns);
//	}
//	public String getPhotoUrl() {
//		return password;
//	}
//	public void setPhotoUrl(PreparedStatement state, String photoUrl) throws SQLException {
//	    state.setString(8, photoUrl);
//	}
//	public String getMedication() {
//		return medication;
//	}
//	public void setMedication(PreparedStatement state, String medication) throws SQLException {
//	    state.setString(9, medication);
//	}
//
//	public String getQrCode() {
//		return qr_code;
//	}
//	public void setQrCode(PreparedStatement state, String qrCode) throws SQLException {
//	    state.setString(10, qrCode);
//	}
//	public Timestamp getCreatedAt() {
//		return created_at;
//	}
//	public void setCreatedAt(PreparedStatement state, Timestamp created_at) throws SQLException {
//	    if (created_at != null) {
//	        state.setTimestamp(11, new java.sql.Timestamp(created_at.getTime()));
//	    } else {
//	        state.setNull(11, Types.TIMESTAMP);
//	    }
//	}
//	
//	public Object getParameter(String parameterName) {
//	    switch (parameterName) {
//	        case "id":
//	            return id;
//	        case "name":
//	            return name;
//	        case "sex":
//	            return sex;
//	        case "type":
//	            return type;
//	        case "breed":
//	            return email;
//	        case "colour":
//	            return address;
//	        case "date_of_birth":
//	            return date_of_birth;
//	        case "distinctive_signs":
//	            return distinctive_signs;
//	        case "photo_url":
//	            return password;
//	        case "qr_code":
//	            return qr_code;
//	        case "medication":
//	            return medication;
//	        case "created_at":
//	            return created_at;
//	        default:
//	            throw new IllegalArgumentException("Unknown parameter: " + parameterName);
//	    }
//	}
//	
//	 public <T> void setParameter(String parameterName, T value) {
//	        switch (parameterName) {
//	            case "id":
//	                this.id = (Integer) value;
//	                break;
//	            case "name":
//	                this.name = (String) value;
//	                break;
//	            case "sex":
//	                this.sex = (String) value;
//	                break;
//	            case "type":
//	                this.type = (String) value;
//	                break;
//	            case "breed":
//	                this.email = (String) value;
//	                break;
//	            case "colour":
//	                this.address = (String) value;
//	                break;
//	            case "date_of_birth":
//	                this.date_of_birth = (Date) value;
//	                break;
//	            case "distinctive_signs":
//	                this.distinctive_signs = (String) value;
//	                break;
//	            case "photo_url":
//	                this.password = (String) value;
//	                break;
//	            case "medication":
//	                this.medication = (String) value;
//	                break;
//	            case "qr_code":
//	                this.qr_code = (String) value;
//	                break;
//	            case "created_at":
//	                this.created_at = (Timestamp) value;
//	                break;
//	            default:
//	                throw new IllegalArgumentException("Unknown parameter: " + parameterName);
//	        }
//	    }

		
//	
//
//}
