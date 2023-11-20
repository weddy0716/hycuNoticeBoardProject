<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/views/cmm/inc_header.jsp" %>
<head>
    <meta charset="UTF-8">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
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
   	<script type="text/javascript">
	   function memberJoin(){
		alert("####PSH") 
	   }
	</script>
</head>

<body>

<div class="container">
    <h2>회원가입</h2>
    <form onsubmit="return false">
        <label for="username">아이디</label>
        <div class="input-container">
            <input type="text" id="userId" name="userId" >
            <button type="button" onclick="checkUsername()">ID 중복 확인</button>
        </div>
        <span id="checkResult"></span>
       
        <label for="fullName">성함</label>
        <input type="text" id="fullName" name="fullName" >

        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" >

        <label for="email">이메일(비밀번호찾기시 필요)</label>
        <input type="email" id="email" name="email" >
        <button id="memberJoin" onclick="memberJoin()">가입하기</button>
    </form>
</div>
</body>