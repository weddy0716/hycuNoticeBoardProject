<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
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
            width: 450px;
            max-width: 100%;
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

        .input-container {
            display: flex;
            align-items: center;
        }

        input {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            flex: 1;
        }

        #checkResult {
            color: green;
            margin-left: 10px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>회원가입</h2>
    <form>
        <label for="username">아이디</label>
        <div class="input-container">
            <input type="text" id="username" name="username" required>
            <button type="button" onclick="checkUsername()">ID 중복 확인</button>
        </div>
        <span id="checkResult"></span>
       
        <label for="fullName">성함</label>
        <input type="text" id="fullName" name="fullName" required>

        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required>

        <label for="email">이메일(비밀번호찾기시 필요)</label>
        <input type="email" id="email" name="email" required>


        <button type="submit">가입하기</button>
    </form>

    <script>
        function checkUsername() {
            var username = document.getElementById('username').value;

            // 실제로는 서버에 AJAX 요청을 보내서 중복 여부를 확인해야 합니다.
            // 여기서는 간단히 사용자 입력값을 출력하는 것으로 대체합니다.
            document.getElementById('checkResult').innerText = 'ID 중복 확인: ' + username;
        }
    </script>
</div>

</body>
</html>