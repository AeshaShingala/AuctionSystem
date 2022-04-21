<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@include file="/bootstrapTemplate.jsp" %>
<html>
<head>
    <script>
          
        // Function to check Whether both passwords
        // is same or not.

        function checkPassword(form) {
            var password1 = document.getElementById("password").value;
            var password2 = document.getElementById("repeatPassword").value;
            console.log(password1);
            console.log(password2);
            var currentUrl = window.location.href;
                  
            // If Not same return False.    
            if (password1 != password2) {
                alert ("\nPassword did not match: Please try again...")
                return false;
            }
            return true;
        }
    </script>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
    <form method="post" action="/reset" onsubmit=" return checkPassword(this)">
        <div class="form-group">
          <label for="email">Email address</label>
          <input type="email" class="form-control" id="email" name = "email" aria-describedby="emailHelp" readonly value="${email}" placeholder="Enter email">
        </div>
        <div class="form-group">
          <label for="Password">Password</label>
          <input type="password" required class="form-control" id="password" placeholder="Enter Password" name="password">
        </div>
        <div class="form-group">
            <label for="repeatPassword">Re-Enter Password</label>
            <input type="password" required class="form-control" id="repeatPassword" placeholder="Re-Enter Password" name="repeatPassword">
          </div>
        <button type="submit" class="btn btn-primary" >Submit</button>
      </form>
</body>
</html>