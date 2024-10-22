<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Note List</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Fredoka+One&family=Nunito:wght@400;700&display=swap');

        body {
            font-family: 'Nunito', sans-serif;
            background: linear-gradient(135deg, #fdfcfb 0%, #e2d1c3 100%);
            margin: 0;
            padding: 20px;
            min-height: 100vh;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #ffffff;
            border-radius: 20px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-family: 'Fredoka One', cursive;
            color: #ff6b6b;
            text-align: center;
            font-size: 2.5em;
            margin-bottom: 30px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-radius: 10px;
        }

        th {
            background-color: #4ecdc4;
            color: white;
            font-weight: bold;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #f7f7f7;
        }

        tr:nth-child(odd) {
            background-color: #e8f5e9;
        }

        .action-links a {
            text-decoration: none;
            color: #ffffff;
            padding: 5px 10px;
            border-radius: 5px;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .edit-link {
            background-color: #ffc107;
        }

        .delete-link {
            background-color: #ff7675;
        }

        .action-links a:hover {
            opacity: 0.8;
            transform: translateY(-2px);
        }

        .add-note {
            display: inline-block;
            margin-top: 20px;
            background-color: #6c5ce7;
            color: white;
            padding: 10px 20px;
            border-radius: 30px;
            text-decoration: none;
            font-weight: bold;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .add-note:hover {
            background-color: #5a4ecc;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
        }

        @keyframes bounce {
            0%, 20%, 50%, 80%, 100% {transform: translateY(0);}
            40% {transform: translateY(-20px);}
            60% {transform: translateY(-10px);}
        }

        .bounce {
            animation: bounce 2s infinite;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="bounce">Your Cheerful Notes!</h1>
    <table>
        <tr>
            <th>Title</th>
            <th>Content</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="note" items="${notes}">
            <tr>
                <td>${note.title}</td>
                <td>${note.content}</td>
                <td class="action-links">
                    <a href="note?action=edit&id=${note.id}" class="edit-link">Edit</a>
                    <a href="note?action=delete&id=${note.id}" class="delete-link" onclick="return confirm('Are you sure you want to delete this cheerful note?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="note?action=new" class="add-note">Add a New Happy Note!</a>
</div>
</body>
</html>