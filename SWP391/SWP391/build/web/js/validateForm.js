function validateForm() {
    var firstName = document.getElementById('first_name').value.trim();
    var lastName = document.getElementById('last_name').value.trim();
    var email = document.getElementById('email').value.trim();
    var password = document.getElementById('password').value.trim();
    var telephone = document.getElementById('telephone').value.trim();
    var gender = document.getElementById('gender').value.trim();
    var role = document.querySelector('input[name="role"]:checked');
    var rePassword = document.getElementById('re-password').value.trim();

    if (password !== rePassword) {
        alert("Passwords do not match");
        return false;
    }
    if (firstName === "") {
        alert("Please enter first name");
        return false;
    }

    if (lastName === "") {
        alert("Please enter last name");
        return false;
    }

    if (email === "") {
        alert("Please enter email");
        return false;
    } else if (!isValidEmail(email)) {
        alert("Please enter a valid email address");
        return false;
    }

    if (password === "") {
        alert("Please enter password");
        return false;
    }

    if (telephone === "") {
        alert("Please enter telephone number");
        return false;
    }
    // You can add more complex telephone number validation here

    if (gender === "") {
        alert("Please select gender");
        return false;
    }

    if (!role) {
        alert("Please select a role");
        return false;
    }

    return true; // Form validated successfully
}
function isValidEmail(email) {
    // Regular expression for email validation
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}
