<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>�α���</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #ececec;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 8px;
            font-weight: bold;
        }

        input {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #1ec800;
            color: #fff;
            padding: 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        button:hover {
            background-color: #188700;
        }

        .signup-link {
            margin-top: 10px;
            text-align: center;
        }

        .signup-link a {
            color: #1ec800;
            text-decoration: none;
            font-weight: bold;
        }

        .signup-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>�α���</h2>
    <form>
        <label for="username">���̵�:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">��й�ȣ:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">�α���</button>

        <div class="signup-link">
            ���� ȸ���� �ƴϽŰ���? <a href="#">ȸ������</a>
        </div>
    </form>
</div>

</body>
</html>