<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <title>강사진</title>
            <meta content="width=device-width, initial-scale=1.0" name="viewport">
            <meta content="" name="keywords">
            <meta content="" name="description">
            <style>
                #teacher-list {
                    display: flex;
                    flex-wrap: wrap;
                }

                /* Set a style for all buttons */
                button {
                    background-color: #04AA6D;
                    color: white;
                    padding: 14px 20px;
                    margin: 8px 0;
                    border: none;
                    cursor: pointer;
                    width: 100%;
                }

                button:hover {
                    opacity: 0.8;
                }

                /* Extra styles for the cancel button */
                .cancelbtn {
                    width: auto;
                    padding: 10px 18px;
                    background-color: #f44336;
                }

                /* Center the image and position the close button */
                .imgcontainer {
                    text-align: center;
                    margin: 12px 0 12px 0;
                    position: relative;
                }

                img.avatar {
                    width: 35%;
                    border-radius: 50%;
                    border: 2px solid #06BBCC;
                }

                .container {
                    padding: 16px;
                }

                span.psw {
                    float: right;
                    padding-top: 16px;
                }

                .modal {
                    background-color: rgba(0, 0, 0, 0.4);
                    padding: 5%;
                }

                .modal-content {
                    top: 0;
                    bottom: 0;
                    left: 0;
                    right: 0;
                    max-width: 500px;
                    height: auto;
                    max-height: 100%;
                    margin: auto;
                    padding: 40px;
                    overflow: auto;
                }



                /* The Close Button (x) */
                .close {
                    position: absolute;
                    right: 25px;
                    top: 0;
                    color: #000;
                    font-size: 35px;
                    font-weight: bold;
                }

                .close:hover,
                .close:focus {
                    color: red;
                    cursor: pointer;
                }

                /* Add Zoom Animation */
                .animate {
                    -webkit-animation: animatezoom 0.6s;
                    animation: animatezoom 0.6s
                }

                @-webkit-keyframes animatezoom {
                    from {
                        -webkit-transform: scale(0)
                    }

                    to {
                        -webkit-transform: scale(1)
                    }
                }

                @keyframes animatezoom {
                    from {
                        transform: scale(0)
                    }

                    to {
                        transform: scale(1)
                    }
                }

                /* Change styles for span and cancel button on extra small screens */
                @media screen and (max-width: 300px) {
                    span.psw {
                        display: block;
                        float: none;
                    }

                    .cancelbtn {
                        width: 100%;
                    }
                }

                span.name-style {
                    font-size: 20px;
                    font-weight: bold;
                }
            </style>
        </head>







        <jsp:include page="/separate/head.jsp"></jsp:include>

        </head>

        <body>
            <jsp:include page="/separate/header2.jsp"></jsp:include>

            <!-- Header Start -->
            <div class="container-fluid py-4">
                <div class="container">
                    <div class="row justify-content">
                        <div class="col-lg-10">
                            <div class="display-3" style="font-size: 20px;">모모런과 함께 해주시는 분들</div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->

            <!-- 강사 리스트 Start -->

            <div class="container">
                <div id="teacher-list" class="container">

                </div>
            </div>
            <!-- 강사 리스트 End -->


            <!-- Back to Top -->
            <jsp:include page="/separate/script.jsp"></jsp:include>
            <jsp:include page="/separate/footer.jsp"></jsp:include>
            <script src="${pageContext.request.contextPath}/js/t-list.js"></script>




            <!-- 팝업 화면 -->

            <div id="id01" class="modal">
                <div class="modal-content" id="id02">
                    <span onclick="document.getElementById('id01').style.display='none'" class="close"
                        title="Close Modal">&times;</span>
                    <div class="imgcontainer">
                        <img id="imgView" alt="Avatar" class="avatar">
                    </div>

                    <div class="container" style="background-color:#f1f1f1">

                        <span class="name-style" id="name">name</span>
                        <!-- <span id="pf"><b>분야</b></span><br> -->
                        <span id="hope">hope</span><br>
                        <span id="pf"><b>포트폴리오 링크</b></span><br>
                        <a href="#" id="pfLink"
                            onclick="location.href=document.getElementById('pfLink').innerHTML;">포트폴리오
                            링크</a><br>
                        <span id="pf"><b>소개</b></span><br>
                        <span id="intro">intro</span><br>

                        <button type="button" onclick="" class="cancelbtn" style=background-color:#06BBCC>개설
                            강의</button>

                    </div>
                </div>
            </div>
            <script>

                var modal = document.getElementById('id01');

                window.onclick = function (event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            </script>

        </body>

        </html>