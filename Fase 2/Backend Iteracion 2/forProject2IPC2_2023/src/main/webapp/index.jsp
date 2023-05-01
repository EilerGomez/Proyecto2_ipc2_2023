

<%@page import="java.util.Base64"%>
<%@ page import="java.sql.*"%>

<%@ page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html class="cuerpo">
<head>
    <title>Subir un archivo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style>
        .main{
            width: 350px;
            height: 200px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            border: 2px solid;
            background-color: #ffffff;
        }
        .cuerpo{
            background-color: gainsboro;
        }
        form{
            width: 100%;
            position: relative;
            display: block;
            margin: 20px auto;
        }
        input{
            margin: 10px 0;
        }

    </style>
</head>

<body>
<%

%>

<div class="main">
    <form method="POST" action="cargaDatos" enctype="multipart/form-data" >
        <input type="file" name="file" accept=".json"/>
        <input type="submit" value="Cargar" class="btn btn-primary" onclick="Mensaje()">
        <script language="JavaScript" type="text/javascript">
            function Mensaje()
            {
                alert("Advertencia: Se cargaran datos");
            }
        </script>
    </form>
    <div class="downlaod">

    </div>
</div>
</body>
</html>

