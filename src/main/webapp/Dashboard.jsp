<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="DatabaseConnection.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.apache.commons.text.StringEscapeUtils"%>



<%
Integer userId = (Integer) session.getAttribute("userId");
User user = null;

if (userId == null) {
	response.sendRedirect("Login.jsp");
} else {
	UserHandler userHandler = new UserHandler();

	user = userHandler.getUserById(userId);

	if (user == null) {
		response.sendRedirect("Login.jsp");
	}
	PetHandler petHandler = new PetHandler();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="sorttable.js"></script>
<script src="dashboard.js"></script>

<title>User details</title>
<link rel="stylesheet" href="css/dashboardStyles.css" type="text/css" />
</head>
<body>
	<div class='main-div'>
		<div class='content-div'>
			<div class='image-div'>
				<img src='./img/person.png' alt='person icon'
					class='rounded-profile-image' />
				<div>
					<h1>Welcome back,</h1>
					<h1><%=user.getFirstName()%></h1>
				</div>

			</div>
			<div class='profileDiv'>
				<!--         <p>Profile details:</p> -->
				<ul>
					<li data-label="First Name:"><%=StringEscapeUtils.escapeHtml4(user.getFirstName())%></li>
					<li data-label="Last Name:"><%=StringEscapeUtils.escapeHtml4(user.getLastName())%></li>
					<li data-label="Address:"><%=StringEscapeUtils.escapeHtml4(user.getAddress())%></li>
					<li data-label="City:"><%=StringEscapeUtils.escapeHtml4(user.getCity())%></li>
					<li data-label="Email:"><%=StringEscapeUtils.escapeHtml4(user.getEmail())%></li>
					<li data-label="Phone number:"><%=StringEscapeUtils.escapeHtml4(user.getPhoneNr())%></li>
					<li data-label="Sex:"><%=StringEscapeUtils.escapeHtml4(String.valueOf(user.getSex()))%></li>
					<li data-label="Date of birth: "><%=(user.getDateOfBirth() != null)
		? StringEscapeUtils.escapeHtml4(new SimpleDateFormat("dd-MM-yyyy").format(user.getDateOfBirth()))
		: ""%></li>
				</ul>
				<form action="EditUser.jsp">
					<button class='editButton' type="submit">Edit</button>
				</form>
			</div>
		</div>
		<div class='table-div'>


			<%
			List<Integer> petIdsList = userHandler.getUsersPets(userId);

			if (petIdsList.isEmpty()) {
			%>
			<p>This user doesn't own any pets.</p>
			<%
			} else {
			%>
			<table class="sortable">
				<thead>
					<tr>
						<th class="tg-0lax">Name:</th>
						<th class="tg-0lax">Sex:</th>
						<th class="tg-0lax">Type:</th>
						<th class="tg-0lax">Breed:</th>
						<th class="tg-0lax">Colour:</th>
						<th class="tg-0lax">Date of birth:</th>
						<th class="tg-0lax">Distinctive signs:</th>
						<th class="tg-0lax">Medication:</th>
						<th class="tg-0lax">Edit:</th>
						<th class="tg-0lax">Delete:</th>
						<th class="tg-0lax">QR Code:</th>
						<th class="tg-0lax">Pet Lost:</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Integer petId : petIdsList) {
						Pet pet = petHandler.getPetById(petId);
						if (pet != null) {
					%>
					<tr>
						<td class="tg-0lax"><%=pet.getName()%></td>
						<td class="tg-0lax">
					    <% 
					        String sex = StringEscapeUtils.escapeHtml4(String.valueOf(pet.getSex()));
					        if ("F".equals(sex)) {
					            out.print("Female");
					        } else if ("M".equals(sex)) {
					            out.print("Male");
					        }
					    %>
					</td>
						<td class="tg-0lax"><%=StringEscapeUtils.escapeHtml4(pet.getType())%></td>
						<td class="tg-0lax"><%=StringEscapeUtils.escapeHtml4(pet.getBreed())%></td>
						<td class="tg-0lax"><%=StringEscapeUtils.escapeHtml4(pet.getColour())%></td>
						<td class="tg-0lax"><%=(pet.getDateOfBirth() != null)
								? StringEscapeUtils.escapeHtml4(new SimpleDateFormat("dd-MM-yyyy").format(pet.getDateOfBirth()))
										: ""%></td>
						<td class="tg-0lax"><%=StringEscapeUtils.escapeHtml4(pet.getDistinctiveSigns())%></td>
						<td class="tg-0lax"><%=StringEscapeUtils.escapeHtml4(pet.getMedication())%></td>
						<td class="tg-0lax">
							<form action="EditPet.jsp">
								<input type="hidden" name="petId" value="<%=pet.getId()%>">
								<button class='editPetButton' type="submit">Edit</button>
							</form>
						</td>
						<td class="tg-0lax">
							<button class='deletePetButton' type="button"
								onClick="openModal(null, 'deleteModal', '<%=pet.getId()%>')">Delete</button>
						</td>
						<td class="tg-0lax">
							<button class='petQrButton'
								onClick="openModal('<%=pet.getQrCode()%>', 'myModal')">View
								qr code</button>
						</td>
						<td class="tg-0lax">
						   <% 
        if (pet.getIsLost() == 0) {
            out.print("Home and well");
        } else {
            out.print("Still not found...");
        }
    %>
						    <form method="post" action="PetStatusHandler.jsp">
						        <input type="hidden" name="petId" value="<%=pet.getId() %>">
						      <input type="hidden" name="petStatus" value="<%=(pet.getIsLost() == 1) %>">


						        <button type="submit">
								    <%= (pet.getIsLost() == 1) ? "Mark it as found" : "Mark it as lost" %>
								</button>
						    </form>
						</td>
						
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>

			<%
			}
			}
			%>

			<div class="button-container">
				<form action="Homepage.jsp">
					<button type="submit">Go back to homepage</button>
				</form>
				<form action="SignOut.jsp">
					<button type="submit">Sign out</button>
				</form>
				<form action="PetRegistration.jsp">
					<button type="submit">Register a new pet</button>
				</form>
			</div>
		</div>

	</div>

	<div id="myModal" class="modal">
		<div class="modal-content">
			<span class="close" onClick="closeModal('myModal')">&times;</span> <img
				id='qrCodeImage' />
			<button onClick="downloadQrCode()">Download qr code</button>
		</div>

	</div>

	<div id="deleteModal" class="modal">
		<div class="delete-modal-content">
			<div class="modalHeader">
				<span class="close" onClick="closeModal('deleteModal')">&times;</span>
				<p class="modalTitle">Delete pet?</p>
			</div>

			<p>Are you sure you want to delete this pet? This action cannot
				be undone</p>

			<div class="modalFooter">
				<form action="DeletePet.jsp">
					<input type="hidden" id="deletePetId" name="petId" >
					<button class="deleteButton" onClick="confirmDelete()">Yes,
						delete</button>
				</form>
				<button onClick="closeModal('deleteModal')">Cancel</button>
			</div>
		</div>
	</div>



	<script>
		let modalImage = document.getElementById("qrCodeImage")
		let imageToDownload
		function openModal(image, modalId, petId) {
			let modal = document.getElementById(modalId);
			if (image) {
				imageToDownload = image
				modalImage.src = 'data:image/png;base64,' + image;
			}

			if (petId) {
				document.getElementById("deletePetId").value = petId
			}
			modal.style.display = "block";
		}

		function closeModal(modalId) {
			let modal = document.getElementById(modalId);
			modal.style.display = "none";
		}

		function downloadQrCode() {
			let downloadLink = document.createElement("a");
			downloadLink.href = 'data:image/png;base64,' + imageToDownload;
			downloadLink.download = "qr_code.png";
			downloadLink.click();
		}

		window.onclick = function(event) {
			let modal = document.getElementById("myModal");
			if (event.target == modal) {
				modal.style.display = "none";
			}
			let deleteModal = document.getElementById("deleteModal");
			if (event.target == deleteModal) {
				deleteModal.style.display = "none";
			}
		}
		
		 
	</script>

</body>
</html>
