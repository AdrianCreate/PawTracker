 function openEditPetModal(petId) {
        document.getElementById('modalPetId').value = petId;
        document.getElementById('editPetModal').style.display = 'block';
    }

    // Function to close the edit pet modal
    function closeEditPetModal() {
        document.getElementById('editPetModal').style.display = 'none';
    }

    // Close the modal if the user clicks outside of it
    window.onclick = function(event) {
        var modal = document.getElementById('editPetModal');
        if (event.target === modal) {
            closeEditPetModal();
        }
    };