
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Notification</title>
        <style>
            .notification {
                position: fixed;
                top: 20px;
                right: 20px;
                background-color: #333;
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                opacity: 0;
                transition: opacity 0.5s ease-in-out;
            }

            .show {
                opacity: 1;
            }
        </style>
    </head>
    <body>
        <div id="notification" class="notification"></div>

        <script>
            function showNotification(message) {
                const notification = document.getElementById('notification');
                notification.textContent = message;
                notification.classList.add('show');

                setTimeout(() => {
                    notification.classList.remove('show');
                }, 3000); // 3 seconds
            }

            // Example usage:
            <% if (request.getAttribute("notificationMessage") != null) { %>
            showNotification('<%= request.getAttribute("mess") %>');
            <% } %>
        </script>
    </body>
</html>